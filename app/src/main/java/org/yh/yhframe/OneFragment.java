package org.yh.yhframe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.LogUtils;

public class OneFragment extends BaseFragment
{
    @BindView(id = R.id.one_text, click = true)
    TextView oneText;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    protected void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.one_text:
                outsideAty.showActivity(outsideAty, YHRecyclerviewActivity.class);
                break;
        }
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
        LogUtils.e(TAG, "OneFragment onChange()");
    }


    @Override
    protected void onMenuClick()
    {
        super.onMenuClick();
        YHViewInject.create().showTips("菜单键");
    }
}
