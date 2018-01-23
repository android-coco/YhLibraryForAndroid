package org.yh.yhframe;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;

import org.yh.library.YHGlide;
import org.yh.library.ui.BindView;
import org.yh.library.ui.I_PermissionListener;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.Constants;
import org.yh.library.utils.FileUtils;
import org.yh.library.view.YHImageViewRoundOval;
import org.yh.yhframe.app.MyApplication;
import org.yh.yhframe.base.BaseActiciy;
import org.yh.yhframe.service.MyIntentService;
import org.yh.yhframe.service.MyService;

import java.util.List;


public class MainActivity extends BaseActiciy
{

    Intent serviceIntent;
    private MyService.DownloadBinder downloadBinder;
    Intent myIntentService;
    @BindView(id = R.id.img)
    private ImageView img;
    @BindView(id = R.id.img1)
    private YHImageViewRoundOval img1;
    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_main);
        bindView(R.id.menu, true);
        bindView(R.id.start_recording, true);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("主页");
        toolbar.setMainTitleDrawable(R.mipmap.logo_white_210);
        toolbar.setRightTitleDrawable(R.mipmap.icon_home_menu_more);
        //startService(serviceIntent);
        //bindService(serviceIntent,connection,BIND_AUTO_CREATE);
        //LogUtils.e(TAG, Thread.currentThread().getId());
        //startService(myIntentService);
        //YHGlide.getInstanse(this).loadImgeForDrawable(R.mipmap.ic_launcher,img);
        YHGlide.getInstanse(MyApplication.getInstance()).loadImgeForUrl("http://beeshomeimg.test.upcdn.net/mms/user/headImg/pj4xkvup9ijcxhqekoj9xqf3acn6c9n8.jpg", img);
//        public static final int TYPE_CIRCLE = 0;// 圆形
//        public static final int TYPE_ROUND = 1;// 圆角矩形
//        public static final int TYPE_OVAL = 2;//椭圆形
        img1.setType(YHImageViewRoundOval.TYPE_ROUND);
        img1.setRoundRadius(20);//矩形凹行大小
    }

    private ServiceConnection connection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            downloadBinder = (MyService.DownloadBinder) iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {

        }
    };

    @Override
    public void initData()
    {
        super.initData();
        //网络请求简单操作
        serviceIntent = new Intent(aty, MyService.class);
        myIntentService = new Intent(aty, MyIntentService.class);
//        Map<String, String> params = new HashMap<>();
//
//        params.put("type", "7");
//        params.put("nurseid", "123456");
//        params.put("patientid", "123123");
//        params.put("date", "2017-07-14 16:42:10");
//        params.put("v1", "1");
//        params.put("v2", "2");
//        params.put("v3", "3");
//        params.put("v4", "4");
//        params.put("v5", "5");
//        params.put("v6", "6");
//        params.put("v7", "7");
//        params.put("v8", "8");
//        params.put("v9", "9");
//        params.put("v10", "10");
//        params.put("v11", "11");
////        params.put("file",new File(FileUtils.getSavePath("Download/SAM_0034.JPG")));
//        YHRequestFactory.getRequestManger().post(HOME_HOST, "api/Expost/testPost", null, params,
//                new HttpCallBack()
//
//        {
//            @Override
//            public void onSuccess(String t)
//            {
//                super.onSuccess(t);
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg)
//            {
//                super.onFailure(errorNo, strMsg);
//            }
//
//            @Override
//            public void onFinish()
//            {
//                super.onFinish();
//                LogUtils.e(TAG, new SharedPrefsCookiePersistor(MyApplication.getInstance())
//                        .loadAll() + "");
//            }
//        }, TAG);
//        Map<String, String> headers = new LinkedHashMap<>();
//        headers.put("imei", "8989898989898989");
//        headers.put("version", "1.0");
//        headers.put("token", CipherUtils.md5(CipherUtils.md5("1.0") + CipherUtils.md5
//                ("8989898989898989") + "hunli20170807@@$*"));
//        headers.put("regid", "123123123");
//        Map<String, String> parms1 = new HashMap<>();
//        parms1.put("mobile", "188888888");
//        parms1.put("type", "auth");
//        YHRequestFactory.getRequestManger().post("http://hunli.ywkjsz.com/Home/User/sendVerify",
//                "", headers, parms1, new HttpCallBack()
//
//        {
//
//
//            @Override
//            public void onSuccess(String t)
//            {
//                super.onSuccess(t);
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg)
//            {
//                super.onFailure(errorNo, strMsg);
//            }
//
//            @Override
//            public void onFinish()
//            {
//                super.onFinish();
//            }
//        }, TAG);

//        //请求头测试
//        /**
//         *  .addHeader("imei", "123123123")
//         .addHeader("version", "1.0")
//         .addHeader("token", "")
//         .addHeader("regid", "123123123")
//         */
//        Map<String, String> headers = new LinkedHashMap<>();
//        headers.put("imei", "123123123");
//        headers.put("version", "1.1");
//        headers.put("token", "");
//        headers.put("regid", "123123123");
////        YHRequestFactory.getRequestManger().setHeaders(headers);
//        YHRequestFactory.getRequestManger().get("",
// "http://192.168.0.130:8081/api/login/login?user=123456&pass=123456", headers, new HttpCallBack()
//        {
//
//            @Override
//            public void onSuccess(String t)
//            {
//                super.onSuccess(t);
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg)
//            {
//                super.onFailure(errorNo, strMsg);
//            }
//        }, TAG);

//        YHRequestFactory.getRequestManger().get("", "http://116.196.82.249:8181/login?name=youhao&pass=123456", null, new HttpCallBack()
//        {
//            @Override
//            public void onSuccess(String t)
//            {
//                super.onSuccess(t);
//                JsonLoginModel jsonMenuModel = JsonUitl.stringToTObject(t, JsonLoginModel.class);
//                LogUtils.e(TAG, jsonMenuModel + "");
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg)
//            {
//                super.onFailure(errorNo, strMsg);
//                LogUtils.e(TAG, strMsg + errorNo);
//            }
//
//            @Override
//            public void onFinish()
//            {
//                super.onFinish();
//                LogUtils.e(TAG, "onFinish()");
//            }
//        }, TAG);


//        Map<String, String> parms1 = new HashMap<>();
//        parms1.put("code", "admin");
//        parms1.put("password", "7c4a8d09ca3762af61e59520943dc26494f8941b");
//        YHRequestFactory.getRequestManger().post("", "http://192.168.0.129:8181/login", null, parms1, new HttpCallBack()
//        {
//            @Override
//            public void onSuccess(String t)
//            {
//                super.onSuccess(t);
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg)
//            {
//                super.onFailure(errorNo, strMsg);
//                LogUtils.e(TAG, strMsg + errorNo);
//            }
//
//            @Override
//            public void onFinish()
//            {
//                super.onFinish();
//                LogUtils.e(TAG, "onFinish()");
//            }
//        }, TAG+"1");


//        YHRequestFactory.getRequestManger().postString("", "http://115.159.123.101:8085/interface/terminal_profile", null, "{\"sns\":\"123456789012345\"}", new HttpCallBack()
//        {
//            @Override
//            public void onSuccess(String t)
//            {
//                super.onSuccess(t);
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg)
//            {
//                super.onFailure(errorNo, strMsg);
//            }
//
//            @Override
//            public void onFinish()
//            {
//                super.onFinish();
//            }
//        },TAG);
//        YHRequestFactory.getRequestManger().get("", "http://192.168.0.129:8181/patient/info?patient_id=802040&department_id=125", null, new HttpCallBack()
//        {
//            @Override
//            public void onSuccess(String t)
//            {
//                super.onSuccess(t);
//                LogUtils.e(TAG,t);
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg)
//            {
//                super.onFailure(errorNo, strMsg);
//                LogUtils.e(TAG,errorNo + " " + strMsg);
//            }
//
//            @Override
//            public void onFinish()
//            {
//                super.onFinish();
//                LogUtils.e(TAG,"onFinish()");
//            }
//        },TAG);
    }

    public String convert(String utfString)
    {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = utfString.indexOf("\\u", pos)) != -1)
        {
            sb.append(utfString.substring(pos, i));
            if (i + 5 < utfString.length())
            {
                pos = i + 6;
                sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
            }
        }

        return sb.toString();
    }

    @Override
    public void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.menu:
                requestRunTimePermission(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, new I_PermissionListener()
                {
                    @Override
                    public void onSuccess()//所有权限OK
                    {
                        //直接执行相应操作了
                        showActivity(aty, DemoActivity.class);
                        Constants.Config.IS_WRITE_EXTERNAL_STORAGE = true;
                    }

                    @Override
                    public void onGranted(List<String> grantedPermission)//部分权限OK
                    {
                    }

                    @Override
                    public void onFailure(List<String> deniedPermission)//全部拒绝
                    {
                        Constants.Config.IS_WRITE_EXTERNAL_STORAGE = false;
                        YHViewInject.create().showTips("拒绝授权列表：" + Constants.initPermissionNames
                                ().get(deniedPermission.get(0)));
                    }
                });
                break;
            case R.id.start_recording:
                Intent video = new Intent(this, VideoActivity.class);
                video.putExtra(VideoActivity.VIDEO_PATH, FileUtils.getSDCardPath() + "/movie.mp4");
                video.putExtra(VideoActivity.IMG_PATH, FileUtils.getSDCardPath() +
                        "/Pictures/ScreenShots/SRC_20170711_223947.png");
                startActivity(video);
                break;
        }
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        //stopService(serviceIntent);
        //unbindService(connection);
        //stopService(myIntentService);
        showActivity(aty, DemoActivity.class);
    }


    @Override
    protected void onMenuClick()
    {
        super.onMenuClick();
        YHViewInject.create().getExitDialog(aty, "确定删除", null, null, new DialogInterface
                .OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                YHViewInject.create().showTips("更多被点击");
            }
        });
    }


}
