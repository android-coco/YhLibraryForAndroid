package org.yh.library.view.loading.dialog;

import android.app.Dialog;

import org.yh.library.view.loading.I_Loading;


/**
 * Created by yhlyl on 2017/5/31.
 */

public interface I_LoadingDialog extends I_Loading
{
    Dialog create();

    I_LoadingDialog setCancelable(boolean flag);

    /**
     * 设置Message
     * @param message
     * @return
     */
    I_LoadingDialog setMessage(CharSequence message);


}
