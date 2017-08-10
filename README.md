## YhLibraryForAndroid
Android实用框架采用MVC设计模式,多个项目经验总结,持续完善中

包括：<br>
     1,OKhttp简单封装<br>
     2,Orm 数据库<br>
     3,Universal-Image-Loader<br>
     4,EventBus<br>
     5,YHRecyclerView 下拉,上拉
     6,BindView 控件绑定
     7,YHGlide  图片框架
     8,YHWebView 自定义View 
     9,BindView 控件绑定
     10,YHVideoPlayer 视频播放器

注，需要在AndroidManifest.xml 中声明如下权限

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```
## 引入

* Gradle
```
compile 'org.yh.yhframe:YhLibraryForAndroid:1.0.1'
```

* Maven
```
<dependency>
  <groupId>org.yh.yhframe</groupId>
  <artifactId>YhLibraryForAndroid</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
 ```
## base包说明：
```
1.BaseActiciy    所有Acticiy的基类
2.BaseFragment   所有Fragment的基类
```
## 对Utils的说明：
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
 ## view包说明：
 ```
 1.YhToolbar     标题栏
  <include layout="@layout/basetitle"/>
 2.YHWebView     继承WebView
  HTML5WebViewCustomAD extends BaseActiciy
  {
    //....
  }
 ```
 
 ## 数据库操作
 ```
 1.YhDBManager.java
 2.Constants.Config.yhDBManager
 Constants.Config.yhDBManager = YhDBManager.getInstance(mInstance,"yh.db",true);
 Constants.Config.yhDBManager.insertAll(mAdapter.getDatas());
 ```
 ## RecyclerView和Adapter,Holder
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
 ## 网络操作
 ```
 1.YHRequestFactory.java
  //url 分为2部分  头和后缀
  //headers  请求头  Map<String,String>
  YHRequestFactory.getRequestManger().get("", "",headers, new HttpCallBack()
         {
 
             @Override
             public void onSuccess(String t)
             {
                 super.onSuccess(t);
                 LogUtils.e(TAG, t);
             }
 
             @Override
             public void onFailure(int errorNo, String strMsg)
             {
                 super.onFailure(errorNo, strMsg);
                 LogUtils.e(TAG, strMsg);
             }
         }, TAG);
 ```
 ## 图片操作
 ```
 1.YHGlide-->Glide
 YHGlide.getInstanse(MyApplication.getInstance()).loadImgeForUrl(item.getPic(), (ImageView) holder.getView(R.id.menu_pic));
 ```
 ## License
 ```
 Copyright (C)  Justson(https://github.com/android-coco/YhLibraryForAndroid)
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ```
 
 
