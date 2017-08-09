package org.yh.library.okhttp.callback;

import java.util.Map;

/**
 * 作者：38314 on 2017/5/24 09:11
 * 邮箱：yh_android@163.com
 * HTTP请求方法
 */
public interface I_RequestManager
{
    //请求头设置
    void setHeaders(Map<String,String> headers);

    void get(final String host, String suffix,Map<String, String> headers,
             final HttpCallBack callback, Object tag);

    void get(final String host, String suffix,Map<String, String> headers,
             final HttpCallBack callback, final long connTimeOut,
             final long readTimeOut, final long writeTimeOut, Object tag);

    void get(final String host, final String suffix,Map<String, String> headers,
             final HttpCallBack callback, final Object tag,
             final long connTimeOut, final long readTimeOut,
             final long writeTimeOut);

    void post(final String host, String suffix,Map<String, String> headers,
              Map<String, String> params, final HttpCallBack callback,
              final long connTimeOut, final long readTimeOut,
              final long writeTimeOut, Object tag);

    void post(final String host, String suffix,Map<String, String> headers,
              Map<String, String> params, final HttpCallBack callback, Object tag);

    void post(final String host, final String suffix,Map<String, String> headers,
              final Map<String, String> params, final HttpCallBack callback,
              final Object tag, final long connTimeOut, final long readTimeOut,
              final long writeTimeOut);

    void postForm(final String host, final String suffix,Map<String, String> headers,
                  final Map<String, Object> params, final HttpCallBack callback,
                  final Object tag);

    void postForm(final String host, final String suffix,Map<String, String> headers,
                  final Map<String, Object> params, final HttpCallBack callback,
                  final long connTimeOut, final long readTimeOut,
                  final long writeTimeOut, final Object tag);

    void postForm(final String host, final String suffix,Map<String, String> headers,
                  final Map<String, Object> params, final HttpCallBack callback,
                  final Object tag, final long connTimeOut, final long readTimeOut,
                  final long writeTimeOut);

    void download(String url, Map<String, String> headers,final String path, String fileName,
                  final HttpCallBack callback, final long connTimeOut,
                  final long readTimeOut, final long writeTimeOut, String tag);

    void download(String url, Map<String, String> headers,final String path, String fileName,
                  final HttpCallBack callback, String tag);
}
