package org.yh.library.view.loading.bar;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import org.yh.library.view.loading.YHLoadingConfig;
import org.yh.library.view.loading.factory.I_LoadingFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yhlyl on 2017/5/31.
 * 加载进度
 * <p>可用于FrameLayout、RelativeLayout、DrawerLayout、CoordinatorLayout、CardView<p>
 */

public final class YHLoadingBar implements I_LoadingBar
{
    private static final Map<View, YHLoadingBar> LOADINGBARS = new HashMap<>();
    private static int LOADING_LIMIT = 15;//默认15,超过会检查资源释放

    private ViewGroup mParent;
    private View mView;
    private I_LoadingBarListener mListener;

    private YHLoadingBar(ViewGroup parent, I_LoadingFactory factory)
    {
        mParent = parent;
        mView = factory.onCreateView(mParent);
    }


    /**
     * 显示loading
     */
    @Override
    public void show()
    {
        if (mView != null)
        {
            mView.setVisibility(View.VISIBLE);
            if (mView.getParent() != null)
            {
                mParent.removeView(mView);
            }
            mParent.addView(mView);
        }
    }

    /**
     * 取消loading
     */
    @Override
    public void cancel()
    {
        if (mView != null)
        {
            mView.setVisibility(View.GONE);
            mParent.removeView(mView);
            mView = null;
            if (this.mListener != null)
            {
                this.mListener.onCancel(mParent);
            }
        }
    }

    public YHLoadingBar setOnClickListener(View.OnClickListener listener)
    {
        if (mView != null)
        {
            mView.setOnClickListener(listener);
        }
        return this;
    }

    public YHLoadingBar setOnLoadingBarListener(I_LoadingBarListener mListener)
    {
        this.mListener = mListener;
        return this;
    }

    public static YHLoadingBar make(View parent)
    {
        return make(parent, YHLoadingConfig.getLoadingFactory());
    }

    public static YHLoadingBar make(View parent, I_LoadingFactory factory)
    {
        //如果已经有Loading在显示了
        if (LOADINGBARS.containsKey(parent))
        {
            YHLoadingBar loadingBar = LOADINGBARS.get(parent);
            loadingBar.mParent.removeView(loadingBar.mView);
        }
        YHLoadingBar newLoadingBar = new YHLoadingBar(findSuitableParent(parent), factory);
        LOADINGBARS.put(parent, newLoadingBar);
        return newLoadingBar;
    }


    public static YHLoadingBar make(View parent, final View loadingView)
    {
        return make(parent, new I_LoadingFactory()
        {
            @Override
            public View onCreateView(ViewGroup parent)
            {
                return loadingView;
            }
        });
    }

    /**
     * 根据父节点取消单个loading
     *
     * @param parent show传过来的父节点
     */
    public static void cancel(View parent)
    {
        YHLoadingBar loadingBar = LOADINGBARS.get(parent);
        if (loadingBar != null)
        {
            loadingBar.cancel();
        }
        LOADINGBARS.remove(parent);
    }

    /**
     * 取消所有loading
     */
    private static void cancelAll()
    {
        Iterator<Map.Entry<View, YHLoadingBar>> it = LOADINGBARS.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<View, YHLoadingBar> entry = it.next();
            cancel(entry.getKey());
        }
    }


    public static void release()
    {
        release(LOADING_LIMIT);
    }

    /**
     * 释放无用的资源
     * <p>可在BaseActivity onDestroy调用</p>
     *
     * @param limit loading池的限制,超过数量才检查资源释放
     */
    public static void release(int limit)
    {
        if (limit <= 0)
        {
            limit = LOADING_LIMIT;
        }
        if (LOADINGBARS.size() < limit)
        {
            return;
        }
        Log.d("LoadingBar", "release before loading size - " + LOADINGBARS.size());
        Iterator<Map.Entry<View, YHLoadingBar>> it = LOADINGBARS.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<View, YHLoadingBar> entry = it.next();
            Context context = entry.getKey().getContext();
            if (context instanceof Activity && ((Activity) context).isFinishing())
            {
                it.remove();
            }
        }
        Log.d("LoadingBar", "release after loading size - " + LOADINGBARS.size());
    }

    /**
     * 找到合适的父布局
     *
     * @param parent  布局
     * @return  父布局
     */
    private static ViewGroup findSuitableParent(View parent)
    {
        if (parent == null)
        {
            return null;
        }
        View suitableParent = parent;
        do
        {
            if (suitableParent instanceof FrameLayout || suitableParent instanceof RelativeLayout ||
                    "android.support.v4.widget.DrawerLayout".equals(suitableParent.getClass()
                            .getName()) ||
                    "android.support.design.widget.CoordinatorLayout".equals(suitableParent
                            .getClass().getName()) ||
                    "android.support.v7.widget.CardView".equals(suitableParent.getClass().getName
                            ()))
            {
                return (ViewGroup) suitableParent;
            }
            else
            {
                final ViewParent viewParent = suitableParent.getParent();
                suitableParent = viewParent instanceof View ? (View) viewParent : null;
                return (ViewGroup) suitableParent;
            }
        }
        while (suitableParent != null);
    }
}
