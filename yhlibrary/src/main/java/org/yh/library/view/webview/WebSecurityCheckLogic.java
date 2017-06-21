package org.yh.library.view.webview;

import android.support.v4.util.ArrayMap;
import android.webkit.WebView;

/**
 * <b>@项目名：</b> Helmet<br>
 * <b>@包名：</b>com.ucmap.helmet<br>
 * <b>@创建者：</b> cxz --  just<br>
 * <b>@创建时间：</b> &{DATE}<br>
 * <b>@公司：</b> 宝诺科技<br>
 * <b>@邮箱：</b> cenxiaozhong.qqcom@qq.com<br>
 * <b>@描述</b><br>
 */

public interface WebSecurityCheckLogic {
    void dealHoneyComb(WebView view);

    void dealJsInterface(ArrayMap<String, Object> objects, YHWebView.SecurityType securityType);

}
