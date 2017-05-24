package org.yh.library.okhttp.callback;

import java.util.Map;

/**
 * 作者：38314 on 2017/5/24 09:11
 * 邮箱：yh_android@163.com
 * HTTP请求方法
 */
public interface I_RequestManager
{

    void get(final String host, String suffix,
             final HttpCallBack callback, Object tag);

    void get(final String host, String suffix,
             final HttpCallBack callback, final long connTimeOut,
             final long readTimeOut, final long writeTimeOut, Object tag);

    void get(final String host, final String suffix,
             final HttpCallBack callback, final Object tag,
             final long connTimeOut, final long readTimeOut,
             final long writeTimeOut);

    void post(final String host, String suffix,
              Map<String, String> params, final HttpCallBack callback,
              final long connTimeOut, final long readTimeOut,
              final long writeTimeOut, Object tag);

    void post(final String host, String suffix,
              Map<String, String> params, final HttpCallBack callback, Object tag);

    void post(final String host, final String suffix,
              final Map<String, String> params, final HttpCallBack callback,
              final Object tag, final long connTimeOut, final long readTimeOut,
              final long writeTimeOut);

    void postForm(final String host, final String suffix,
                  final Map<String, Object> params, final HttpCallBack callback,
                  final Object tag);

    void postForm(final String host, final String suffix,
                  final Map<String, Object> params, final HttpCallBack callback,
                  final long connTimeOut, final long readTimeOut,
                  final long writeTimeOut, final Object tag);

    void postForm(final String host, final String suffix,
                  final Map<String, Object> params, final HttpCallBack callback,
                  final Object tag, final long connTimeOut, final long readTimeOut,
                  final long writeTimeOut);

    void download(String url, final String path, String fileName,
                  final HttpCallBack callback, final long connTimeOut,
                  final long readTimeOut, final long writeTimeOut, String tag);

    void download(String url, final String path, String fileName,
                  final HttpCallBack callback, String tag);
}
