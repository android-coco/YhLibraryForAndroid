package org.yh.yhframe;

import android.os.Bundle;
import android.widget.TextView;

import org.yh.library.YHActivity;

/**
 * Created by yhlyl on 2017/4/25.
 */

public abstract class BaseActiciy extends YHActivity
{
    public TextView mTvTitle;//标题

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
            mTvTitle = (TextView) findViewById(R.id.toolbar_title);
            //mTvTitle.setText("jjjjj");
        }
        catch (NullPointerException e)
        {
            throw new NullPointerException(
                    "TitleBar Notfound from Activity layout");
        }
    }

}
