package org.yh.library.view.loading.factory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.yh.library.R;

/**
 * Created by yhlyl on 2017/5/31.
 * Material默认loading样式
 */

public class MaterialFactory implements I_LoadingFactory
{
    @Override
    public View onCreateView(ViewGroup parent)
    {
        View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .loading_progressbar_vertical_material, parent, false);
        return loadingView;
    }
}
