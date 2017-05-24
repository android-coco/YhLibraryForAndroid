# YhLibraryForAndroid
Android实用框架采用MVC设计模式,多个项目经验总结,持续完善中

包括：<br>
     1,OKhttp简单封装<br>
     2,Orm 数据库<br>
     3,Universal-Image-Loader<br>
     4,EventBus<br>
     5,RecyclerView 下拉,上拉
     6,BindView 控件绑定

注，需要在AndroidManifest.xml 中声明如下权限

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```
#base包说明：
```
1.BaseActiciy    所有Acticiy的基类
2.BaseFragment   所有Fragment的基类
```
# 对Utils的说明：
```
 1.PreferenceUtils.java   对SharedPreferences的封装
 2.CipherUtils.java       对常用加密方法进行整理
 3.Constants.java         全局常量
 4.CrashHandler.java      异常捕捉
 5.DensityUtils.java      系统屏幕的一些操作
 6.FileUtils.java         文件与流处理工具类
 7.ImageUtils.java        图片工具类
 8.JsonUitl.java          json数据基于Gson
 9.LogUtils.java          Log日志工具类
 10.NetWorkUtils.java     网络工具类
 11.StringUtils.java      字符串操作
 12.SystemUtils.java      系统信息工具包
 13.ViewUtils.java        系统界面工具类(截屏)
 14.YHViewInject.java     侵入式View的调用工具类
 15.AnnotateUtil.java     注解工具类(View id绑定)
 ```
 #view包说明：
 ```
 1.YhToolbar     标题栏
  <include layout="@layout/basetitle"/>
 2.YHWebView     继承WebView
  HTML5WebViewCustomAD extends BaseActiciy
  {
    //....
  }
 ```
 
 #数据库操作
 ```
 1.YhDBManager.java
 2.Constants.Config.yhDBManager
 Constants.Config.yhDBManager = YhDBManager.getInstance(mInstance,"yh.db",true);
 Constants.Config.yhDBManager.insertAll(mAdapter.getDatas());
 ```
 #RecyclerView和Adapter,Holder
 ```
 1.YHAdapter<D>     adatper
 2.YHHolder<D>      holder
 3.YHRecyclerView   RecyclerView
 4.I_BaseAdapter<D, H>    适配器相关的接口，与RecyclerView配套使用
 5.I_YHHolder<D>       Holder相关接口
 6.I_YHItemClickListener<T>   事件接口

         LinearLayoutManager layoutManager = new LinearLayoutManager(this);
         layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
         mRecyclerView.addItemDecoration(mRecyclerView.new YHItemDecoration());//分割线
         mRecyclerView.setLayoutManager(layoutManager);
         mRecyclerView.setEmptyView(empty_layout);//没有数据的空布局
         mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
         mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
         mRecyclerView.setFootViewText(getString(R.string.listview_loading), "没有更多");
         //mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
 
         mAdapter = new MyAdapter();
         mAdapter.setOnItemClickListener(this);
 
         mRecyclerView.setLoadingListener(new YHRecyclerView.LoadingListener()
         {
             @Override
             public void onRefresh()
             {
                 //下拉
             }
 
             @Override
             public void onLoadMore()
             {
                //上拉
             }
         });
         mRecyclerView.setAdapter(mAdapter);
         mRecyclerView.refresh();
     }
   //刷新
   mAdapter.setDatas(list);
  //刷新完毕
   mRecyclerView.refreshComplete();
   
   //没有更多
   mRecyclerView.setNoMore(true);
   mAdapter.notifyDataSetChanged();
   
   //加载更多
   mAdapter.addDatas(list);
   //加载完毕
   mRecyclerView.loadMoreComplete();
 ```
 #网络操作
 ```
 1.YHRequestFactory.java
  YHRequestFactory.getRequestManger().get(url, "", new HttpCallBack()
         {
             @Override
             public void onSuccess(String t)
             {
                 super.onSuccess(t);
             }
 
             @Override
             public void onFailure(int errorNo, String strMsg)
             {
                 super.onFailure(errorNo, strMsg);
             }
 
             @Override
             public void onFinish()
             {
                 super.onFinish();
             }
         }, TAG);
     }
 ```
 # 图片操作
 ```
 1.ImageLoader
 ImageLoader.getInstance().displayImage(data.getPic(), menu_pic);
 ```
 
 
