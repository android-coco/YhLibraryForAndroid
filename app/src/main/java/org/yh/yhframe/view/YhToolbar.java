package org.yh.yhframe.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import org.yh.library.ui.AnnotateUtil;
import org.yh.library.ui.BindView;
import org.yh.library.utils.DensityUtils;
import org.yh.yhframe.R;

/**
 * Created by yhlyl on 2017/5/11.
 */

public class YhToolbar extends Toolbar
{

    /**
     * 左侧Title
     */
    @BindView(id = R.id.txt_left_title)
    private TextView mTxtLeftTitle;
    /**
     * 中间Title
     */
    @BindView(id = R.id.txt_main_title)
    private TextView mTxtMiddleTitle;
    /**
     * 右侧Title
     */
    @BindView(id = R.id.txt_right_title)
    private TextView mTxtRightTitle;


    public YhToolbar(Context context)
    {
        super(context);
    }

    public YhToolbar(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public YhToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        //绑定控件
        AnnotateUtil.initBindView(this,getRootView());
//        mTxtLeftTitle = (TextView) findViewById(R.id.txt_left_title);
//        mTxtMiddleTitle = (TextView) findViewById(R.id.txt_main_title);
//        mTxtRightTitle = (TextView) findViewById(R.id.txt_right_title);
    }

    //设置中间title的内容
    public void setMainTitle(CharSequence text)
    {
        this.setTitle(" ");
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        mTxtMiddleTitle.setText(text);
    }



    //设置中间title的内容文字的颜色
    public void setMainTitleColor(int color)
    {
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        mTxtMiddleTitle.setTextColor(color);
    }

    //设置中间title图标
    public void setMainTitleDrawable(int res)
    {
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        Drawable dwMain = ContextCompat.getDrawable(getContext(), res);
        dwMain.setBounds(0, 0, DensityUtils.dip2px(getContext(),32), DensityUtils.dip2px(getContext(),32));
        mTxtMiddleTitle.setCompoundDrawables(dwMain, null, null, null);
    }

    //设置中间title图标
    public void setMainTitleDrawable(Drawable dwMain)
    {
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        dwMain.setBounds(0, 0,DensityUtils.dip2px(getContext(),32), DensityUtils.dip2px(getContext(),32));
        mTxtMiddleTitle.setCompoundDrawables(dwMain, null, null, null);
    }

    //设置title左边文字
    public void setLeftTitleText(CharSequence text)
    {
        mTxtLeftTitle.setVisibility(View.VISIBLE);
        mTxtLeftTitle.setText(text);
    }

    //设置title左边文字颜色
    public void setLeftTitleColor(int color)
    {
        mTxtLeftTitle.setVisibility(View.VISIBLE);
        mTxtLeftTitle.setTextColor(color);
    }

    //设置title左边图标
    public void setLeftTitleDrawable(int res)
    {
        mTxtLeftTitle.setVisibility(View.VISIBLE);
        Drawable dwLeft = ContextCompat.getDrawable(getContext(), res);
        //dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight()
        dwLeft.setBounds(0, 0, DensityUtils.dip2px(getContext(),32), DensityUtils.dip2px(getContext(),32));
        mTxtLeftTitle.setCompoundDrawables(dwLeft, null, null, null);
    }

    //设置title左边图标
    public void setLeftTitleDrawable(Drawable dwLeft)
    {
        mTxtLeftTitle.setVisibility(View.VISIBLE);
        dwLeft.setBounds(0, 0, DensityUtils.dip2px(getContext(),32), DensityUtils.dip2px(getContext(),32));
        mTxtLeftTitle.setCompoundDrawables(dwLeft, null, null, null);
    }

    //设置title左边点击事件
    public void setLeftTitleClickListener(OnClickListener onClickListener)
    {
        mTxtLeftTitle.setOnClickListener(onClickListener);
    }

    //设置title右边文字
    public void setRightTitleText(CharSequence text)
    {
        mTxtRightTitle.setVisibility(View.VISIBLE);
        mTxtRightTitle.setText(text);
    }

    //设置title右边文字颜色
    public void setRightTitleColor(int color)
    {
        mTxtRightTitle.setVisibility(View.VISIBLE);
        mTxtRightTitle.setTextColor(color);
    }

    //设置title右边图标
    public void setRightTitleDrawable(int res)
    {
        mTxtRightTitle.setVisibility(View.VISIBLE);
        Drawable dwRight = ContextCompat.getDrawable(getContext(), res);
        dwRight.setBounds(0, 0, DensityUtils.dip2px(getContext(),32), DensityUtils.dip2px(getContext(),32));
        mTxtRightTitle.setCompoundDrawables(null, null, dwRight, null);
    }

    //设置title右边图标
    public void setRightTitleDrawable(Drawable dwRight)
    {
        mTxtRightTitle.setVisibility(View.VISIBLE);
        dwRight.setBounds(0, 0, DensityUtils.dip2px(getContext(),32), DensityUtils.dip2px(getContext(),32));
        mTxtRightTitle.setCompoundDrawables(null, null, dwRight, null);
    }

    //设置title右边点击事件
    public void setRightTitleClickListener(OnClickListener onClickListener)
    {
        mTxtRightTitle.setOnClickListener(onClickListener);
    }

}
