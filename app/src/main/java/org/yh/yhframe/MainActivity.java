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
import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.ui.I_PermissionListener;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.Constants;
import org.yh.library.utils.FileUtils;
import org.yh.yhframe.app.MyApplication;
import org.yh.yhframe.base.BaseActiciy;
import org.yh.yhframe.service.MyIntentService;
import org.yh.yhframe.service.MyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.yh.yhframe.app.MyApplication.HOME_HOST;


public class MainActivity extends BaseActiciy
{

    Intent serviceIntent;
    private MyService.DownloadBinder downloadBinder;
    Intent myIntentService;
    @BindView(id = R.id.img)
    private ImageView img;

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

        YHGlide.getInstanse(MyApplication.getInstance()).loadImgeForUrl("http://image.51efan.com/storage/menu/201705/ca07a8ca27b3beae828b871d888cd88f.jpg",img);
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
        Map<String, Object> params = new HashMap<>();

        params.put("type", "7");
        params.put("nurseid", "123456");
        params.put("patientid", "123123");
        params.put("date", "2017-07-14 16:42:10");
        params.put("v1", "1");
        params.put("v2", "2");
        params.put("v3", "3");
        params.put("v4", "4");
        params.put("v5", "5");
        params.put("v6", "6");
        params.put("v7", "7");
        params.put("v8", "8");
        params.put("v9", "9");
        params.put("v10", "10");
        params.put("v11", "11");
//        params.put("file",new File(FileUtils.getSavePath("Download/SAM_0034.JPG")));
        YHRequestFactory.getRequestManger().postForm(HOME_HOST, "ci/api/Expost/testPost", null, params, new HttpCallBack()

        {
            @Override
            public void onSuccess(String t)
            {
                super.onSuccess(t);
            }

            @Override
            public void onFailure(int errorNo, String strMsg)
            {
                super.onFailure(errorNo, strMsg);
            }

            @Override
            public void onFinish()
            {
                super.onFinish();
            }
        },TAG);

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
//        YHRequestFactory.getRequestManger().get("", "http://192.168.0.130:8081/api/login/login?user=123456&pass=123456", headers, new HttpCallBack()
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
                        YHViewInject.create().showTips("拒绝授权列表：" + Constants.initPermissionNames().get(deniedPermission.get(0)));
                    }
                });
                break;
            case R.id.start_recording:
                Intent video = new Intent(this, VideoActivity.class);
                video.putExtra(VideoActivity.VIDEO_PATH, FileUtils.getSDCardPath() + "/movie.mp4");
                video.putExtra(VideoActivity.IMG_PATH, FileUtils.getSDCardPath() + "/Pictures/ScreenShots/SRC_20170711_223947.png");
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
        YHViewInject.create().getExitDialog(aty, "确定删除", null, null, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                YHViewInject.create().showTips("更多被点击");
            }
        });
    }


}
