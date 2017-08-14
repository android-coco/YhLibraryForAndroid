package org.yh.library.okhttp.utils;

import android.text.TextUtils;

import org.yh.library.utils.LogUtils;
import org.yh.library.utils.StringUtils;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by zhy on 16/3/1.
 * HTTP 请求和回复  log日志
 */
public class LoggerInterceptor implements Interceptor
{
    public static final String TAG = "YHOkHttpLog";
    private String tag;
    private boolean showResponse;

    public LoggerInterceptor(String tag, boolean showResponse)
    {
        if (TextUtils.isEmpty(tag))
        {
            tag = TAG;
        }
        this.showResponse = showResponse;
        this.tag = tag;
    }

    public LoggerInterceptor(String tag)
    {
        this(tag, false);
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        long startTime = System.currentTimeMillis();
        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        long duration=endTime-startTime;
        return logForResponse(response,duration);
    }

    private Response logForResponse(Response response,long duration)
    {
        try
        {
            //===>response LogUtils
            LogUtils.e(tag, "============服务器回复'LogUtils============开始");
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            LogUtils.e(tag, "请求url : " + clone.request().url());
            LogUtils.e(tag, "请求code : " + clone.code());
            LogUtils.e(tag, "请求协议protocol : " + clone.protocol());
            if (!TextUtils.isEmpty(clone.message()))
            {
                LogUtils.e(tag, "是否成功 : " + clone.message());
                if (showResponse)
                {
                    ResponseBody body = clone.body();
                    if (body != null)
                    {
                        MediaType mediaType = body.contentType();
                        if (mediaType != null)
                        {
                            LogUtils.e(tag, "响应主体的类型 : " + mediaType.toString());
                            if (isText(mediaType))
                            {
                                String resp = body.string();
                                LogUtils.e(tag, "响应主体的内容 : " + resp);
                                body = ResponseBody.create(mediaType, resp);
                                LogUtils.e(tag, "============服务器回复'LogUtils==========结束" + duration+"毫秒");
                                return response.newBuilder().body(body).build();
                            } else
                            {
                                LogUtils.e(tag, "响应主体的内容 : " + " 也许[文件部分]，太大了太大了，忽略了!");
                            }
                        }
                    }
                }
            }
            LogUtils.e(tag, "============服务器回复'LogUtils==========结束" + duration+"毫秒");
        }
        catch (Exception e)
        {
            LogUtils.e(tag, "============服务器回复打印异常："+e.getMessage());
        }
        return response;
    }

    private void logForRequest(Request request)
    {
        try
        {
            String url = request.url().toString();
            Headers headers = request.headers();
            LogUtils.e(tag, "============请求'LogUtils=====开始");
            LogUtils.e(tag, "请求方式 : " + request.method());
            LogUtils.e(tag, "请求URL : " + url);
            if (headers != null && headers.size() > 0)
            {
                LogUtils.e(tag, "请求头 : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null)
            {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null)
                {
                    LogUtils.e(tag, "请求参数类型 : " + mediaType);
                    LogUtils.e(tag, "请求参数内容 : " + bodyToString(request).replace("&","   "));
//                    if (isText(mediaType))
//                    {
//                        LogUtils.e(tag, "请求参数内容 : " + bodyToString(request));
//                    } else
//                    {
//                        LogUtils.e(tag, "请求参数内容 : " + " 也许[文件部分]，太大了太大了，忽略了!");
//                    }
                }
            }
            LogUtils.e(tag, "============请求'LogUtils============结束");
        }
        catch (Exception e)
        {
//            e.printStackTrace();
        }
    }

    private boolean isText(MediaType mediaType)
    {
        if (mediaType.type() != null && mediaType.type().equals("text"))
        {
            return true;
        }
        if (mediaType.subtype() != null)
        {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
            {
                return true;
            }
        }
        return false;
    }

    private String bodyToString(final Request request)
    {
        try
        {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return StringUtils.decodeUnicodeToString(buffer.readUtf8Line());
        }
        catch (final IOException e)
        {
            return "请求参数获取出错."+e.getMessage();
        }
    }

}
