package org.yh.yhframe;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import org.yh.library.okhttp.YHOkHttp;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActiciy
{

    private static final int REQUECT_CODE_SDCARD = 1;
    @BindView(id = R.id.menu, click = true)
    Button menu;

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("主页");
        toolbar.setMainTitleDrawable(R.mipmap.logo_white_210);
        toolbar.setRightTitleDrawable(R.mipmap.icon_home_menu_more);
    }

    @Override
    public void initData()
    {
        super.initData();
        Map<String, String> params = new HashMap<>();
        params.put("user", "123456");
        params.put("pass", "123456");
        //网络请求简单操作
        YHOkHttp.post("http://192.168.0.3/Ci/api/Login/login", "", params, new HttpCallBack()
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
        showActivity(aty, DemoActivity.class);
    }

    @Override
    protected void onMenuClick()
    {
        super.onMenuClick();
        YHViewInject.create().getExitDialog(aty, "确定删除", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                YHViewInject.create().showTips("更多被点击");
            }
        });
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
