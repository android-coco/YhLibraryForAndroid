package org.yh.library.okhttp.builder;

import org.yh.library.okhttp.request.PostFileRequest;
import org.yh.library.okhttp.request.RequestCall;

import java.io.File;

import okhttp3.MediaType;

/**
 * Created by zhy on 15/12/14.
 */
public class PostFileBuilder extends OkHttpRequestBuilder<PostFileBuilder> 
{
    private File file;
    private MediaType mediaType;


    public OkHttpRequestBuilder<PostFileBuilder> file(File file) 
    {
        this.file = file;
        return this;
    }

    public OkHttpRequestBuilder<PostFileBuilder> mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostFileRequest(url, tag, params, headers, file, mediaType,id).build();
    }


}
