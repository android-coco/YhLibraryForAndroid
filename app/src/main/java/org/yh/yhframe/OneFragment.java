package org.yh.yhframe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.yh.library.utils.LogUtils;

public class OneFragment extends BaseFragment
{

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }


    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes)
    {
        actionBarRes.title = "One";
        actionBarRes.mainImageId = R.mipmap.logo_white_210;
        actionBarRes.rightImageId = R.mipmap.icon_home_menu_more;
    }

    @Override
    public void onChange()
    {
        super.onChange();
        LogUtils.e(TAG,"OneFragment onChange()");
    }


    @Override
    protected void onMenuClick()
    {
        super.onMenuClick();
        Toast.makeText(outsideAty, "菜单键", Toast.LENGTH_SHORT).show();
    }
}
