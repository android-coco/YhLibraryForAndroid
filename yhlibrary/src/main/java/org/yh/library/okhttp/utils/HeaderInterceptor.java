package org.yh.library.okhttp.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：游浩 on 2017/8/15 14:16
 * https://github.com/android-coco/YhLibraryForAndroid
 * 邮箱：yh_android@163.com
 * 统一添加请求头
 */
public class HeaderInterceptor implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .header("AppType", "Android")
                .header("token", "FJLSDJFLAJS2DLFJLASDJFLJASDLFJIJ5")
                .header("version", "1.0.1")
                .method(originalRequest.method(), originalRequest.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
