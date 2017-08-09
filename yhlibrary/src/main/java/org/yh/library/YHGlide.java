package org.yh.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;

import org.yh.library.utils.StringUtils;

import java.io.File;

/**
 * 作者：38314 on 2017/5/24 11:37
 * 邮箱：yh_android@163.com
 */
@SuppressWarnings("all")
public class YHGlide
{
    private static YHGlide instanse;
    private Context mContext;
    private int placeholder = R.drawable.progressloading;
    private int error = R.drawable.icon_loding_error;

    private YHGlide()
    {

    }

    private YHGlide(Context mContext)
    {
        this.mContext = mContext;
    }

    public static YHGlide getInstanse(Context mContext)
    {
        if (StringUtils.isEmpty(instanse))
        {
            synchronized (YHGlide.class)
            {
                if (StringUtils.isEmpty(instanse))
                {
                    instanse = new YHGlide(mContext);
                }
            }
        }
        return instanse;
    }

    public void loadImgeForUrl(String url, ImageView imageView)
    {
        Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder).error(error).into(imageView);
    }

    //在加载开始--> 加载完成(失败)，显示placeholder()图片; 如果加载失败，则显示error() 里面的图片。
    public void loadImgeForUrl(String url, SimpleTarget<Bitmap> target)
    {
        Glide.with(mContext).load(url).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder).error(error).into(target);
    }

    public void loadImgeForDrawable(int resourceId, ImageView imageView)
    {
        Glide.with(mContext).load(resourceId).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder).error(error).into(imageView);
    }

    public void loadImgeSD(String path, ImageView imageView)
    {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder).error(error).into(imageView);
    }

    //sd卡上的一张图片
    // String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp.jpg" ;
    // File file = new File( path ) ;
    public void loadImgeSD(File file, ImageView imageView)
    {
        Glide.with(mContext).load(file).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder).error(error).into(imageView);
    }

    //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp.jpg" ;
    // File file = new File( path ) ;
    //Uri uri = Uri.fromFile( file ) ;
    public void loadImgeSD(Uri uri, ImageView imageView)
    {
        Glide.with(mContext).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder).error(error).into(imageView);
    }

    public void loadImgeGif(String url, ImageView imageView)
    {
        Glide.with(mContext).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder).error(error).into(imageView);
    }

    /**
     * 清除缓存
     */
    public void clearCache()
    {
        clearMemoryCache();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                clearDiskCache();
            }
        }).start();
    }

    /**
     * 清除内存缓存
     */
    public void clearMemoryCache()
    {
        Glide.get(mContext).clearMemory();
    }

    /**
     * 清除磁盘缓存
     */
    public void clearDiskCache()
    {
        Glide.get(mContext).clearDiskCache();
    }
}
