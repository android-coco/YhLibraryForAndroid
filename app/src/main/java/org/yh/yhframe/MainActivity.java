package org.yh.yhframe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.yh.library.okhttp.YHOkHttp;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.utils.JsonUitl;
import org.yh.library.utils.LogUtils;

import java.util.HashMap;
import java.util.List;
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
        YHOkHttp.post("http://192.168.0.197:8080/Ci/api/Login/login", "", params, new HttpCallBack()
        {
            @Override
            public void onSuccess(String t)
            {
                super.onSuccess(t);
                LogUtils.e(TAG, t);
                //User user = (User)JsonUitl.stringToObject(t,User.class);
                //LogUtils.e(TAG, user.getResult());
                Gson gson = new Gson();
//                Map<String, Object> retMap = gson.fromJson(t,
//                        new TypeToken<Map<String, List<Object>>>()
//                        {
//                        }.getType());
                Map<String, Object> retMap = JsonUitl.stringToMap(t);
                for (String key : retMap.keySet())
                {
                    LogUtils.e(TAG, "key:" + key + " values:" + retMap.get(key));
                    if (key.equals("students"))
                    {
                        List<Student> stuList = (List<Student>) retMap.get(key);
                        LogUtils.e(TAG, stuList);
                    } else if (key.equals("teachers"))
                    {
                        List<Teacher> tchrList = (List<Teacher>) retMap.get(key);
                        LogUtils.e(TAG, tchrList);
                    }
                }
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
        Toast.makeText(MainActivity.this, "更多被点击", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                showActivity(aty, DemoActivity.class);
            } else
            {
                Toast.makeText(MainActivity.this, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
