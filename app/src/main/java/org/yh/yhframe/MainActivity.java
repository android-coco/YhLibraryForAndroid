package org.yh.yhframe;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.view.View;

import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.LogUtils;
import org.yh.yhframe.base.BaseActiciy;
import org.yh.yhframe.service.MyIntentService;
import org.yh.yhframe.service.MyService;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActiciy
{

    private static final int REQUECT_CODE_SDCARD = 1;
    Intent serviceIntent;
    private MyService.DownloadBinder downloadBinder;
    Intent myIntentService;

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        bindView(R.id.menu, true);
        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("主页");
        toolbar.setMainTitleDrawable(R.mipmap.logo_white_210);
        toolbar.setRightTitleDrawable(R.mipmap.icon_home_menu_more);
        //startService(serviceIntent);
        //bindService(serviceIntent,connection,BIND_AUTO_CREATE);
        LogUtils.e(TAG, Thread.currentThread().getId());
        //startService(myIntentService);
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
        serviceIntent = new Intent(aty, MyService.class);
        myIntentService = new Intent(aty, MyIntentService.class);
        Map<String, String> params = new HashMap<>();
        params.put("user", "123456");
        params.put("pass", "123456");
        //网络请求简单操作
        YHRequestFactory.getRequestManger().post("http://121.201.35.230:8806/suitactivity/bindActivityBySuitCode",
                "", params, new HttpCallBack()
                {
                    @Override
                    public void onSuccess(String t)
                    {
                        super.onSuccess(t);
                        LogUtils.e(TAG, t);
                        //User user = (User)JsonUitl.stringToObject(t,User.class);
                        //LogUtils.e(TAG, user.getResult());
                    }

                    @Override
                    public void onFailure(int errorNo, String strMsg)
                    {
                        super.onFailure(errorNo, strMsg);
                        LogUtils.e(TAG, strMsg);
                    }
                }, TAG);

        YHRequestFactory.getRequestManger().get("http://211.149.215.12:8081/articleInterface/article/getRegCode?userName=15626590280", "", new HttpCallBack()
        {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t)
            {
                super.onSuccess(headers, t);
                LogUtils.e(TAG, headers + new String(t));
            }

            @Override
            public void onSuccess(String t)
            {
                super.onSuccess(t);
                LogUtils.e(TAG, t);
            }

            @Override
            public void onFailure(int errorNo, String strMsg)
            {
                super.onFailure(errorNo, strMsg);
                LogUtils.e(TAG, strMsg);
            }
        },TAG);
    }

    @Override
    public void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.menu:
                requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUECT_CODE_SDCARD);
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
    public void requestPermissionSuccess()
    {
        //直接执行相应操作了
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        if (requestCode == REQUECT_CODE_SDCARD)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                YHViewInject.create().showTips("授权成功");
                showActivity(aty, DemoActivity.class);
            } else
            {
                YHViewInject.create().showTips("您没有授权该权限，请在设置中打开授权");
            }
        }
    }

}
