package org.yh.library.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import org.yh.library.ui.YHActivityStack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;


/////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                  //
////////////////////////////////////////////////////////////////////
/**
  * CrashHandler
  * @author yh
  * @time 2016/10/26 14:03
  */
/**
 * UncaughtExceptionHandler：线程未捕获异常控制器是用来处理未捕获异常的。 如果程序出现了未捕获异常默认情况下则会出现强行关闭对话框
 * 实现该接口并注册为程序中的默认未捕获异常处理 这样当未捕获异常发生时，就可以做些异常处理操作 例如：收集异常信息，发送错误报告 等。
 * UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告. <br>
 * 
 * <b>创建时间</b> 2014-7-2
 * 
 * @author yh (https://github.com/android-coco)
 */
public class CrashHandler implements UncaughtExceptionHandler
{
	public static final String TAG = "CrashHandler";
	// 系统默认的UncaughtException处理类
	private UncaughtExceptionHandler mDefaultHandler;
	private final Context mContext;
	// log文件的后缀名
	public static final String FILE_NAME_SUFFIX = "yherror.txt";
	private static CrashHandler sInstance = null;
	
	private CrashHandler(Context cxt)
	{
		// 获取系统默认的UncaughtException处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 将当前实例设为系统默认的异常处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
		// 获取Context，方便内部使用
		mContext = cxt;
	}
	
	public synchronized static CrashHandler create(Context cxt)
	{
		if (sInstance == null)
		{
			sInstance = new CrashHandler(cxt);
		}
		return sInstance;
	}
	
	/**
	 * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
	 * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
	 */
	@Override
	public void uncaughtException(Thread thread, final Throwable ex)
	{
		
		if (!handleException(ex) && mDefaultHandler != null)
		{
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		}
		else
		{
			
			try
			{
				Thread.sleep(3000);
			}
			catch (InterruptedException e)
			{
				LogUtils.e(TAG, "error : ", e);
			}
			//退出应用
			YHActivityStack.create().finishAllActivity();
			// 退出程序
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(1);
			
		}
	}
	
	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 * 
	 * @param ex
	 * @return true:如果处理了该异常信息;否则返回false.
	 */
	private boolean handleException(final Throwable ex)
	{
		if (null == ex)
		{
			return false;
		}
		LogUtils.e(TAG, ex.getMessage() + "");
		try
		{
			saveToSDCard(ex);
		}
		catch (Exception e1)
		{
		}
		return true;
	}
	
	private void saveToSDCard(Throwable ex) throws Exception
	{
		//保存文件
		File file = FileUtils.getSaveFile(Constants.logPath, FILE_NAME_SUFFIX);
		boolean append = false;
		if (System.currentTimeMillis() - file.lastModified() > 5000)
		{
			append = true;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
				file, append)));
		// 导出发生异常的时间
		pw.println(StringUtils.getDataTime("yyyy-MM-dd HH-mm-ss"));
		// 导出手机信息
		dumpPhoneInfo(pw);
		pw.println();
		// 导出异常的调用栈信息
		ex.printStackTrace(pw);
		pw.println();
		pw.close();
	}
	
	@SuppressWarnings("deprecation")
	private void dumpPhoneInfo(PrintWriter pw) throws NameNotFoundException
	{
//		String userName = PreferenceUtils.readString(AppConfig.user_xml,
//				"userName");
//		User mUser = null;
//		if (!StringUtils.isEmpty(Constants.Config.db))
//		{
//			// AppConfig = new AppConfig(aty);
//			QueryBuilder<User> qb = new QueryBuilder<User>(User.class).where(
//					User.COL_USERNAME + " =?", new String[] { userName });
//			ArrayList<User> users = Constants.Config.db.query(qb);
//			if (!StringUtils.isEmpty(users))
//			{
//				mUser = users.get(0);
//			}
//		}
		// 应用的版本名称和版本号
		PackageManager pm = mContext.getPackageManager();
		PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
				PackageManager.GET_ACTIVITIES);
		pw.print("App Version: ");
		pw.print(pi.versionName);
		pw.print('_');
		pw.println(pi.versionCode);
		pw.println();
		
		// android版本号
		pw.print("OS Version: ");
		pw.print(Build.VERSION.RELEASE);
		pw.print("_");
		pw.println(Build.VERSION.SDK_INT);
		pw.println();
		
		// 手机制造商
		pw.print("Vendor: ");
		pw.println(Build.MANUFACTURER);
		pw.println();
		
		// 手机型号
		pw.print("Model: ");
		pw.println(Build.MODEL);
		pw.println();
		
		// cpu架构
		pw.print("CPU ABI: ");
		pw.println(Build.CPU_ABI);
		pw.println();
		//APP用户信息
		// 账号
//		pw.print("账号: ");
//		pw.println(userName);
//		pw.println();

//		if (!StringUtils.isEmpty(mUser))
//		{
//			// 账号
//			pw.print("姓名: ");
//			pw.println(mUser.getName());
//			pw.println();
//
//			//用户信息
//			pw.print("用户信息:");
//			pw.println(mUser.toString());
//			pw.println();
//		}
		
	}
}