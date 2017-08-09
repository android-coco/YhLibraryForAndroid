package org.yh.library.view.webview;

import android.view.KeyEvent;


public interface IEventHandler {

    boolean onKeyDown(int keyCode, KeyEvent event);


    boolean back();
}
