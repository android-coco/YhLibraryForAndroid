package org.yh.library.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
/**
 * Created by yhlyl on 2017/4/16.
 */

public class YHUtils
{
    /**
     * @param @param  obj
     * @param @return 参数说明
     * @return boolean 返回类型
     * @throws
     * @Title: empty
     * @Description: 判断所有对象是否为空
     */
    public static boolean isEmpty(Object obj)
    {
        if (null == obj)
        {
            return true;
        }
        else if ((obj instanceof String) && "".equals(obj))
        {
            return true;
        }
        else if ((obj instanceof Number) && ((Number) obj).doubleValue() == 0)
        {
            return true;
        }
        else if ((obj instanceof Collection<?>)
                && ((Collection<?>) obj).isEmpty())
        {
            return true;
        }
        else if ((obj instanceof Map<?, ?>) && ((Map<?, ?>) obj).isEmpty())
        {
            return true;
        }
        else if ((obj instanceof Object[]) && ((Object[]) obj).length == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     */
    public static boolean isNumber(CharSequence str)
    {
        try
        {
            Integer.parseInt(str.toString());
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 指定格式返回当前系统时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDataTime(String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }


    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context)
    {

        int statusHeight = -1;
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity)
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     *
     * @Title: getViewBitmap
     * @Description:
     * @param @param v
     * @param @return
     * @author yh
     * @return Bitmap 返回类型
     * @throws
     */
    public static Bitmap getViewBitmap(View v)
    {

        v.clearFocus();
        v.setPressed(false);
        // 能画缓存就返回false
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if(color != 0)
        {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if(cacheBitmap == null)
        {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    /**
     * 截屏,截取相关控件
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(View view)
    {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = view.getWidth();
        int height = view.getHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity)
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     *
     * @Title: full
     * @Description: 状态栏的显示与隐藏
     * @param @param enable
     * @author wangchen
     * @return void 返回类型
     * @throws
     */
    public static void chageWindowfull(boolean enable,Activity aty)
    {
        if (enable)
        {
            WindowManager.LayoutParams lp = aty.getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            aty.getWindow().setAttributes(lp);
            aty.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }
        else
        {
            WindowManager.LayoutParams attr = aty.getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            aty.getWindow().setAttributes(attr);
            aty.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
