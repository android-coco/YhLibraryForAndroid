package org.yh.yhframe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.LogUtils;
import org.yh.yhframe.base.BaseFragment;
import org.yh.yhframe.view.LineChartView;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends BaseFragment
{
    @BindView(id = R.id.one_text, click = true)
    TextView oneText;
    @BindView(id = R.id.lcv, click = true)
    private LineChartView mLineChartView;
    private List<LineChartView.ItemBean> mItems;
    private int[] shadeColors;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    protected void initData()
    {
        super.initData();
        //  初始化折线数据
        mItems = new ArrayList<>();
        mItems.add(new LineChartView.ItemBean(1111111111, 23));
        mItems.add(new LineChartView.ItemBean(1489593600, 88));
        mItems.add(new LineChartView.ItemBean(1489680000, 60));
        mItems.add(new LineChartView.ItemBean(1489766400, 50));
        mItems.add(new LineChartView.ItemBean(1489852800, 70));
        mItems.add(new LineChartView.ItemBean(1489939200, 10));
        mItems.add(new LineChartView.ItemBean(1490025600, 33));
        mItems.add(new LineChartView.ItemBean(1490112000, 44));
        mItems.add(new LineChartView.ItemBean(1490198400, 99));
        mItems.add(new LineChartView.ItemBean(1490284800, 17));

        shadeColors = new int[]{
                Color.argb(100, 255, 86, 86), Color.argb(15, 255, 86, 86),
                Color.argb(0, 255, 86, 86)};
    }

    @Override
    protected void initWidget(View parentView)
    {
        super.initWidget(parentView);
        //  设置折线数据
        mLineChartView.setItems(mItems);
        //  设置渐变颜色
        mLineChartView.setShadeColors(shadeColors);
        //  开启动画
        mLineChartView.startAnim(mLineChartView, 2000);
    }

    @Override
    protected void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.one_text:
                outsideAty.showActivity(outsideAty, ListRecycleActivity.class);
                break;
            case R.id.lcv:
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
