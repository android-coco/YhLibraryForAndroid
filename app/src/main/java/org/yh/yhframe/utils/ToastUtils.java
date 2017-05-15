package org.yh.yhframe.utils;

import android.widget.Toast;

import org.yh.library.utils.StringUtils;
import org.yh.yhframe.app.MyApplication;

/**
 * 作者：38314 on 2017/5/15 13:37
 * 邮箱：yh_android@163.com
 */
public class ToastUtils
{
    public static Toast tipsToast = null;

    /**
     * @param @param tips 文件资源ID
     * @return void 返回类型
     * @throws
     * @Title: showTips
     * @Description: 文字资源 提示
     */
    public static void showTips(int tips)
    {
        showTips(MyApplication.getInstance().getApplicationContext().getString(tips));
    }

    /**
     * @param @param tips 字符串文字
     * @return void 返回类型
     * @throws
     * @Title: showTips
     * @Description: 字符串提示
     */
    public static void showTips(String tips)
    {
        showTips(0, tips);
    }

    /**
     * @param @param iconResId 图片ID
     * @param @param tips 文字ID
     * @return void 返回类型
     * @throws
     * @Title: showTips
     * @Description:
     */
    public static void showTips(int iconResId, String tips)
    {
        if (StringUtils.isEmpty(tipsToast))
        {
            tipsToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), tips, Toast.LENGTH_SHORT);
        } else
        {
            tipsToast.setText(tips);
            tipsToast.setDuration(Toast.LENGTH_SHORT);
        }
        tipsToast.show();
    }

    public static void disMisTip()
    {
        if (!StringUtils.isEmpty(tipsToast))
        {
            tipsToast.cancel();
            tipsToast = null;
        }
    }
}
