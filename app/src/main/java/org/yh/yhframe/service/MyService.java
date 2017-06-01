package org.yh.yhframe.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import org.yh.library.utils.LogUtils;
import org.yh.yhframe.MainActivity;
import org.yh.yhframe.R;

public class MyService extends Service
{
    private static final String TAG = MyService.class.getSimpleName();
    private DownloadBinder mBinder = new DownloadBinder();
    //private ThreadPoolExecutor threadPoolExecutor;

    public MyService()
    {
        LogUtils.e(TAG, "MyService()");
        //ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }

    public class DownloadBinder extends Binder
    {
        public void startDownload()
        {
            LogUtils.e(TAG, "startDownload()");
        }

        public int getProgress()
        {
            LogUtils.e(TAG, "getProgress()");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        LogUtils.e(TAG, "onCreate()");
        //前端服务
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Title")
                .setContentText("Text jjj")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap
                        .ic_launcher_round))
                .setContentIntent(pi)
                .build();
        //让android服务前台运行
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        LogUtils.e(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        LogUtils.e(TAG, "onDestroy()");
    }
}
