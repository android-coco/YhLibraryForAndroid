package org.yh.yhframe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.yh.yhframe.app.MyApplication;

/**
 * 作者：38314 on 2017/5/15 13:38
 * 邮箱：yh_android@163.com
 */
public class PreferenceUtils
{
    public static void write(String fileName, String k, int v)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(k, v);
        editor.commit();
    }

    public static void write(String fileName, String k,
                             boolean v)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(k, v);
        editor.commit();
    }

    public static void write(String fileName, String k,
                             String v)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(k, v);
        editor.commit();
    }

    public static int readInt(String fileName, String k)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getInt(k, 0);
    }

    public static int readInt(String fileName, String k,
                              int defv)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getInt(k, defv);
    }

    public static boolean readBoolean(String fileName, String k)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getBoolean(k, false);
    }

    public static boolean readBoolean(String fileName,
                                      String k, boolean defBool)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getBoolean(k, defBool);
    }

    public static String readString(String fileName, String k)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getString(k, "");
    }

    public static String readString(String fileName, String k,
                                    String defV)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getString(k, defV);
    }

    public static void remove(String fileName, String k)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.remove(k);
        editor.commit();
    }

    public static void clean(String fileName)
    {
        SharedPreferences preference = MyApplication.getInstance().getApplicationContext().getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.commit();
    }
}
