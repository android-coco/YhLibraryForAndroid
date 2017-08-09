package org.yh.library.view.loading;

import org.yh.library.view.loading.factory.I_DialogFactory;
import org.yh.library.view.loading.factory.I_LoadingFactory;
import org.yh.library.view.loading.factory.MaterialDialogFactory;
import org.yh.library.view.loading.factory.MaterialFactory;

/**
 * Created by yhlyl on 2017/5/31.
 * 配置文件
 */
@SuppressWarnings("all")
public class YHLoadingConfig
{
    private final static I_LoadingFactory DEFAULT_LOADING_FACTORY = new MaterialFactory();
    private final static I_DialogFactory DEFAULT_DIALOG_FACTORY = new MaterialDialogFactory();

    private static I_LoadingFactory mLoadingFactory = DEFAULT_LOADING_FACTORY;
    private static I_DialogFactory mDialogFactory = DEFAULT_DIALOG_FACTORY;

    /**
     * 全局配置
     * <p>在程序入口调用</p>
     *
     * @param loadingFactory
     * @param dialogFactory
     */
    public static void setFactory(I_LoadingFactory loadingFactory, I_DialogFactory dialogFactory)
    {
        if (loadingFactory != null)
        {
            mLoadingFactory = loadingFactory;
        }
        if (dialogFactory != null)
        {
            mDialogFactory = dialogFactory;
        }
    }


    public static void defaultFactory()
    {
        setFactory(new MaterialFactory(), new MaterialDialogFactory());
    }


    public static I_LoadingFactory getLoadingFactory()
    {
        return mLoadingFactory;
    }

    public static I_DialogFactory getDialogFactory()
    {
        return mDialogFactory;
    }
}
