package org.yh.library.img;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

import org.yh.library.utils.Constants;
import org.yh.library.utils.FileUtils;

/**
 * 作者：38314 on 2017/5/24 14:13
 * 邮箱：yh_android@163.com
 */
public class YHGlideModule implements GlideModule
{
    @Override
    public void applyOptions(Context context, GlideBuilder builder)
    {
        //定义缓存大小为100M
        int diskCacheSize = 100 * 1024 * 1024;

        //自定义缓存 路径 和 缓存大小
        String diskCachePath = FileUtils.getSavePath(Constants.imgCachePath);

        //提高图片质量
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

        //自定义磁盘缓存:这种缓存只有自己的app才能访问到
        // builder.setDiskCache( new InternalCacheDiskCacheFactory( context , diskCacheSize )) ;
        // builder.setDiskCache( new InternalCacheDiskCacheFactory( context , diskCachePath , diskCacheSize  )) ;

        //自定义磁盘缓存：这种缓存存在SD卡上，所有的应用都可以访问到
        builder.setDiskCache(new DiskLruCacheFactory(diskCachePath, diskCacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide)
    {

    }
}
