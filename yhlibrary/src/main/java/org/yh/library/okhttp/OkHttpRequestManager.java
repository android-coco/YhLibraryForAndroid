package org.yh.library.okhttp;

import org.json.JSONException;
import org.json.JSONObject;
import org.yh.library.okhttp.builder.PostFormBuilder;
import org.yh.library.okhttp.callback.FileCallBack;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.okhttp.callback.I_RequestManager;
import org.yh.library.okhttp.callback.StringCallback;
import org.yh.library.utils.Constants;
import org.yh.library.utils.LogUtils;
import org.yh.library.utils.StringUtils;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import okhttp3.Call;
import okhttp3.Request;

import static org.yh.library.utils.StringUtils.isEmpty;

/**
 * @author yh   耦合性太高  需要重新编写
 * @ClassName: OkHttpRequestManager
 * @Description: (对okhttp封装)
 * @date 2016-7-18 下午4:49:41
 */
public class OkHttpRequestManager implements I_RequestManager
{
    private static OkHttpRequestManager oKHTTPRequestManager;
    public static final int ERROR_4XX = 400;
    public static final int ERROR_5XX = 500;
    public static final int ERROR_OTHER = 1;
    public static final String ERROE_3001 = "30001";
    public static final String ERROE_3002 = "30002";
    public static final String ERROR_UNKNOWN = "unknown";

    private final static long readTimeOut = 10 * 1000L;
    private final static long writeTimeOut = 60 * 1000L;
    private final static long connTimeOut = 10 * 1000L;

    private OkHttpRequestManager()
    {
    }

    public static OkHttpRequestManager getInstance()
    {
        if (StringUtils.isEmpty(oKHTTPRequestManager))
        {
            oKHTTPRequestManager = new OkHttpRequestManager();
        }
        return oKHTTPRequestManager;
    }

    public void get(final String host, String suffix,
                    final HttpCallBack callback, Object tag)
    {
        get(host, suffix, callback, tag, connTimeOut, readTimeOut,
                writeTimeOut);
    }

    public void get(final String host, String suffix,
                    final HttpCallBack callback, final long connTimeOut,
                    final long readTimeOut, final long writeTimeOut, Object tag)
    {
        get(host, suffix, callback, tag, connTimeOut, readTimeOut,
                writeTimeOut);
    }

    public void get(final String host, final String suffix,
                    final HttpCallBack callback, final Object tag,
                    final long connTimeOut, final long readTimeOut,
                    final long writeTimeOut)
    {
        String url = host + suffix;
        LogUtils.e("GET请求host：", url + "   ");

        final String url1 = url;
        // 组成的URL 为空或者不是http或https开头都是非法URL
        if (isEmpty(url1)
                || (!url1.startsWith(Constants.FILE_HTTP) && !url1
                .startsWith(Constants.FILE_HTTPS)))
        {
            callback.onFailure(-3, "非法URL");
            callback.onFinish();
            return;
        }
        OkHttpUtils.get().url(url1).tag(tag).build().connTimeOut(connTimeOut)
                .readTimeOut(readTimeOut).writeTimeOut(writeTimeOut)
                .execute(new StringCallback()
                {

                    @Override
                    public void onResponse(String response, int id)
                    {
                        try
                        {
                            JSONObject json = new JSONObject(response);
                            String ret = json.getString(Constants.RESULT);
                            if (ERROE_3001.equals(ret)
                                    || ERROE_3002.equals(ret))
                            {
                                //相关操作
//								EventBus.getDefault().post(
//										new EventBusBean(new Intent(
//												AppConfig.ACTION_PLOGIN_OUT)));
//								EventBus.getDefault().post(
//										new EventBusBean(new Intent(
//												AppConfig.ACTION_AGAIN_TOKEN)));
                            } else
                            {
                                callback.onSuccess(response);
                                callback.onFinish();
                            }
                        }
                        catch (JSONException e)
                        {
                            callback.onSuccess(response);
                            callback.onFinish();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        LogUtils.e("OkHttpRequestManager", "GET请求URL：" + url1 + " 请求错误："
                                + e.getMessage() + "  " + id);
                        String error = e.getMessage();
                        if (!isEmpty(error))
                        {
                            if (error.startsWith("failed to connect to"))
                            {
                                callback.onFailure(-4, "连接超时");
                                callback.onFinish();
                            } else
                            {
                                dealError(e, callback);
                            }
                        }
                    }
                });
    }

    public void post(final String host, String suffix,
                     Map<String, String> params, final HttpCallBack callback,
                     final long connTimeOut, final long readTimeOut,
                     final long writeTimeOut, Object tag)
    {
        post(host, suffix, params, callback, tag, connTimeOut, readTimeOut,
                writeTimeOut);
    }

    public void post(final String host, String suffix,
                     Map<String, String> params, final HttpCallBack callback, Object tag)
    {
        post(host, suffix, params, callback, tag, connTimeOut, readTimeOut,
                writeTimeOut);
    }

    public void post(final String host, final String suffix,
                     final Map<String, String> params, final HttpCallBack callback,
                     final Object tag, final long connTimeOut, final long readTimeOut,
                     final long writeTimeOut)
    {


        final String url = host + suffix;
        LogUtils.e("post请求host：", url);
        // 组成的URL 为空或者不是http或https开头都是非法URL
        if (isEmpty(url)
                || (!url.startsWith(Constants.FILE_HTTP) && !url
                .startsWith(Constants.FILE_HTTPS)))
        {
            callback.onFailure(-3, "非法URL");
            callback.onFinish();
            return;
        }
        OkHttpUtils.post().url(url).tag(tag).params(params).build()
                .connTimeOut(connTimeOut).readTimeOut(readTimeOut)
                .writeTimeOut(writeTimeOut).execute(new StringCallback()
        {

            @Override
            public void onResponse(String response, int id)
            {
                try
                {
                    JSONObject json = new JSONObject(response);
                    String ret = json.getString(Constants.RESULT);
                    if (ERROE_3001.equals(ret)
                            || ERROE_3002.equals(ret))
                    {
                        //相关操作
//								EventBus.getDefault().post(
//									new EventBusBean(new Intent(
//											AppConfig.ACTION_PLOGIN_OUT)));
//								EventBus.getDefault().post(
//										new EventBusBean(new Intent(
//												AppConfig.ACTION_AGAIN_TOKEN)));
                    } else
                    {
                        callback.onSuccess(response);
                        callback.onFinish();
                    }
                }
                catch (JSONException e)
                {
                    callback.onSuccess(response);
                    callback.onFinish();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id)
            {
                // failed to connect to
                LogUtils.e("OkHttpRequestManager", "POST请求URL：" + url + " 请求错误："
                        + e.getMessage() + "  " + id);
                String error = e.getMessage();
                if (!isEmpty(error))
                {
                    if (error.startsWith("failed to connect to"))
                    {
                        callback.onFailure(-4, "连接超时");
                        callback.onFinish();
                    } else
                    {
                        dealError(e, callback);
                    }
                }
            }
        });
    }

    public void postForm(final String host, final String suffix,
                         final Map<String, Object> params, final HttpCallBack callback,
                         final Object tag)
    {
        postForm(host, suffix, params, callback, tag, connTimeOut, readTimeOut,
                writeTimeOut);
    }

    public void postForm(final String host, final String suffix,
                         final Map<String, Object> params, final HttpCallBack callback,
                         final long connTimeOut, final long readTimeOut,
                         final long writeTimeOut, final Object tag)
    {
        postForm(host, suffix, params, callback, tag, connTimeOut, readTimeOut,
                writeTimeOut);
    }

    public void postForm(final String host, final String suffix,
                         final Map<String, Object> params, final HttpCallBack callback,
                         final Object tag, final long connTimeOut, final long readTimeOut,
                         final long writeTimeOut)
    {


        final String url = host + suffix;
        LogUtils.e("postForm请求host：", url);

        // 组成的URL 为空或者不是http或https开头都是非法URL
        if (isEmpty(url)
                || (!url.startsWith(Constants.FILE_HTTP) && !url
                .startsWith(Constants.FILE_HTTPS)))
        {
            callback.onFailure(-3, "非法URL");
            callback.onFinish();
            return;
        }

        PostFormBuilder formbuilder = OkHttpUtils.post();
        formbuilder.url(url).tag(tag);
        Iterator<Entry<String, Object>> paramsIterator = params.entrySet()
                .iterator();
        while (paramsIterator.hasNext())
        {
            Entry<String, Object> entry = paramsIterator.next();
            String key = entry.getKey();// key
            Object obj = entry.getValue();// 值
            if (obj instanceof File)
            {
                // 如果是文件
                File newFile = (File) obj;
                formbuilder.addFile(key, newFile.getName(), newFile);
            } else if (obj instanceof String)
            {
                // 如果是字符
                formbuilder.addParams(key, (String) obj);
            }
            LogUtils.e("postForm参数集：", "key:" + key + "   Value：" + obj);
        }
        formbuilder.build().connTimeOut(connTimeOut).readTimeOut(readTimeOut)
                .writeTimeOut(writeTimeOut)//
                .execute(new StringCallback()
                {

                    @Override
                    public void onResponse(String response, int id)
                    {
                        try
                        {
                            JSONObject json = new JSONObject(response);
                            String ret = json.getString(Constants.RESULT);
                            if (ERROE_3001.equals(ret)
                                    || ERROE_3002.equals(ret))
                            {
                                //相关操作
//								EventBus.getDefault().post(
//										new EventBusBean(new Intent(
//												AppConfig.ACTION_PLOGIN_OUT)));
//								EventBus.getDefault().post(
//										new EventBusBean(new Intent(
//												AppConfig.ACTION_AGAIN_TOKEN)));
                            } else
                            {
                                callback.onSuccess(response);
                                callback.onFinish();
                            }
                        }
                        catch (JSONException e)
                        {
                            callback.onSuccess(response);
                            callback.onFinish();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        LogUtils.e("OkHttpRequestManager", "postForm请求URL：" + url
                                + " 请求错误：" + e.getMessage() + "  " + id);
                        dealError(e, callback);
                    }

                    @Override
                    public void onAfter(int id)
                    {
                        super.onAfter(id);
                    }
                });

    }

    private static void dealError(Exception e, HttpCallBack callback)
    {
        String error = e.getMessage();
        if (isEmpty(error))
        {
            error = ERROR_UNKNOWN;
        }
        if (!isEmpty(error) && StringUtils.isNumber(error))
        {
            if (Integer.parseInt(error) >= ERROR_4XX)
            {
                callback.onFailure(ERROR_4XX, error);
            } else if (Integer.parseInt(error) >= ERROR_5XX)
            {
                callback.onFailure(ERROR_5XX, error);
            } else
            {
                callback.onFailure(ERROR_OTHER, error);
            }
        } else
        {
            callback.onFailure(ERROR_OTHER, error);
        }
        callback.onFinish();
    }

    public static void cancel(Object tag)
    {
        if (!StringUtils.isEmpty(OkHttpUtils.getInstance()))
        {
            OkHttpUtils.getInstance().cancelTag(tag);
        }
    }

    public static void cancelAll()
    {
        if (!StringUtils.isEmpty(OkHttpUtils.getInstance()))
        {
            OkHttpUtils.getInstance().cancelTagAll();
        }
    }


    /**
     * @param @param url 下载地址
     * @param @param path 文件路径
     * @param @param fileName 文件名称
     * @param @param callback 回调
     * @param @param tag
     * @return void 返回类型
     * @throws
     * @Title: download
     * @Description: 下载文件
     * @author yh
     */
    public void download(String url, final String path, String fileName,
                         final HttpCallBack callback, final long connTimeOut,
                         final long readTimeOut, final long writeTimeOut, String tag)
    {
        LogUtils.e("GET请求视屏：", url + "   ");
        OkHttpUtils.get().tag(tag).url(url).build().connTimeOut(connTimeOut)
                .readTimeOut(readTimeOut).writeTimeOut(writeTimeOut)
                .execute(new FileCallBack(path, fileName)
                {
                    @Override
                    public void onBefore(Request request, int id)
                    {
                        callback.onBefore(request, id);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id)
                    {
                        callback.onLoading(progress, total, id);
                    }

                    @Override
                    public void onResponse(File response, int id)
                    {
                        callback.onSuccess(response, id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        dealError(e, callback);
                    }
                });
    }

    /**
     * @param @param url
     * @param @param path
     * @param @param fileName
     * @param @param callback
     * @param @param tag
     * @return void 返回类型
     * @throws
     * @Title: download
     * @Description: 下载
     * @author yh
     */
    public void download(String url, final String path, String fileName,
                         final HttpCallBack callback, String tag)
    {
        LogUtils.e("下载URL：", url + "   ");
        OkHttpUtils.get().tag(tag).url(url).build().connTimeOut(connTimeOut)
                .readTimeOut(readTimeOut).writeTimeOut(writeTimeOut)
                .execute(new FileCallBack(path, fileName)
                {
                    @Override
                    public void onBefore(Request request, int id)
                    {
                        callback.onBefore(request, id);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id)
                    {
                        callback.onLoading(progress, total, id);
                    }

                    @Override
                    public void onResponse(File response, int id)
                    {
                        callback.onSuccess(response, id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        dealError(e, callback);
                    }
                });
    }

}
