package org.yh.yhframe.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import org.yh.library.utils.LogUtils;

/**
 *
 */
public class MyIntentService extends IntentService
{
    private static final String TAG = MyIntentService.class.getSimpleName();
    private boolean isWook = true;
    public MyIntentService()
    {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        LogUtils.e(TAG,"onHandleIntent  " + Thread.currentThread().getId());
        while (isWook)
        {
            LogUtils.e(TAG,"while(true) " + Thread.currentThread().getId());
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        isWook = false;
        LogUtils.e(TAG,"onDestroy()");
    }
}
