package org.yh.library.okhttp.callback;

import android.graphics.Bitmap;

import java.io.File;
import java.util.Map;

import okhttp3.Request;

/**
 * ClassName: HttpCallBack <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015-5-20 下午2:36:31 <br/>
 * Http请求回调类
 *
 * @author hao
 * @since JDK 1.6
 */
public abstract class HttpCallBack
{

    /**
     * 请求开始之前回调
     */
    public void onPreStar()
    {
    }

    /**
     * 请求开始之前回调
     */
    public void onBefore(Request request, int id)
    {
    }

    /**
     * Http请求成功时回调
     *
     * @param t HttpRequest返回信息
     */
    public void onSuccess(String t)
    {
    }

    /**
     * Http请求成功时回调
     */
    public void onSuccess(File response, int id)
    {
    }


    /**
     * Http请求成功时回调
     *
     * @param t HttpRequest返回信息
     */
    public void onSuccess(byte[] t)
    {
        if (t != null)
        {
            onSuccess(new String(t));
        }
    }

    /**
     * Http请求成功时回调
     *
     * @param headers HttpRespond头
     * @param t       HttpRequest返回信息
     */
    public void onSuccess(Map<String, String> headers, byte[] t)
    {
        onSuccess(t);
    }

    /**
     * 仅在YHBitmap中可用，图片加载完成时回调
     *
     * @param t
     */
    public void onSuccess(Bitmap t)
    {
    }

    /**
     * Http请求失败时回调
     *
     * @param errorNo 错误码
     * @param strMsg  错误原因
     */
    public void onFailure(int errorNo, String strMsg)
    {
    }

    /**
     * Http请求结束后回调
     */
    public void onFinish()
    {
    }

    /**
     * 进度回调，仅支持Download时使用
     *
     * @param count   总数
     * @param current 当前进度
     */
    public void onLoading(long count, long current)
    {
    }

    /**
     * 进度回调，仅支持Download时使用
     *
     * @param progress 当前进度
     * @param total    总数
     * @param id
     */
    public void onLoading(float progress, long total, int id)
    {
    }
}
