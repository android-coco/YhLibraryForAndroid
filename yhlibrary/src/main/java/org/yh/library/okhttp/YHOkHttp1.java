package org.yh.library.okhttp;

import org.json.JSONException;
import org.json.JSONObject;
import org.yh.library.okhttp.builder.PostFormBuilder;
import org.yh.library.okhttp.callback.FileCallBack;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.okhttp.callback.StringCallback;
import org.yh.library.utils.Constants;
import org.yh.library.utils.LogUtils;
import org.yh.library.utils.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import okhttp3.Call;
import okhttp3.Request;

import static org.yh.library.utils.StringUtils.isEmpty;

/**
 * 
 * @ClassName: OkHttpRequestManager
 * @Description: (对okhttp封装)
 * @author yh   耦合性太高  需要重新编写
 * @date 2016-7-18 下午4:49:41
 * 
 */
public class YHOkHttp1
{
	public static final int ERROR_4XX = 400;
	public static final int ERROR_5XX = 500;
	public static final int ERROR_OTHER = 1;
	public static final String ERROE_3001 = "30001";
	public static final String ERROE_3002 = "30002";
	public static final String ERROR_UNKNOWN = "unknown";
	
	private final static long readTimeOut = 10 * 1000L;
	private final static long writeTimeOut = 60 * 1000L;
	private final static long connTimeOut = 10 * 1000L;
	
	public static void get(final String host, String suffix,
						   final HttpCallBack callback, Object tag)
	{
		get(host, suffix, callback, tag, connTimeOut, readTimeOut,
				writeTimeOut, 1);
	}
	
	public static void get(final String host, String suffix,
			final HttpCallBack callback, final long connTimeOut,
			final long readTimeOut, final long writeTimeOut, Object tag)
	{
		get(host, suffix, callback, tag, connTimeOut, readTimeOut,
				writeTimeOut, 1);
	}
	
	private static void get(final String host, final String suffix,
			final HttpCallBack callback, final Object tag,
			final long connTimeOut, final long readTimeOut,
			final long writeTimeOut, final int control)
	{
		String prefix = "";
		
		Map<String, String> mapUrls = null;
		if (!isEmpty(host) && !host.equals(Constants.HOST_MAIN))
		{
			//mapUrls = getHost();
			prefix = mapUrls.get(host);
		}
		if (host.equals(Constants.HOST_MAIN))
		{
			prefix = Constants.HOST_MAIN;
		}
		String url = "";
		url = prefix + suffix;
		LogUtils.e("GET请求host：", url + "   ");
		
		final String url1 = url;
		// 组成的URL 为空或者不是http或https开头都是非法URL
		if (isEmpty(url1)
				|| (!url1.startsWith(Constants.FILE_HTTP) && !url1
						.startsWith(Constants.FILE_HTTPS)))
		{
			callback.onFailure(-3, "非法URL");
			callback.onFinish();
			return;
		}
		OkHttpUtils.get().url(url1).tag(tag).build().connTimeOut(connTimeOut)
				.readTimeOut(readTimeOut).writeTimeOut(writeTimeOut)
				.execute(new StringCallback()
				{
					
					@Override
					public void onResponse(String response, int id)
					{
						try
						{
							JSONObject json = new JSONObject(response);
							String ret = json.getString(Constants.RESULT);
							if (ERROE_3001.equals(ret)
									|| ERROE_3002.equals(ret))
							{
								//相关操作
//								EventBus.getDefault().post(
//										new EventBusBean(new Intent(
//												AppConfig.ACTION_PLOGIN_OUT)));
//								EventBus.getDefault().post(
//										new EventBusBean(new Intent(
//												AppConfig.ACTION_AGAIN_TOKEN)));
							}
							else
							{
								callback.onSuccess(response);
								callback.onFinish();
							}
						}
						catch (JSONException e)
						{
							callback.onSuccess(response);
							callback.onFinish();
						}
					}
					
					@Override
					public void onError(Call call, Exception e, int id)
					{
						LogUtils.e("OkHttpRequestManager", "GET请求URL：" + url1 + " 请求错误："
								+ e.getMessage() + "  " + id);
						String error = e.getMessage();
						if (!isEmpty(error))
						{
							if (error.startsWith("failed to connect to"))
							{
								callback.onFailure(-4, "连接超时");
								callback.onFinish();
								return;
							}
						}
						if (control == 1)
						{
							get(host, suffix, callback, tag, connTimeOut,
									readTimeOut, writeTimeOut, control + 1);
						}
						else if (control == 2)
						{
							if (isEmpty(host)
									|| host.equals(Constants.HOST_MAIN))
							{
								dealError(e, callback);
							}
							else
							{
								requestHostConfiguration(tag,
										new StringCallback()
										{
											
											@Override
											public void onResponse(
													String response, int id)
											{
												boolean x = jxHOST(response);
												if (x)
												{
													get(host, suffix, callback,
															tag, connTimeOut,
															readTimeOut,
															writeTimeOut,
															control + 1);
												}
												else
												{
													callback.onFailure(-2,
															"JSON解析异常");
													callback.onFinish();
												}
											}
											
											@Override
											public void onError(Call call,
													Exception e, int id)
											{
												dealError(e, callback);
											}
										});
							}
							
						}
						else if (control == 3)
						{
							dealError(e, callback);
						}
					}
				});
	}
	
	public static void post(final String host, String suffix,
			Map<String, String> params, final HttpCallBack callback,
			final long connTimeOut, final long readTimeOut,
			final long writeTimeOut, Object tag)
	{
		post(host, suffix, params, callback, tag, connTimeOut, readTimeOut,
				writeTimeOut, 1);
	}
	
	public static void post(final String host, String suffix,
			Map<String, String> params, final HttpCallBack callback, Object tag)
	{
		post(host, suffix, params, callback, tag, connTimeOut, readTimeOut,
				writeTimeOut, 1);
	}
	
	private static void post(final String host, final String suffix,
			final Map<String, String> params, final HttpCallBack callback,
			final Object tag, final long connTimeOut, final long readTimeOut,
			final long writeTimeOut, final int control)
	{
		String prefix = "";
		
		Map<String, String> mapUrls = null;
		if (!isEmpty(host) && !host.equals(Constants.HOST_MAIN))
		{
			//mapUrls = getHost();
			prefix = mapUrls.get(host);
		}
		if (host.equals(Constants.HOST_MAIN))
		{
			prefix = Constants.HOST_MAIN;
		}
		
		String url = "";
		url = prefix + suffix;
		LogUtils.e("post请求host：", url + "   第几次请求：" + control);
		final String url1 = url;
		
		// 组成的URL 为空或者不是http或https开头都是非法URL
		if (isEmpty(url1)
				|| (!url1.startsWith(Constants.FILE_HTTP) && !url1
						.startsWith(Constants.FILE_HTTPS)))
		{
			callback.onFailure(-3, "非法URL");
			callback.onFinish();
			return;
		}
		OkHttpUtils.post().url(url1).tag(tag).params(params).build()
				.connTimeOut(connTimeOut).readTimeOut(readTimeOut)
				.writeTimeOut(writeTimeOut).execute(new StringCallback()
				{
					
					@Override
					public void onResponse(String response, int id)
					{
						try
						{
							JSONObject json = new JSONObject(response);
							String ret = json.getString(Constants.RESULT);
							if (ERROE_3001.equals(ret)
									|| ERROE_3002.equals(ret))
							{
								//相关操作
//								EventBus.getDefault().post(
//									new EventBusBean(new Intent(
//											AppConfig.ACTION_PLOGIN_OUT)));
//								EventBus.getDefault().post(
//										new EventBusBean(new Intent(
//												AppConfig.ACTION_AGAIN_TOKEN)));
							}
							else
							{
								callback.onSuccess(response);
								callback.onFinish();
							}
						}
						catch (JSONException e)
						{
							callback.onSuccess(response);
							callback.onFinish();
						}
					}
					
					@Override
					public void onError(Call call, Exception e, int id)
					{
						// failed to connect to
						LogUtils.e("OkHttpRequestManager", "POST请求URL：" + url1 + " 请求错误："
								+ e.getMessage() + "  " + id);
						String error = e.getMessage();
						if (!isEmpty(error))
						{
							if (error.startsWith("failed to connect to"))
							{
								callback.onFailure(-4, "连接超时");
								callback.onFinish();
								return;
							}
						}
						if (control == 1)
						{
							post(host, suffix, params, callback, tag,
									connTimeOut, readTimeOut, writeTimeOut,
									control + 1);
						}
						else if (control == 2)
						{
							if (isEmpty(host)
									|| host.equals(Constants.HOST_MAIN))
							{
								dealError(e, callback);
							}
							else
							{
								requestHostConfiguration(tag,
										new StringCallback()
										{
											
											@Override
											public void onResponse(
													String response, int id)
											{
												boolean x = jxHOST(response);
												if (x)
												{
													post(host, suffix, params,
															callback, tag,
															connTimeOut,
															readTimeOut,
															writeTimeOut,
															control + 1);
												}
												else
												{
													callback.onFailure(-2,
															"JSON解析异常");
													callback.onFinish();
												}
											}
											
											@Override
											public void onError(Call call,
													Exception e, int id)
											{
												dealError(e, callback);
											}
										});
							}
						}
						else if (control == 3)
						{
							dealError(e, callback);
						}
					}
				});
	}
	
	public static void postForm(final String host, final String suffix,
			final Map<String, Object> params, final HttpCallBack callback,
			final Object tag)
	{
		postForm(host, suffix, params, callback, tag, connTimeOut, readTimeOut,
				writeTimeOut, 1);
	}
	
	public static void postForm(final String host, final String suffix,
			final Map<String, Object> params, final HttpCallBack callback,
			final long connTimeOut, final long readTimeOut,
			final long writeTimeOut, final Object tag)
	{
		postForm(host, suffix, params, callback, tag, connTimeOut, readTimeOut,
				writeTimeOut, 1);
	}
	
	private static void postForm(final String host, final String suffix,
			final Map<String, Object> params, final HttpCallBack callback,
			final Object tag, final long connTimeOut, final long readTimeOut,
			final long writeTimeOut, final int control)
	{
		String prefix = "";
		
		Map<String, String> mapUrls = null;
		if (!isEmpty(host) && !host.equals(Constants.HOST_MAIN))
		{
			//mapUrls = getHost();
			prefix = mapUrls.get(host);
		}
		if (host.equals(Constants.HOST_MAIN))
		{
			prefix = Constants.HOST_MAIN;
		}
		
		String url = "";
		url = prefix + suffix;
		LogUtils.e("postForm请求host：", url + "   第几次请求：" + control);
		
		final String url1 = url;
		// 组成的URL 为空或者不是http或https开头都是非法URL
		if (isEmpty(url1)
				|| (!url1.startsWith(Constants.FILE_HTTP) && !url1
						.startsWith(Constants.FILE_HTTPS)))
		{
			callback.onFailure(-3, "非法URL");
			callback.onFinish();
			return;
		}
		
		PostFormBuilder formbuilder = OkHttpUtils.post();
		formbuilder.url(url1).tag(tag);
		Iterator<Entry<String, Object>> paramsIterator = params.entrySet()
				.iterator();
		while (paramsIterator.hasNext())
		{
			Entry<String, Object> entry = paramsIterator.next();
			String key = entry.getKey();// key
			Object obj = entry.getValue();// 值
			if (obj instanceof File)
			{
				// 如果是文件
				File newFile = (File) obj;
				formbuilder.addFile(key, newFile.getName(), newFile);
			}
			else if (obj instanceof String)
			{
				// 如果是字符
				formbuilder.addParams(key, (String) obj);
			}
			LogUtils.e("postForm参数集：", "key:" + key + "   Value：" + obj);
		}
		formbuilder.build().connTimeOut(connTimeOut).readTimeOut(readTimeOut)
				.writeTimeOut(writeTimeOut)//
				.execute(new StringCallback()
				{
					
					@Override
					public void onResponse(String response, int id)
					{
						try
						{
							JSONObject json = new JSONObject(response);
							String ret = json.getString(Constants.RESULT);
							if (ERROE_3001.equals(ret)
									|| ERROE_3002.equals(ret))
							{
								//相关操作
//								EventBus.getDefault().post(
//										new EventBusBean(new Intent(
//												AppConfig.ACTION_PLOGIN_OUT)));
//								EventBus.getDefault().post(
//										new EventBusBean(new Intent(
//												AppConfig.ACTION_AGAIN_TOKEN)));
							}
							else
							{
								callback.onSuccess(response);
								callback.onFinish();
							}
						}
						catch (JSONException e)
						{
							callback.onSuccess(response);
							callback.onFinish();
						}
					}
					
					@Override
					public void onError(Call call, Exception e, int id)
					{
						LogUtils.e("OkHttpRequestManager", "postForm请求URL：" + url1
								+ " 请求错误：" + e.getMessage() + "  " + id);
						dealError(e, callback);
					}
					
					@Override
					public void onAfter(int id)
					{
						super.onAfter(id);
					}
				});
		
	}
	
	private static void dealError(Exception e, HttpCallBack callback)
	{
		String error = e.getMessage();
		if (isEmpty(error))
		{
			error = ERROR_UNKNOWN;
		}
		if (!isEmpty(error) && StringUtils.isNumber(error))
		{
			if (Integer.parseInt(error) >= ERROR_4XX)
			{
				callback.onFailure(ERROR_4XX, error);
			}
			else if (Integer.parseInt(error) >= ERROR_5XX)
			{
				callback.onFailure(ERROR_5XX, error);
			}
			else
			{
				callback.onFailure(ERROR_OTHER, error);
			}
		}
		else
		{
			callback.onFailure(ERROR_OTHER, error);
		}
		callback.onFinish();
	}
	
	public static void cancel(Object tag)
	{
		if (!isEmpty(OkHttpUtils.getInstance()))
		{
			OkHttpUtils.getInstance().cancelTag(tag);
		}
	}
	
	public static void cancelAll()
	{
		if (!isEmpty(OkHttpUtils.getInstance()))
		{
			OkHttpUtils.getInstance().cancelTagAll();
		}
	}
	//用于分布式获取主机地址
	public static void requestHostConfiguration(Object tag,
			StringCallback callback)
	{
		//String url = Constants.HOST_CONFIG + "&app_id=0";
		//OkHttpUtils.get().url(url).tag(tag).build().execute(callback);
	}
	
	// 设置配置
	private static boolean jxHOST(String json)
	{
		JSONObject response;
		try
		{
			response = new JSONObject(json);
			String ret = response.getString("result");
			if ("0".equals(ret))
			{
				HashMap<String, String> maps = new HashMap<String, String>();
				HashMap<String, String> mapDomin = new HashMap<String, String>();
				
				JSONObject hostsArray = response.getJSONObject("hosts");
				// ---------host_user------//
				JSONObject host_users = hostsArray.getJSONObject("host_user");
				String host_user = host_users.optString("ip");
				String user_domin = host_users.optString("type");
				// ---------host_cherishs------//
				JSONObject host_cherishs = hostsArray
						.getJSONObject("host_cherish");
				String host_cherish = host_cherishs.optString("ip");
				String cherish_domin = host_cherishs.optString("type");
				// ---------host_device------//
				JSONObject host_devices = hostsArray
						.getJSONObject("host_device");
				String host_device = host_devices.optString("ip");
				String device_domin = host_devices.optString("type");
				// ---------host_lbs------//
				JSONObject host_lbss = hostsArray.getJSONObject("host_lbs");
				String host_lbs = host_lbss.optString("ip");
				String lbs_domin = host_lbss.optString("type");
				// ---------host_apns------//
				JSONObject host_apnss = hostsArray.getJSONObject("host_apns");
				String host_apns = host_apnss.optString("ip");
				String apns_domin = host_apnss.optString("type");
				// ---------host_forum------//
				// JSONObject host_forums =
				// hostsArray.getJSONObject("host_forum");
				// String host_forum = host_forums.optString("ip");
				// String forum_domin = host_forums.optString("domain");
				// ---------host_mall------//
				// JSONObject host_malls =
				// hostsArray.getJSONObject("host_mall");
				// String host_mall = host_malls.optString("ip");
				// String mall_domin = host_malls.optString("domain");
				// ---------host_files------//
				JSONObject host_filess = hostsArray.getJSONObject("host_files");
				String host_files = host_filess.optString("ip");
				String files_domin = host_filess.optString("type");
				// ---------host_data------//
				JSONObject host_datas = hostsArray.getJSONObject("host_data");
				String host_data = host_datas.optString("ip");
				String data_domin = host_datas.optString("type");
				// ---------host_care------//
				// JSONObject host_cares =
				// hostsArray.getJSONObject("host_care");
				// String host_care = host_cares.optString("ip");
				// String care_domin = host_cares.optString("domain");
				// ---------host_im------//
				JSONObject host_ims = hostsArray.getJSONObject("host_im");
				String host_im = host_ims.optString("ip");
				String im_domin = host_ims.optString("type");
				// ---------host_udp------//
				JSONObject host_udps = hostsArray.getJSONObject("host_udp");
				String host_udp = host_udps.optString("ip");
				String udp_domin = host_udps.optString("type");
				// ------host_apns_udp-----//
				JSONObject host_apns_udps = hostsArray
						.getJSONObject("host_apns_udp");
				String host_apns_udp = host_apns_udps.optString("ip");
				String apns_udp_domin = host_apns_udps.optString("type");
				
				// ---------host_wechat------//
				JSONObject host_wechats = hostsArray
						.getJSONObject("host_wechat");
				String host_wachat = host_wechats.optString("ip");
				String wachat_domin = host_wechats.optString("type");
				
				JSONObject host_companys = hostsArray
						.getJSONObject("host_company");
				String host_company = host_companys.optString("ip");
				String company_domin = host_wechats.optString("type");
				
				maps.put("host_user", host_user);
				maps.put("host_cherish", host_cherish);
				maps.put("host_device", host_device);
				maps.put("host_lbs", host_lbs);
				maps.put("host_apns", host_apns);
				// maps.put("host_forum", host_forum);
				// maps.put("host_mall", host_mall);
				maps.put("host_files", host_files);
				maps.put("host_data", host_data);
				// maps.put("host_care", host_care);
				maps.put("host_im", host_im);
				maps.put("host_udp", host_udp);
				maps.put("host_apns_udp", host_apns_udp);
				maps.put("host_wachat", host_wachat);
				maps.put("host_company", host_company);
				
				mapDomin.put("host_user", user_domin);
				mapDomin.put("host_cherish", cherish_domin);
				mapDomin.put("host_device", device_domin);
				mapDomin.put("host_lbs", lbs_domin);
				mapDomin.put("host_apns", apns_domin);
				// mapDomin.put("host_forum", forum_domin);
				// mapDomin.put("host_mall", mall_domin);
				mapDomin.put("host_files", files_domin);
				mapDomin.put("host_data", data_domin);
				// mapDomin.put("host_care", care_domin);
				mapDomin.put("host_im", im_domin);
				mapDomin.put("host_udp", udp_domin);
				mapDomin.put("host_apns_udp", apns_udp_domin);
				mapDomin.put("host_wachat", wachat_domin);
				mapDomin.put("company_domin", company_domin);
				
//				if (!YHUtils.isEmpty(Constants.Config.db))
//				{
//					Constants.Config.db.delete(BaseUrl.class);
//					BaseUrl baseUrl = new BaseUrl(maps, mapDomin);
//					Constants.Config.db.save(baseUrl);
//				}
				return true;
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
//	// 得到配置
//	private static Map<String, String> getHost()
//	{
//		if (!isEmpty(Constants.Config.db))
//		{
//			ArrayList<BaseUrl> baseUrls = Constants.Config.db
//					.query(BaseUrl.class);
//			if (!StringUtils.isEmpty(baseUrls))
//			{
//				// LogUtils.e("APPconfig", baseUrls + " " +
//				// Constants.Config.db);
//				HashMap<String, String> mapUrls = baseUrls.get(0).getUrls();
//				if (!StringUtils.isEmpty(mapUrls))
//				{
//					String host_udp = mapUrls.get("host_udp");
//					String aps_host_udp = mapUrls.get("host_apns_udp");// 苹果用UDP
//					if (!StringUtils.isEmpty(host_udp))
//					{
//						AppConfig.HOST_UDP = host_udp.split(":")[0];
//						AppConfig.UDP_PORT = Integer.parseInt(host_udp
//								.split(":")[1]);
//					}
//					if (!StringUtils.isEmpty(aps_host_udp))
//					{
//						AppConfig.HOST_APNS_UDP = aps_host_udp.split(":")[0];
//						AppConfig.APNS_UDP_PORT = Integer.parseInt(aps_host_udp
//								.split(":")[1]);
//					}
//					return mapUrls;
//				}
//			}
//		}
//		return new HashMap<String, String>();
//	}
	
	/**
	 * 
	 * @Title: download
	 * @Description: 下载文件
	 * @param @param url 下载地址
	 * @param @param path 文件路径
	 * @param @param fileName 文件名称
	 * @param @param callback 回调
	 * @param @param tag
	 * @author yh
	 * @return void 返回类型
	 * @throws
	 */
	public static void download(String url, final String path, String fileName,
			final HttpCallBack callback, final long connTimeOut,
			final long readTimeOut, final long writeTimeOut, String tag)
	{
		LogUtils.e("GET请求视屏：", url + "   ");
		OkHttpUtils.get().tag(tag).url(url).build().connTimeOut(connTimeOut)
				.readTimeOut(readTimeOut).writeTimeOut(writeTimeOut)
				.execute(new FileCallBack(path, fileName)
				{
					@Override
					public void onBefore(Request request, int id)
					{
						callback.onBefore(request, id);
					}
					
					@Override
					public void inProgress(float progress, long total, int id)
					{
						callback.onLoading(progress, total, id);
					}
					
					@Override
					public void onResponse(File response, int id)
					{
						callback.onSuccess(response, id);
					}
					
					@Override
					public void onError(Call call, Exception e, int id)
					{
						dealError(e, callback);
					}
				});
	}
	
	/**
	 * 
	 * @Title: download
	 * @Description: 下载
	 * @param @param url
	 * @param @param path
	 * @param @param fileName
	 * @param @param callback
	 * @param @param tag
	 * @author yh
	 * @return void 返回类型
	 * @throws
	 */
	public static void download(String url, final String path, String fileName,
			final HttpCallBack callback, String tag)
	{
		LogUtils.e("GET请求视屏：", url + "   ");
		OkHttpUtils.get().tag(tag).url(url).build().connTimeOut(connTimeOut)
				.readTimeOut(readTimeOut).writeTimeOut(writeTimeOut)
				.execute(new FileCallBack(path, fileName)
				{
					@Override
					public void onBefore(Request request, int id)
					{
						callback.onBefore(request, id);
					}
					
					@Override
					public void inProgress(float progress, long total, int id)
					{
						callback.onLoading(progress, total, id);
					}
					
					@Override
					public void onResponse(File response, int id)
					{
						callback.onSuccess(response, id);
					}
					
					@Override
					public void onError(Call call, Exception e, int id)
					{
						dealError(e, callback);
					}
				});
	}
	
}
