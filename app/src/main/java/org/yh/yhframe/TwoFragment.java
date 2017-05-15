package org.yh.yhframe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yh.library.ui.BindView;
import org.yh.library.utils.LogUtils;
import org.yh.yhframe.utils.ToastUtils;
public class TwoFragment extends BaseFragment
{
    private static final int REQUECT_CODE_SDCARD = 1;
    @BindView(id = R.id.two_txt, click = true)
    TextView two_txt;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {

        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes)
    {
        actionBarRes.title = "Two";
        actionBarRes.mainImageId = R.mipmap.logo_white_210;
        actionBarRes.rightImageId = R.mipmap.icon_home_menu_more;
    }

    @Override
    public void onChange()
    {
        super.onChange();
        LogUtils.e(TAG, "TwoFragment onChange()");
    }

    @Override
    protected void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.two_txt:
                ToastUtils.showTips("授权开始");
                requestPermission(Manifest.permission.ACCESS_NETWORK_STATE, REQUECT_CODE_SDCARD);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        if (requestCode == REQUECT_CODE_SDCARD)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                ToastUtils.showTips("授权成功");
                outsideAty.showActivity(outsideAty, HTML5WebViewCustomAD.class);
            }
            else
            {
                ToastUtils.showTips( "您没有授权该权限，请在设置中打开授权");
            }
        }
    }

    @Override
    public void requestPermissionSuccess()
    {
        //直接执行相应操作了
        outsideAty.showActivity(outsideAty, HTML5WebViewCustomAD.class);
    }
}
