/*
 * Copyright (c) 2014, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yh.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.yh.library.bean.EventBusBean;
import org.yh.library.okhttp.OkHttpRequestManager;
import org.yh.library.ui.AnnotateUtil;
import org.yh.library.ui.I_BroadcastReg;
import org.yh.library.ui.I_SkipActivity;
import org.yh.library.ui.I_YHActivity;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.LogUtils;

import java.lang.ref.SoftReference;

import static org.yh.library.utils.SystemUtils.isGranted;

/**
 * @author yh (https://github.com/android-coco) on 11/19/15.
 */
@SuppressWarnings("all")
public abstract class YHActivity extends AppCompatActivity implements
        View.OnClickListener, I_BroadcastReg, I_YHActivity, I_SkipActivity
{
    protected final String TAG = this.getClass().getSimpleName();
    public static final int WHICH_MSG = 0X37210;

    public Activity aty;

    protected YHFragment currentYHFragment;
    private ThreadDataCallBack callback;
    private YHActivityHandle threadHandle = new YHActivityHandle(this);

    /**
     * Activity状态
     */
    public int activityState = DESTROY;

    /**
     * 一个私有回调类，线程中初始化数据完成后的回调
     */
    private interface ThreadDataCallBack
    {
        void onSuccess();
    }


    private static class YHActivityHandle extends Handler
    {
        private final SoftReference<YHActivity> mOuterInstance;

        YHActivityHandle(YHActivity outer)
        {
            mOuterInstance = new SoftReference<>(outer);
        }

        // 当线程中初始化的数据初始化完成后，调用回调方法
        @Override
        public void handleMessage(android.os.Message msg)
        {
            YHActivity aty = mOuterInstance.get();
            if (msg.what == WHICH_MSG && aty != null)
            {
                aty.callback.onSuccess();
            }
        }
    }

    /**
     * 如果调用了initDataFromThread()，则当数据初始化完成后将回调该方法。
     */
    protected void threadDataInited()
    {
    }

    /**
     * 在线程中初始化数据，注意不能在这里执行UI操作
     */
    @Override
    public void initDataFromThread()
    {
        callback = new ThreadDataCallBack()
        {
            @Override
            public void onSuccess()
            {
                threadDataInited();
            }
        };
    }

    @Override
    public void initData()
    {
    }

    @Override
    public void initWidget()
    {
    }
    @Override
    public void requestPermissionSuccess()
    {
    }
    // 仅仅是为了代码整洁点
    private void initializer()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                initDataFromThread();
                threadHandle.sendEmptyMessage(WHICH_MSG);
            }
        }).start();
        initData();
        initWidget();
    }

    /**
     * listened widget's click method
     */
    @Override
    public void widgetClick(View v)
    {
    }

    @Override
    public void onClick(View v)
    {
        widgetClick(v);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T bindView(int id)
    {
        return (T) findViewById(id);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T bindView(int id, boolean click)
    {
        T view = (T) findViewById(id);
        if (click)
        {
            view.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void registerBroadcast()
    {
    }

    @Override
    public void unRegisterBroadcast()
    {
    }


    /***************************************************************************
     * print Activity callback methods
     ***************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        aty = this;
        //YHActivityStack.create().addActivity(this);
        LogUtils.e(TAG, "---------onCreat ");
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setRootView(); // 必须放在annotate之前调用
        AnnotateUtil.initBindView(this);
        initializer();
        registerBroadcast();
    }
    //申请权限
    protected void requestPermission(String permission, int requestCode)
    {
        if (!isGranted(getApplicationContext(), permission))
        {
            //主要用于给用户一个申请权限的解释，该方法只有在用户在上一次已经拒绝过你的这个权限申请。
            // 也就是说，用户已经拒绝一次了，
            // 你又弹个授权框，你需要给用户一个解释，为什么要授权，则使用该方法。
            YHViewInject.create().showTips("XX:" + ActivityCompat.shouldShowRequestPermissionRationale(aty, permission));
            if (!ActivityCompat.shouldShowRequestPermissionRationale(aty, permission))
            {
                ActivityCompat.requestPermissions(aty, new String[]{permission}, requestCode);
            }
        } else
        {
            //直接执行相应操作了
            requestPermissionSuccess();
        }
    }
    @Override
    protected void onStart()
    {
        super.onStart();
       LogUtils.e(TAG, "---------onStart ");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        activityState = RESUME;
       LogUtils.e(TAG, "---------onResume ");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        activityState = PAUSE;
       LogUtils.e(TAG, "---------onPause ");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        activityState = STOP;
       LogUtils.e(TAG, "---------onStop ");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
       LogUtils.e(TAG, "---------onRestart ");
    }

    @Override
    protected void onDestroy()
    {
        unRegisterBroadcast();
        activityState = DESTROY;
        LogUtils.e(TAG, "---------onDestroy ");
        super.onDestroy();
        //YHActivityStack.create().finishActivity(this);
        currentYHFragment = null;
        callback = null;
        threadHandle = null;
        aty = null;
        OkHttpRequestManager.cancel(TAG);//取消网络请求
        YHViewInject.create().disMisTip();
        EventBus.getDefault().unregister(this);
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Class<?> cls)
    {
        showActivity(aty, cls);
        aty.finish();
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Intent it)
    {
        showActivity(aty, it);
        aty.finish();
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras)
    {
        showActivity(aty, cls, extras);
        aty.finish();
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Class<?> cls)
    {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Intent it)
    {
        aty.startActivity(it);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Class<?> cls, Bundle extras)
    {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }
    /**
     * 用Fragment替换视图
     *
     * @param resView        将要被替换掉的视图
     * @param targetFragment 用来替换的Fragment
     */
    public void changeFragment(int resView, YHFragment targetFragment)
    {
        if (targetFragment.equals(currentYHFragment))
        {
            return;
        }
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded())
        {
            transaction.add(resView, targetFragment, targetFragment.getClass()
                    .getName());
        }
        if (targetFragment.isHidden())
        {
            transaction.show(targetFragment);
            targetFragment.onChange();
        }
        if (currentYHFragment != null
                && currentYHFragment.isVisible())
        {
            transaction.hide(currentYHFragment);
        }
        currentYHFragment = targetFragment;
        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ploginOut(EventBusBean msg)
    {

    }
}
