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
     */
    D onCreateDialog(Context context);

    /**
     * 设置提示消息
     *
     */
    void setMessage(D dialog, CharSequence message);

    /**
     * 进入退出的动画id
     */
    @StyleRes
    int getAnimateStyleId();

}
