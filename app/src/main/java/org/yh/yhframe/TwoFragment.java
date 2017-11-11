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
import org.yh.library.utils.Constants;
import org.yh.library.utils.LogUtils;
import org.yh.library.view.YHLabelsView;
import org.yh.yhframe.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends BaseFragment
{
    @BindView(id = R.id.two_txt, click = true)
    TextView two_txt;
    @BindView(id = R.id.labels, click = true)
    private YHLabelsView labelsView;
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
    protected void initWidget(View parentView)
    {
        super.initWidget(parentView);
        //测试的数据
        ArrayList<String> label = new ArrayList<>();
        label.add("周一");
        label.add("周二");
        label.add("周三");
        label.add("周四");
        label.add("周五");
        label.add("周六");
        label.add("周日");
        labelsView.setLabels(label);
        labelsView.setSelectType(YHLabelsView.SelectType.MULTI);
        labelsView.setMaxSelect(0);
        labelsView.setOnLabelClickListener(new YHLabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(View label, String labelText, int position) {
                YHViewInject.create().showTips(position + " : " + labelText);
                LogUtils.e(TAG,labelsView.getSelectLabels());
            }
        });
    }

    @Override
    protected void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.two_txt:
                requestRunTimePermission(new String[]{Manifest.permission.CAMERA}, new I_PermissionListener()
                {
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
                        YHViewInject.create().showTips("您没有授权" + Constants.initPermissionNames().get(deniedPermission.get(0)) + "请在设置中打开授权");
                    }
                });
                break;
        }
    }


}
