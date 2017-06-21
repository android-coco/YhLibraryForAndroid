package org.yh.library.utils;

import android.util.Log;

/**
 * 
*    
* 项目名称：healthplus   
* 类名称：LogUtils   
* 类描述：  Log日志工具类 
* 创建人：yh (https://github.com/android-coco)
* 创建时间：2015-5-15 下午2:01:36   
* 修改人：yh (https://github.com/android-coco)
* 修改时间：2015-5-15 下午2:01:36   
* 修改备注：   
* @version 1.0
*
 */
public final class LogUtils {

    /**
     * isPrint: print switch, true will print. false not print
     */
    public static boolean isDebug = !Constants.Config.DEVELOPER_MODE;
    private static String defaultTag = "Log";

    private LogUtils() {
    }

    public static boolean isDebug()
    {
        return  isDebug;
    }

    public static void setTag(String tag) {
        defaultTag = tag;
    }

    public static int i(Object o) {
        return isDebug && o != null ? android.util.Log.i(defaultTag, o.toString()) : -1;
    }

    public static int i(String m) {
        return isDebug && m != null ? android.util.Log.i(defaultTag, m) : -1;
    }

    /**
     * ******************** Log **************************
     */
    public static int v(String tag, String msg) {
        return isDebug && msg != null ? android.util.Log.v(tag, msg) : -1;
    }

    public static int d(String tag, String msg) {
        return isDebug && msg != null ? android.util.Log.d(tag, msg) : -1;
    }

    public static int i(String tag, String msg) {
        return isDebug && msg != null ? android.util.Log.i(tag, msg) : -1;
    }

    public static int w(String tag, String msg) {
        return isDebug && msg != null ? android.util.Log.w(tag, msg) : -1;
    }

    public static int e(String tag, String msg) {
        return isDebug && msg != null ? android.util.Log.e(tag, msg) : -1;
    }

    /**
     * ******************** Log with object list **************************
     */
    public static int v(String tag, Object... msg) {
        return isDebug ? android.util.Log.v(tag, getLogMessage(msg)) : -1;
    }

    public static int d(String tag, Object... msg) {
        return isDebug ? android.util.Log.d(tag, getLogMessage(msg)) : -1;
    }

    public static int i(String tag, Object... msg) {
        return isDebug ? android.util.Log.i(tag, getLogMessage(msg)) : -1;
    }

    public static int w(String tag, Object... msg) {
        return isDebug ? android.util.Log.w(tag, getLogMessage(msg)) : -1;
    }

    public static int e(String tag, Object... msg) {
        return isDebug ? android.util.Log.e(tag, getLogMessage(msg)) : -1;
    }

    private static String getLogMessage(Object... msg) {
        if (msg != null && msg.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object s : msg) {
                if (msg != null && s != null) {
                    sb.append(s.toString());
                }
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * ******************** Log with Throwable **************************
     */
    public static int v(String tag, String msg, Throwable tr) {
        return isDebug && msg != null ? android.util.Log.v(tag, msg, tr) : -1;
    }

    public static int d(String tag, String msg, Throwable tr) {
        return isDebug && msg != null ? android.util.Log.d(tag, msg, tr) : -1;
    }

    public static int i(String tag, String msg, Throwable tr) {
        return isDebug && msg != null ? android.util.Log.i(tag, msg, tr) : -1;
    }

    public static int w(String tag, String msg, Throwable tr) {
        return isDebug && msg != null ? android.util.Log.w(tag, msg, tr) : -1;
    }

    public static int e(String tag, String msg, Throwable tr) {
        return isDebug && msg != null ? android.util.Log.e(tag, msg, tr) : -1;
    }

    /**
     * ******************** TAG use Object Tag **************************
     */
    public static int v(Object tag, String msg) {
        return isDebug ? android.util.Log.v(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int d(Object tag, String msg) {
        return isDebug ? android.util.Log.d(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int i(Object tag, String msg) {
        return isDebug ? android.util.Log.i(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int w(Object tag, String msg) {
        return isDebug ? android.util.Log.w(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int e(Object tag, String msg) {
        return isDebug ? android.util.Log.e(tag.getClass().getSimpleName(), msg) : -1;
    }


    public static void safeCheckCrash(String tag, String msg, Throwable tr) {
        if (isDebug) {
            throw new RuntimeException(tag + " " + msg, tr);
        } else {
            Log.e(tag, msg, tr);
        }
    }
}
