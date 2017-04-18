package org.yh.yhframe;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.yh.library.YHActivity;
import org.yh.library.okhttp.YHOkHttp;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.utils.LogUtils;

public class MainActivity extends YHActivity
{

    private static final int REQUECT_CODE_SDCARD = 1;
    @BindView(id = R.id.menu,click = true)
    public Button menu;
    @Override
    public void setRootView()
    {
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
        MPermissions.requestPermissions(MainActivity.this, REQUECT_CODE_SDCARD, Manifest
                .permission.WRITE_EXTERNAL_STORAGE);
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
                showActivity(null,DemoActivity.class);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestCallPhoneSuccess()
    {
        //Toast.makeText(this, "请打开写权限!", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE_SDCARD)
    public void requestSdcardFailed()
    {
        Toast.makeText(this, "请打开写权限!", Toast.LENGTH_SHORT).show();
    }
}
