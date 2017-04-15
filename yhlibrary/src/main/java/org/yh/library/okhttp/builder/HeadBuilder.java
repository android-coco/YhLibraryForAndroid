package org.yh.library.okhttp.builder;

import org.yh.library.okhttp.OkHttpUtils;
import org.yh.library.okhttp.request.OtherRequest;
import org.yh.library.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
