package org.yh.yhframe;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.yh.library.YHActivity;

/**
 * Created by yhlyl on 2017/4/25.
 */

public abstract class BaseActiciy extends YHActivity
{
    public TextView mTvTitle;//标题
    public Toolbar toolbar;//标题栏

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        try
        {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("");//设置左边标题为空
            setSupportActionBar(toolbar);
            mTvTitle = (TextView) findViewById(R.id.toolbar_title);
        }
        catch (NullPointerException e)
        {
            throw new NullPointerException(
                    "TitleBar Notfound from Activity layout");
        }
    }

}
