package org.yh.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by yhlyl on 2017/5/15.
 * 网络工具类
 */

public class NetWorkUtils
{
    /**
     * @author jiangqq
     * 获取当前的网络状态  -1：没有网络  1：WIFI网络 2：wap网络3：net网络
     * @param context
     * @return
     */
    public static final int CMNET = 3;
    public static final int CMWAP = 2;
    public static final int WIFI = 1;

    public static int getAPNType(Context context)
    {
        int netType = -1;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null)
        {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE)
        {

            if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet"))
            {
                netType = CMNET;
            }
            else
            {
                netType = CMWAP;
            }
        }
        else if (nType == ConnectivityManager.TYPE_WIFI)
        {
            netType = WIFI;
        }
        LogUtils.e("当前网络状态：", "当前网络状态是：" + networkInfo
                .getExtraInfo());
        return netType;
    }
}
