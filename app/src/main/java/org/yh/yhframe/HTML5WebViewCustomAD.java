package org.yh.yhframe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.LogUtils;
import org.yh.library.view.webview.ChromeClientCallbackManager;
import org.yh.library.view.webview.YHWebView;
import org.yh.yhframe.base.BaseActiciy;

/**
 * 作者：38314 on 2017/7/12 13:53
 * 邮箱：yh_android@163.com
 */
public class HTML5WebViewCustomAD extends BaseActiciy
{
    //private String ad_url = "http://211.149.215.12:8081/articleInterface/article/getItem?item_id=4926&os=2";
    //private String ad_url = "file:///android_asset/js_interaction/hello.html";
    private String ad_url = "http://192.168.0.130:8181/record/nr1/edit?pid=124&uid=10010&type=1";
    private YHWebView mYHWebView;
    private AlertDialog mAlertDialog;
    @BindView(id = R.id.container)
    private LinearLayout mLinearLayout;

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_html5);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("");
        toolbar.setMainTitle("");
        toolbar.setLeftTitleDrawable(R.mipmap.icon_back_colose_32px);
        long p = System.currentTimeMillis();
        mYHWebView = YHWebView.with(this)
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, ViewGroup
                        .LayoutParams.MATCH_PARENT))//主布局
                .useDefaultIndicator()//
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mCallback)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecutityType(YHWebView.SecurityType.strict)
                .createAgentWeb()//
                .ready()
                .go(ad_url);
        mYHWebView.getLoader().loadUrl(ad_url);

        long n = System.currentTimeMillis();
        LogUtils.e("Info", "init used time:" + (n - p));
        mYHWebView.getJsInterfaceHolder().addJavaObject("android",new JsObj());
        mYHWebView.getJsEntraceAccess().quickCallJs("callByAndroidMoreParams", new ValueCallback<String>()
        {
            @Override
            public void onReceiveValue(String value)
            {

            }
        });//调用JS  第一个参数方法名 后面是返回值·
    }

    class JsObj
    {
        @JavascriptInterface
        public void callAndroid(final String msg)
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    LogUtils.e("Info", "main Thread:" + Thread.currentThread());
                    YHViewInject.create().showTips("" + msg);
                }
            });
            //对外接口
        }
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        mYHWebView.getJsEntraceAccess().quickCallJs("setTime","{\"date\":\"2017-01-02\",\"time\":\"30:00\"}");
        YHViewInject.create().getExitDialog(aty, "您确定要关闭该页面吗?", "确定", "再逛逛", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                YHViewInject.create().showTips("退出");
                HTML5WebViewCustomAD.this.finish();
            }
        });
    }

    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback()
    {
        @Override
        public void onReceivedTitle(WebView view, String title)
        {
            if (toolbar != null)
            {
                toolbar.setMainTitle(title);
            }
        }
    };
    private WebViewClient mWebViewClient = new WebViewClient()
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            //do you  work
            LogUtils.e("Info", "HTML5WebViewCustomAD onPageStarted");
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient()
    {
        @Override
        public void onProgressChanged(WebView view, int newProgress)
        {
            //do you work
            LogUtils.e("Info", "progress:" + newProgress);
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if (mYHWebView.handleKeyEvent(keyCode, event))
        {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause()
    {
        mYHWebView.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume()
    {
        mYHWebView.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        LogUtils.e("Info", "result:" + requestCode + " result:" + resultCode);
        mYHWebView.uploadFileResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //mAgentWeb.destroy();
        mYHWebView.getWebLifeCycle().onDestroy();
    }
}
