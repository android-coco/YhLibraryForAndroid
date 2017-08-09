package org.yh.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;

/**
 * 文件与流处理工具类
 */
public class FileUtils
{

	/**
	 * 创建sd卡的目录
	 * @param dirName
	 * @return file
	 */
	public static File createSDDir(String dirName)
	{
		File file = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()))
		{
			// 创建一个文件夹对象，赋值为外部存储器的目录
			File sdcardDir = Environment.getExternalStorageDirectory();
			// 得到一个路径，内容是sdcard的文件夹路径和名字
			String path = sdcardDir.getPath() + dirName;
			file = new File(path);
			if (!file.exists())
			{
				// 若不存在，创建目录，可以在应用启动的时候创建
				file.mkdirs();
			}
		}
		return file;
	}
	
	public static boolean fileIsExists(String path)
	{
		try
		{
			File f = new File(path);
			if (!f.exists())
			{
				return false;
			}
		}
		catch (Exception e)
		{
			
			return false;
		}
		return true;
	}
	
	/**
	 * 检测SD卡是否存在
	 */
	public static boolean checkSDcard()
	{
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState());
	}
	
	/**
	 * 将文件保存到本地
	 */
	public static void saveFileCache(byte[] fileData, String folderPath,
			String fileName)
	{
		File folder = new File(folderPath);
		folder.mkdirs();
		File file = new File(folderPath, fileName);
		ByteArrayInputStream is = new ByteArrayInputStream(fileData);
		OutputStream os = null;
		if (!file.exists())
		{
			try
			{
				file.createNewFile();
				os = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while (-1 != (len = is.read(buffer)))
				{
					os.write(buffer, 0, len);
				}
				os.flush();
			}
			catch (Exception e)
			{
				throw new RuntimeException(
						FileUtils.class.getClass().getName(), e);
			}
			finally
			{
				closeIO(is, os);
			}
		}
	}
	
	/**
	 * 从指定文件夹获取文件
	 * 
	 * @return 如果文件不存在则创建,如果如果无法创建文件或文件名为空则返回null
	 */
	public static File getSaveFile(String folderPath, String fileNmae)
	{
		File file = new File(getSavePath(folderPath) + File.separator
				+ fileNmae);
		try
		{
			file.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * 获取SD卡下指定文件夹的绝对路径
	 * 
	 * @return 返回SD卡下的指定文件夹的绝对路径
	 */
	public static String getSavePath(String folderName)
	{
		return getSaveFolder(folderName).getAbsolutePath();
	}
	
	/**
	 * 获取文件夹对象
	 * 
	 * @return 返回SD卡下的指定文件夹对象，若文件夹不存在则创建
	 */
	public static File getSaveFolder(String folderName)
	{
		File file = new File(getSDCardPath() + File.separator + folderName
				+ File.separator);
		file.mkdirs();
		
		return file;
	}
	
	public static String getSDCardPath()
	{
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
	
	/**
	 * 输入流转byte[]<br>
	 */
	public static final byte[] input2byte(InputStream inStream)
	{
		if (inStream == null)
		{
			return null;
		}
		byte[] in2b = null;
		BufferedInputStream in = new BufferedInputStream(inStream);
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int rc = 0;
		try
		{
			while ((rc = in.read()) != -1)
			{
				swapStream.write(rc);
			}
			in2b = swapStream.toByteArray();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeIO(inStream, in, swapStream);
		}
		return in2b;
	}
	
	/**
	 * 复制文件
	 * 
	 * @param from
	 * @param to
	 */
	public static void copyFile(File from, File to)
	{
		if (null == from || !from.exists())
		{
			return;
		}
		if (null == to)
		{
			return;
		}
		FileInputStream is = null;
		FileOutputStream os = null;
		try
		{
			is = new FileInputStream(from);
			if (!to.exists())
			{
				to.createNewFile();
			}
			os = new FileOutputStream(to);
			copyFileFast(is, os);
		}
		catch (Exception e)
		{
			throw new RuntimeException(FileUtils.class.getClass().getName(), e);
		}
		finally
		{
			closeIO(is, os);
		}
	}
	
	/**
	 * 快速复制文件（采用nio操作）
	 * 
	 * @param is
	 *        数据来源
	 * @param os
	 *        数据目标
	 * @throws IOException
	 */
	public static void copyFileFast(FileInputStream is, FileOutputStream os)
			throws IOException
	{
		FileChannel in = is.getChannel();
		FileChannel out = os.getChannel();
		in.transferTo(0, in.size(), out);
	}
	
	/**
	 * 关闭流
	 * 
	 * @param closeables
	 */
	public static void closeIO(Closeable... closeables)
	{
		if (null == closeables || closeables.length <= 0)
		{
			return;
		}
		for (Closeable cb : closeables)
		{
			try
			{
				if (null == cb)
				{
					continue;
				}
				cb.close();
			}
			catch (IOException e)
			{
				throw new RuntimeException(
						FileUtils.class.getClass().getName(), e);
			}
		}
	}

	/**
	 * 保存图片
	 * @param bm
	 * @param picName
	 * @param type
	 * @return 图片路径
	 */
	public static String saveBitmap(Bitmap bm, String picName, String type)
	{
		LogUtils.e("saveBitmap", "保存图片");
		String back = "-1";
		try
		{

			File f = null;
			if(Constants.IMG_PNG.equals(type))
			{
				f = getSaveFile(Constants.imgCachePath + File.separator
						+ "cache", picName + ".png");
			}
			else if(Constants.IMG_JPG.equals(type))
			{
				f = getSaveFile(Constants.imgCachePath + File.separator
						+ "cache", picName + ".jpg");
			}
			if(f.exists())
			{
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);

			if(Constants.IMG_PNG.equals(type))
			{
				bm.compress(CompressFormat.PNG, 90, out);
			}
			else if(Constants.IMG_JPG.equals(type))
			{
				bm.compress(CompressFormat.JPEG, 90, out);
			}
			out.flush();
			out.close();
			back = f.getPath();
			LogUtils.e("saveBitmap", "已经保存" + f.getPath());
		}
		catch (FileNotFoundException e)
		{
			LogUtils.e("saveBitmap", e.getMessage());
			e.printStackTrace();
			back = "-1";
			return back;
		}
		catch (IOException e)
		{
			LogUtils.e("saveBitmap", e.getMessage());
			e.printStackTrace();
			back = "-1";
			return back;
		}
		return back;
	}
	/**
	 * 图片写入文件
	 * 
	 * @param bitmap
	 *        图片
	 * @param filePath
	 *        文件路径
	 * @return 是否写入成功
	 */
	public static boolean bitmapToFile(Bitmap bitmap, String filePath)
	{
		boolean isSuccess = false;
		if (bitmap == null)
		{
			return isSuccess;
		}
		File file = new File(filePath.substring(0,
				filePath.lastIndexOf(File.separator)));
		if (!file.exists())
		{
			file.mkdirs();
		}
		
		OutputStream out = null;
		try
		{
			out = new BufferedOutputStream(new FileOutputStream(filePath),
					8 * 1024);
			isSuccess = bitmap.compress(CompressFormat.PNG, 100, out);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeIO(out);
		}
		return isSuccess;
	}

	/**
	 * 从文件中读取文本
	 * @param filePath
	 * @return String
	 */
	public static String readFile(String filePath)
	{
		InputStream is = null;
		try
		{
			is = new FileInputStream(filePath);
		}
		catch (Exception e)
		{
			throw new RuntimeException(FileUtils.class.getName()
					+ "readFile---->" + filePath + " not found");
		}
		return inputStream2String(is);
	}
	
	/**
	 * 从assets中读取文本
	 * 
	 * @param name
	 * @return String
	 */
	public static String readFileFromAssets(Context context, String name)
	{
		InputStream is = null;
		try
		{
			is = context.getResources().getAssets().open(name);
		}
		catch (Exception e)
		{
			throw new RuntimeException(FileUtils.class.getName()
					+ ".readFileFromAssets---->" + name + " not found");
		}
		return inputStream2String(is);
	}
	
	/**
	 * 输入流转字符串
	 * 
	 * @param is
	 * @return 一个流中的字符串
	 */
	public static String inputStream2String(InputStream is)
	{
		if (null == is)
		{
			return null;
		}
		StringBuilder resultSb = null;
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			resultSb = new StringBuilder();
			String len;
			while (null != (len = br.readLine()))
			{
				resultSb.append(len);
			}
		}
		catch (Exception ex)
		{
		}
		finally
		{
			closeIO(is);
		}
		return null == resultSb ? null : resultSb.toString();
	}
	
	/**
	 * 格式化单位
	 * 
	 * @param size
	 * @return string
	 */
	public static String getFormatSize(long size)
	{
		double kiloByte = size / 1024;
		if (kiloByte < 1)
		{
			return size + "KB";
		}
		
		double megaByte = kiloByte / 1024;
		if (megaByte < 1)
		{
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "KB";
		}
		
		double gigaByte = megaByte / 1024;
		if (gigaByte < 1)
		{
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "MB";
		}
		
		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1)
		{
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
				+ "TB";
	}

	/**
	 * 获取文件夹的大小
	 * @param file
	 * @return 大小
	 */
	public static long getFolderSize(File file)
	{
		if (StringUtils.isEmpty(file))
		{
			return 0;
		}
		long size = 0;
		try
		{
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++)
			{
				// 如果下面还有文件
				if (fileList[i].isDirectory())
				{
					size = size + getFolderSize(fileList[i]);
				}
				else
				{
					size = size + fileList[i].length();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return size;
		
	}

	/**
	 * 获取文件的大小
	 * @param file
	 * @return 大小
	 */
	public static long getFileSize(File file)
	{
		FileInputStream fis;
		int fileLen = 0;
		try
		{
			fis = new FileInputStream(file);
			fileLen = fis.available(); // 这就是文件大小
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return fileLen;
	}
	
	/*
	 * 递归删除文件和文件夹
	 * 
	 * @param file
	 * 要删除的根目录
	 */
	public static boolean deleteFile(File file)
	{
		if (file.exists() == false)
		{
			return false;
		}
		else
		{
			if (file.isFile())
			{
				return file.delete();
			}
			if (file.isDirectory())
			{
				File[] childFile = file.listFiles();
				if (childFile == null || childFile.length == 0)
				{
					return file.delete();
				}
				for (File f : childFile)
				{
					deleteFile(f);
				}
				return file.delete();
			}
		}
		return false;
	}
	
}