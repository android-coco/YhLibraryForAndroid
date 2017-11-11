package org.yh.yhframe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yh.library.ui.BindView;
import org.yh.library.utils.LogUtils;
import org.yh.yhframe.base.BaseFragment;
/**
 * 作者：38314 on 2017/7/12 13:53
 * 邮箱：yh_android@163.com
 */
public class FourFragment extends BaseFragment
{
    @BindView(id = R.id.four_txt, click = true)
    TextView four_txt;
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        return inflater.inflate(R.layout.fragment_four, container, false);
    }


    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes)
    {
        actionBarRes.title = "Four";
        actionBarRes.mainImageId = R.mipmap.logo_white_210;
        actionBarRes.rightImageId = R.mipmap.icon_home_menu_more;
    }

    @Override
    public void onChange()
    {
        super.onChange();
        LogUtils.e(TAG, "FourFragment onChange()");
    }

    @Override
    protected void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.four_txt:
                outsideAty.showActivity(outsideAty, ConstraintActivity.class);
                break;
        }
    }
}
