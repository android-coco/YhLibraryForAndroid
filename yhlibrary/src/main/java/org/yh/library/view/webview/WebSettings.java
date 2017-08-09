package org.yh.library.view.webview;

import android.webkit.WebView;


public interface WebSettings <T extends android.webkit.WebSettings>{

    WebSettings toSetting(WebView webView);

    T getWebSettings();
}
