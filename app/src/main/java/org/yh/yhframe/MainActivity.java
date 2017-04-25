package org.yh.yhframe;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import org.yh.library.YHActivity;
import org.yh.library.okhttp.YHOkHttp;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.utils.LogUtils;

public class MainActivity extends YHActivity
{

    private static final int REQUECT_CODE_SDCARD = 1;
    @BindView(id = R.id.menu, click = true)
    Button menu;

    @Override
    public void setRootView()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();

    }

    @Override
    public void initData()
    {
        super.initData();
        //网络请求简单操作
        YHOkHttp.get("http://192.168.0.5/", "", new HttpCallBack()
        {
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
        }, TAG);
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
    public void requestPermissionSuccess()
    {
        //直接执行相应操作了
        showActivity(aty, DemoActivity.class);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        if (requestCode == REQUECT_CODE_SDCARD)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                showActivity(aty, DemoActivity.class);
            }
            else
            {
                Toast.makeText(MainActivity.this, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
