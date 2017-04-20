package org.yh.yhframe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.yh.library.YHFragment;
import org.yh.library.utils.LogUtils;

public class DemoFragment extends YHFragment
{

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        LogUtils.e(TAG,"inflaterView()");
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }


    @Override
    public void onChange()
    {
        super.onChange();
        LogUtils.e(TAG,"onChange()");
    }
}
