package org.yh.library.utils;


import org.yh.library.db.YhDBManager;

/**
 * 框架全局设置
 * @author yh (https://github.com/android-coco)
 * 2016-3-3 上午9:45:49
 * 
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
	/**网络相关*/
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
	
	public static class Config
	{
		public static final boolean DEVELOPER_MODE = false;// 是否是发布模式
		// The system cache folder
		public static YhDBManager yhDBManager;
	}
}
