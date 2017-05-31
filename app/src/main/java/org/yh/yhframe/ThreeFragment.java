package org.yh.yhframe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yh.library.ui.BindView;
import org.yh.library.utils.LogUtils;
import org.yh.yhframe.base.BaseFragment;

public class ThreeFragment extends BaseFragment
{
    @BindView(id = R.id.three_txt, click = true)
    TextView three_txt;
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }


    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes)
    {
        actionBarRes.title = "Three";
        actionBarRes.mainImageId = R.mipmap.logo_white_210;
        actionBarRes.rightImageId = R.mipmap.icon_home_menu_more;
    }
    @Override
    public void onChange()
    {
        super.onChange();
        LogUtils.e(TAG,"ThreeFragment onChange()");
    }

    @Override
    protected void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.three_txt:
                outsideAty.showActivity(outsideAty,VideoActivity.class);
                break;
        }
    }

}
