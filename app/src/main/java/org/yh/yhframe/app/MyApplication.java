package org.yh.yhframe.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;

import org.yh.library.db.YhDBManager;
import org.yh.library.okhttp.OkHttpUtils;
import org.yh.library.okhttp.https.HttpsUtils;
import org.yh.library.okhttp.utils.HeaderInterceptor;
import org.yh.library.okhttp.utils.LoggerInterceptor;
import org.yh.library.ui.YHActivityStack;
import org.yh.library.utils.Constants;
import org.yh.library.utils.CrashHandler;
import org.yh.library.utils.DensityUtils;
import org.yh.library.utils.FileUtils;
import org.yh.library.utils.LogUtils;
import org.yh.library.utils.NetWorkUtils;
import org.yh.library.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author hao
 * @version 1.0
 * ClassName: MyApplication<br/>
 * Description: MyApplication<br/>
 * date: 2015-6-17 下午5:00:20 <br/>
 * @since JDK 1.7
 */
public class MyApplication extends Application
{
    public static final String HOME_HOST = "http://192.168.0.130:8081/";//IP地址
    private static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication mInstance = null;
    private static final long cacheSize = 1024*1024*20;//缓存文件最大限制大小20M
    SendEmailThread sendEmail;//发送邮件
    //默认屏幕宽高
    public static int width = 1080;
    public static int height = 960;
    //延迟加载
    private Handler mHandler = new Handler();
    //json解析
    public Gson yhGson = null;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
        Constants.Config.app = this;//框架用到上下文对象
        //注册监听Activiy的声明周期
        registerActivityLifecycleCallbacks();
        width = DensityUtils.getScreenW(MyApplication.getInstance()
                .getApplicationContext()); //屏幕宽度
        height = DensityUtils.getScreenH(MyApplication.getInstance()
                .getApplicationContext());//屏幕高度
        if (width == 0)
        {
            width = 1080;
        }

        if (height == 0)
        {
            height = 960;
        }
        LogUtils.e(TAG, "MyApplication onCreate() height：" + height + " width：" + width);
        mHandler.postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                initSystem();
            }
        }, 500);
        // 网络框架初始化
        initHttp();
        //初始化小视频录制
    }

    public synchronized static MyApplication getInstance()
    {
        return mInstance;
    }

    /**
     * 初始化系统信息
     */
    public void initSystem()
    {

        if (!StringUtils.isEmpty(mInstance))
        {
            //Gson解析
            yhGson = new Gson();
            // 图片缓存框架初始化
            //initImageLoader(mInstance);
            //根据不同的用户生成不同的数据库
            Constants.Config.yhDBManager = YhDBManager.getInstance(mInstance, "yh.db", true);
        }
        // 发布BUG用邮件形式发送
       if(!LogUtils.isDebug)
       {
           CrashHandler.create(getApplicationContext());
           sendEmail = new SendEmailThread();
           sendEmail.start();
       }

    }

    /**
     * 初始化OKHTTP
     */
    public static void initHttp()
    {
        //全局设置请求头  单独设置请求头覆盖全局设置
//        Map<String, String> headers = new LinkedHashMap<>();
//        headers.put("imei", "123123123");
//        headers.put("version", "1.0");
//        headers.put("token", "");
//        headers.put("regid", "123123123");
//        YHRequestFactory.getRequestManger().setHeaders(headers);
        //缓存http
        Cache cache = new Cache(new File(FileUtils.getSavePath(Constants.httpCachePath)), cacheSize);
        //cookie
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getInstance()));
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null,
                null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60000L, TimeUnit.MILLISECONDS)
                .readTimeout(60000L, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)//允许重试
                .addInterceptor(new LoggerInterceptor("",true))//日志拦截 是否显示返回数据
//                .addInterceptor(new RetryInterceptor(3))//重试3次
                .addInterceptor(new HeaderInterceptor())// 统一请求头
                .addNetworkInterceptor(new Interceptor()//添加网络拦截器缓存用
                {
                    @Override
                    public Response intercept(Chain chain) throws IOException
                    {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        if (NetWorkUtils.isConnectedByState(getInstance())) {
                            int maxAge = 60 * 60;// 有网 就1个小时可用 缓存有效时间
                            return response.newBuilder()
                                    .header("Cache-Control", "public, max-age=" + maxAge)
                                    .build();
                        } else {
                            int maxStale = 60 * 60 * 24 * 7;// 没网 就1周可用 缓存有效时间
                            return response.newBuilder()
                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .build();
                        }
                    }
                })
                .cache(cache)//添加缓存
                .hostnameVerifier(new HostnameVerifier()
                {
                    @Override
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                })
                .cookieJar(cookieJar)
//                .cookieJar(new CookieJar()
//                {
//                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
//
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
//                    {
//                        cookieStore.put(url.host(), cookies);
//                        if (!StringUtils.isEmpty(cookieStore.get(url)))
//                        {
//                            LogUtils.e("cookieStore.get(url)", "" + cookieStore.get(url).size());
//                        }
//                        LogUtils.e("saveFromResponse", "" + cookies);
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url)
//                    {
//                        if (!StringUtils.isEmpty(cookieStore.get(url.host())))
//                        {
//                            LogUtils.e("cookieStore.get(url)", "" + cookieStore.get(url.host())
//                                    .size());
//                        }
//                        List<Cookie> cookies = cookieStore.get(url.host());
//                        LogUtils.e("loadForRequest", "" + cookies);
//                        return cookies != null ? cookies : new ArrayList<Cookie>();
//
//                    }
//                })
                .sslSocketFactory(sslParams.sSLSocketFactory,
                        sslParams.trustManager).build();
        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * 初始化缓存框架ImageLoader
     * @param context 上下文
     */
    public static void initImageLoader(Context context)
    {

//        DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.ic_launcher)//加载中图片
//                .showImageForEmptyUri(R.mipmap.ic_launcher)//加载空URL图片
//                .showImageOnFail(R.mipmap.ic_launcher)//加载错误图片
//                .cacheInMemory(true).cacheOnDisk(true)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .resetViewBeforeLoading(true).considerExifParams(false)
//                .bitmapConfig(Bitmap.Config.RGB_565).build();
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//                .memoryCacheExtraOptions(width, height)
//                // default = device screen dimensions
//                .diskCacheExtraOptions(width, height, null)
//                .threadPoolSize(5)
//                // default Thread.NORM_PRIORITY - 1
//                .threadPriority(Thread.NORM_PRIORITY)
//                // default FIFO
//                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                // default
//                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//                .memoryCacheSize(2 * 1024 * 1024)
//                .memoryCacheSizePercentage(13)
//                // default// 文件路径
////                .diskCache(
////                        new UnlimitedDiskCache(StorageUtils
////                                .getOwnCacheDirectory(context,
////                                        AppConfig.imgCachePath)))
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
//                // default// connectTimeout (20 s), readTimeout (60 s)超时时间
//                .imageDownloader(new BaseImageDownloader(context, 20 * 1000, 60 * 1000))
//                // default
//                .imageDecoder(new BaseImageDecoder(false))
//                // default
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//                // default
//                .defaultDisplayImageOptions(imageOptions) // Log日志
//                .build();
//        ImageLoader.getInstance().init(config);
    }

    /**
     * 管理所有Activity声明周期
     */
    private void registerActivityLifecycleCallbacks()
    {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks()
        {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle)
            {
                YHActivityStack.create().addActivity(activity);
                //LogUtils.e("onActivityCreated", activity.getClass().toString());
            }

            @Override
            public void onActivityStarted(Activity activity)
            {
                //LogUtils.e("onActivityStarted", activity.getClass().toString());
            }

            @Override
            public void onActivityResumed(Activity activity)
            {
               // LogUtils.e("onActivityResumed", activity.getClass().toString());
            }

            @Override
            public void onActivityPaused(Activity activity)
            {
                //LogUtils.e("onActivityPaused", activity.getClass().toString());
            }

            @Override
            public void onActivityStopped(Activity activity)
            {
                //LogUtils.e("onActivityStopped", activity.getClass().toString());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
            {
                //LogUtils.e("onActivitySaveInstanceState", activity.getClass().toString());
            }

            @Override
            public void onActivityDestroyed(Activity activity)
            {
                YHActivityStack.create().finishActivity(activity);
                //LogUtils.e("onActivityDestroyed", activity.getClass().toString());
            }
        });
    }


    /**
     * 发送邮件线程
     *
     * @author youhao
     */
    private class SendEmailThread extends Thread
    {
        @Override
        public void run()
        {
            super.run();

            //SystemUtils.sendLogEmail();
        }
    }


    // 杀进程
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        //LogUtils.e(TAG, "onLowMemory()");
    }

    @Override
    public void onTerminate()
    {
        // 程序终止的时候执行
        super.onTerminate();
        System.exit(1);
        //LogUtils.e(TAG, "onTerminate()");
    }

    @Override
    public void onTrimMemory(int level)
    {
        // 程序在内存清理的时候执行
        super.onTrimMemory(level);
        //LogUtils.e(TAG, "内存清理()：" + level + "");
    }

}
