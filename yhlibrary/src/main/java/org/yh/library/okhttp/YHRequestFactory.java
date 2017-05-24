package org.yh.library.okhttp;

import org.yh.library.okhttp.callback.I_RequestManager;

/**
 * 作者：38314 on 2017/5/24 09:23
 * 邮箱：yh_android@163.com
 * 请求工厂
 */
public class YHRequestFactory
{
    public static I_RequestManager getRequestManger()
    {
        return OkHttpRequestManager.getInstance();
        //return VolleyRequestManager.getInstance();
    }

}
