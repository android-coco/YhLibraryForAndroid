package org.yh.library.view.loading.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;

import org.yh.library.view.loading.YHLoadingConfig;
import org.yh.library.view.loading.factory.I_DialogFactory;

/**
 * Created by yhlyl on 2017/5/31.
 */

public class YHLoadingDialog implements I_LoadingDialog
{
    private static YHLoadingDialog LOADINGDIALOG;

    private Dialog mDialog;
    private I_DialogFactory mFactory;

    public YHLoadingDialog(Context context, I_DialogFactory factory)
    {
        this.mDialog = factory.onCreateDialog(context);
        this.mFactory = factory;
        int animateStyleId = this.mFactory.getAnimateStyleId();
        if (animateStyleId > 0)
        {
            this.mDialog.getWindow().setWindowAnimations(animateStyleId);
        }
    }

    @Override
    public void show()
    {
        if (isValid() && !mDialog.isShowing())
        {
            mDialog.show();
        }
    }

    public void cancelDialog()
    {
        if (isValid() && mDialog.isShowing())
        {
            mDialog.cancel();
        }
    }


    @Override
    public Dialog create()
    {
        return mDialog;
    }

    @Override
    public I_LoadingDialog setCancelable(boolean flag)
    {
        mDialog.setCancelable(flag);
        return this;
    }

    @Override
    public I_LoadingDialog setMessage(CharSequence message)
    {
        mFactory.setMessage(mDialog, message);
        return this;
    }

    private boolean isValid()
    {
        if (mDialog != null)
        {
            Context context = mDialog.getContext();
            if (context instanceof ContextWrapper)
            {
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (context instanceof Activity)
            {
                if (!((Activity) context).isFinishing())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static YHLoadingDialog make(Context context)
    {
        return make(context, YHLoadingConfig.getDialogFactory());
    }

    public static YHLoadingDialog make(Context context, I_DialogFactory factory)
    {
        cancel();
        LOADINGDIALOG = new YHLoadingDialog(context, factory);
        return LOADINGDIALOG;
    }


    public static void cancel()
    {
        if (LOADINGDIALOG != null)
        {
            LOADINGDIALOG.cancelDialog();
            LOADINGDIALOG = null;
        }
    }
}
