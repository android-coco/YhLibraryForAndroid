package org.yh.library.okhttp.utils;

import org.yh.library.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：游浩 on 2017/8/12 16:47
 * https://github.com/android-coco/YhLibraryForAndroid
 * 邮箱：yh_android@163.com
 * okhttp重试次数拦截器
 */
public class RetryInterceptor implements Interceptor
{
    private final static String TAG = RetryInterceptor.class.getSimpleName();
    public int maxRetry = 4;//最大重试次数
    private int retryNum = 0;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）

    public RetryInterceptor(int retryNum)
    {
        this.retryNum = retryNum;
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        LogUtils.e(TAG,"重试次数：" + retryNum);
        Response response = chain.proceed(request);
        while (!response.isSuccessful() && retryNum < maxRetry)
        {
            retryNum++;
            LogUtils.e(TAG,"第" + retryNum + "次重试！");
            response = chain.proceed(request);
        }
        return response;
    }

}
