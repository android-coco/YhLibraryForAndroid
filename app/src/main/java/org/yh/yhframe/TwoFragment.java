package org.yh.yhframe;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yh.library.ui.BindView;
import org.yh.library.ui.I_PermissionListener;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.LogUtils;
import org.yh.yhframe.base.BaseFragment;

import java.util.List;

public class TwoFragment extends BaseFragment implements I_PermissionListener
{
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
                YHViewInject.create().showTips("授权开始");
                requestRunTimePermission(new String[]{Manifest.permission.CAMERA},this);
                break;
        }
    }


    @Override
    public void onSuccess()
    {
        YHViewInject.create().showTips("授权成功");
        //直接执行相应操作了
        outsideAty.showActivity(outsideAty, HTML5WebViewCustomAD.class);
    }

    @Override
    public void onGranted(List<String> grantedPermission)
    {

    }

    @Override
    public void onFailure(List<String> deniedPermission)
    {
        YHViewInject.create().showTips("您没有授权该权限，请在设置中打开授权");
    }
}
