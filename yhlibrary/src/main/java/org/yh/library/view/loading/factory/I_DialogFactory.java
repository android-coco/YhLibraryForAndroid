package org.yh.library.view.loading.factory;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.StyleRes;

/**
 * Created by yhlyl on 2017/5/31.
 * DialogFactory
 */

public interface I_DialogFactory<D extends Dialog>
{
    /**
     * 创建dialog
     *
     * @param context
     * @return
     */
    D onCreateDialog(Context context);

    /**
     * 设置提示消息
     *
     * @param dialog
     * @param message
     */
    void setMessage(D dialog, CharSequence message);

    /**
     * 进入退出的动画id
     *
     * @return
     */
    @StyleRes
    int getAnimateStyleId();

}
