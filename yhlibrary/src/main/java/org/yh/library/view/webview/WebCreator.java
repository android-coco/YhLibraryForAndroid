package org.yh.library.view.webview;

import android.view.ViewGroup;
import android.webkit.WebView;


public interface WebCreator extends ProgressManager {
    WebCreator create();

    WebView get();

    ViewGroup getGroup();
}
