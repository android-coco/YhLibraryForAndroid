package org.yh.library.utils;


import org.yh.library.db.YhDBManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 框架全局设置
 *
 * @author yh (https://github.com/android-coco)
 *         2016-3-3 上午9:45:49
 */
public final class Constants
{
    // String imageUri = "http://site.com/image.png"; // 网络图片
    // String imageUri = "file:///mnt/sdcard/image.png"; //SD卡图片
    // String imageUri = "content://media/external/audio/albumart/13"; // 媒体文件夹
    // String imageUri = "assets://image.png"; // assets
    // String imageUri = "drawable://" + R.drawable.image; // drawable文件
    // 图像从SD卡编码符号 file:///storage/sdcard0/YH_LL_Care/imgs/face/28.jpg
    public static final String FILE_SD = "file://";
    public static final String FILE_HTTP = "http://";
    public static final String FILE_HTTPS = "https://";
    // Image from assets "drawable://" + R.drawable.ic_launcher
    public static final String FILE_DRAWBLE = "drawable://";
    public static final String FILE_CONTENT = "content://";
    public static final String FILE_ASSETS = "assets://";
    // 图片格式
    public static final String IMG_PNG = "png";
    public static final String IMG_JPG = "jpg";
    // 缓存文件夹
    public static final String saveFolder = "yh";
    // 图片缓存文件夹
    public static final String imgCachePath = saveFolder + "/imgs";
    // 视频缓存目录
    public static final String videoCachePath = saveFolder + "/imgs";
    // 错误日志
    public static final String logPath = saveFolder + "/log";
    /**
     * 网络相关
     */
    // 主URL
    public final static String HOST_MAIN = "http://user.fitcome.net";
    // 服务器返回码 "0"表示请求成功
    public static String RESULT = "result";

    public static final double VERSION = 2.6;

    /**
     * 错误处理广播
     */
    public static final String RECEIVER_ERROR = Constants.class.getName()
            + "org.yh.android.frame.error";
    /**
     * 无网络警告广播
     */
    public static final String RECEIVER_NOT_NET_WARN = Constants.class.getName()
            + "org.yh.android.frame.notnet";
    /**
     * preference键值对
     */
    public static final String SETTING_FILE = "yhframe_preference";
    public static final String ONLY_WIFI = "only_wifi";

    private Constants()
    {
    }
    //初始权限对应表
    public static Map<String, String> initPermissionNames()
    {
        //权限对应表
        Map<String, String> permissionNames = new HashMap<>();
        //组  android.Manifest.permission_group.STORAGE
        // android.permission-group.STORAGE 用于与共享外部存储相关的运行时权限。
        /**
         * 声明WRITE_EXTERNAL_STORAGE权限的任何应用程序都隐式地授予此权限。
         这个权限是从API级别19开始执行的。在API第19级之前，这个权限没有执行，
         所有应用程序仍然可以从外部存储器读取。你可以通过在运行Android 4.1或更高版本的设备上的
         设置应用程序中启用保护USB存储，来测试你的应用程序。
         同样从API第19级开始，这个权限不需要在您的应用程序特定目录中读写文件，
         这些文件由getExternalFilesDir(String)和getExternalCacheDir()返回。
         注意:如果您的minSdkVersion和targetSdkVersion值设置为3或更低，系统将隐式地授予应用程序这个权限。
         如果您不需要此权限，请确保您的targetSdkVersion为4或更高。
         */
        permissionNames.put("android.permission.READ_EXTERNAL_STORAGE", "允许应用程序从外部存储器读取。");
        permissionNames.put("android.permission.WRITE_EXTERNAL_STORAGE", "允许应用程序从外部存储器编写。");


        //组  android.Manifest.permission_group.CALENDAR
        // android.permission-group.CALENDAR 用于与用户日历相关的运行时权限。
        permissionNames.put("android.permission.READ_CALENDAR", "允许应用程序读取用户的日历数据。");
        permissionNames.put("android.permission.WRITE_CALENDAR", "允许应用程序编写用户的日历数据。");

        //组  android.Manifest.permission_group.CAMERA
        // android.permission-group.CAMERA 用于与访问摄像机或从设备捕获图像/视频相关联的权限。
        permissionNames.put("android.permission.CAMERA", "需要能够访问相机设备。");

        //组  android.Manifest.permission_group.CONTACTS
        // android.permission-group.CONTACTS 用于与此设备上的联系人和配置文件相关的运行时权限。
        permissionNames.put("android.permission.READ_CONTACTS", "允许应用程序读取用户的联系人数据。");
        permissionNames.put("android.permission.WRITE_CONTACTS", "允许应用程序编写用户的联系人数据。");
        permissionNames.put("android.permission.GET_ACCOUNTS", "允许访问帐户服务中的帐户列表。");

        //组  android.Manifest.permission_group.LOCATION
        // android.permission-group.LOCATION 用于允许访问设备位置的权限。
        permissionNames.put("android.permission.ACCESS_FINE_LOCATION", "允许应用程序访问精确的位置。");
        permissionNames.put("android.permission.ACCESS_COARSE_LOCATION", "允许应用程序访问近似位置。");

        //组  android.Manifest.permission_group.MICROPHONE
        // android.permission-group.MICROPHONE 用于与从设备访问麦克风音频相关的权限。
        permissionNames.put("android.permission.RECORD_AUDIO", "允许应用程序录制音频。。");

        //组  android.Manifest.permission_group.PHONE
        // android.permission-group.PHONE 用于相关电话功能的权限。
        permissionNames.put("android.permission.READ_PHONE_STATE", "允许只读访问电话状态，包括设备的电话号码，当前的蜂窝网络信息，任何正在进行的呼叫的状态，以及在设备上注册的任何电话帐户的列表。");
        permissionNames.put("android.permission.CALL_PHONE", "允许应用程序在不通过拨号用户接口的情况下启动一个电话呼叫，让用户确认呼叫。");
        //注意:如果您的应用程序使用READ_CONTACTS权限，并且您的minSdkVersion和targetSdkVersion值设置为15或更低，
        // 系统将隐式授予您的应用程序这个权限。如果您不需要此权限，请确保您的targetSdkVersion 16或更高。
        permissionNames.put("android.permission.READ_CALL_LOG", "允许应用程序读取用户的调用日志。");
        //注意:如果您的应用程序使用WRITE_CONTACTS权限，而minSdkVersion和targetSdkVersion值设置为15或更低，
        // 则系统会隐式地授予应用程序这个权限。如果您不需要此权限，请确保您的targetSdkVersion 16或更高。
        permissionNames.put("android.permission.WRITE_CALL_LOG", "允许应用程序编写(而不是读取)用户的调用日志数据。");
        permissionNames.put("android.permission.ADD_VOICEMAIL", "允许应用程序将语音邮件添加到系统中。");
        permissionNames.put("android.permission.USE_SIP", "允许应用程序使用SIP服务。");
        permissionNames.put("android.permission.PROCESS_OUTGOING_CALLS", "允许应用程序看到在发出调用时被拨号的数字，该选项可以将呼叫重定向到一个不同的数字，或者完全取消呼叫。");


        //组  android.Manifest.permission_group.SENSORS 传感器
        // android.permission-group.SENSORS  用于与访问摄像机或从设备捕获图像/视频相关联的权限。
        permissionNames.put("android.permission.BODY_SENSORS", "允许应用程序从用户使用的传感器访问数据，以测量其身体内部发生的情况，例如心率。");

        //组  android.Manifest.permission_group.SMS
        // android.permission-group.SMS 用于与用户的SMS消息相关的运行时权限。
        permissionNames.put("android.permission.SEND_SMS", "允许应用程序发送SMS消息。");
        permissionNames.put("android.permission.RECEIVE_SMS", "允许应用程序接收SMS消息。");
        permissionNames.put("android.permission.READ_SMS", "允许应用程序读取SMS消息。");
        permissionNames.put("android.permission.RECEIVE_WAP_PUSH", "允许应用程序接收WAP推送消息。");
        permissionNames.put("android.permission.RECEIVE_MMS", "允许应用程序监视传入的MMS消息。");

        return permissionNames;
    }

    public static class Config
    {
        public static final boolean DEVELOPER_MODE = false;// 是否是发布模式
        public static YhDBManager yhDBManager;//数据库操作对象
        public static boolean  IS_WRITE_EXTERNAL_STORAGE = false;//是否有读写磁盘的权限
    }
}
