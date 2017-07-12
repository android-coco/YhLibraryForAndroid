package org.yh.yhframe;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;

import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.LogUtils;
import org.yh.library.view.webview.YHWebView;

/**
 * 作者：38314 on 2017/7/12 13:53
 * 邮箱：yh_android@163.com
 */
public class AndroidInterface
{
    private YHWebView agent;
    private Context context;

    public AndroidInterface(YHWebView agent, Context context)
    {
        this.agent = agent;
        this.context = context;
    }

    private Handler deliver = new Handler(Looper.getMainLooper());

    @JavascriptInterface
    public void callAndroid(final String msg)
    {
        deliver.post(new Runnable()
        {
            @Override
            public void run()
            {

                LogUtils.e("Info", "main Thread:" + Thread.currentThread());
                YHViewInject.create().showTips("" + msg);
            }
        });
        LogUtils.e("Info", "Thread:" + Thread.currentThread());
        //对外接口
    }
}
