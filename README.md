## YhLibraryForAndroid
Android实用框架采用MVC设计模式,多个项目经验总结,持续完善中

包括：<br>
     1,OKhttp简单封装<br>
     2,Orm 数据库<br>
     3,Universal-Image-Loader<br>
     4,EventBus<br>
     5,YHRecyclerView 下拉,上拉<br>
     6,BindView 控件绑定<br>
     7,YHGlide  图片框架<br>
     8,YHWebView 自定义View <br>
     9,BindView 控件绑定<br>
     10,YHVideoPlayer 视频播放器<br>

注，需要在AndroidManifest.xml 中声明如下权限

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```
## 引入

* Gradle
```
compile 'org.yh.yhframe:YhLibraryForAndroid:1.0.3'
```

* Maven
```
<dependency>
  <groupId>org.yh.yhframe</groupId>
  <artifactId>YhLibraryForAndroid</artifactId>
  <version>1.0.2</version>
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
 https://github.com/android-coco/AgentWeb
 2.YHWebView     继承WebView
      HTML5WebViewCustomAD extends BaseActiciy
      {
        //....
      }
 https://github.com/android-coco/LoadingBar
 3.LoadingBar
     //默认样式 loading将会覆盖在parent的内容上面
     LoadingBar.make(parent).show();
     //自定义样式
     //提供两种形式,loadingView更简便,loadingFactory自由度更高
     LoadingBar.make(parent,loadingView).show();
     LoadingBar.make(parent,loadingFactory).show();
     //完全自定义
     LoadingBar.make(parent,loadingFactory)
             .setOnClickListener(clickListener)//点击事件
             .setOnLoadingBarListener(loadingBarListener)//当loadingbar取消的时候回调
             .show();
     //取消Loading
     LoadingBar.cancel(parent);
 4.LoadingDialog
     //默认样式
     LoadingDialog.make(context).show();
     
     //自定义样式
     LoadingDialog.make(context, dialogFactory).show();
     
     //完全自定义
     LoadingDialog.make(context, dialogFactory)
                .setMessage(message)//提示消息
                .setCancelable(cancelable)
                .show();
     
     //设置更多属性
     Dialog dialog = LoadingDialog.make(context, dialogFactory)
                .setMessage(message)//提示消息
                .setCancelable(cancelable)
                .create();
     dialog.setOnCancelListener(cancelListener);
     dialog.set...
     dialog.show();
     //取消Loading
     LoadingDialog.cancel();
  5.自定义Factory
      public class CustomLoadingFactory implements LoadingFactory {
      
          @Override
          public View onCreateView(ViewGroup parent) {
              View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_custom, parent,false);
              return loadingView;
          }
      }
     全局配置
     //自定义样式并应用于全局
     LoadingConfig.setFactory(loadingFactory,dialogFactory);
     资源释放
     其实LoadingBar在cancel的时候已经释放掉了，可以不用手动释放，但是这里也提供释放的方法，根据自己需要选择
     在Activity onDestroy调用，个人建议在BaseActivity，资源释放只会释放无效的资源
     LoadingBar.release();
 ```
 
 ## 数据库操作
 ```
 1.YhDBManager.java
 2.Constants.Config.yhDBManager
 Constants.Config.yhDBManager = YhDBManager.getInstance(mInstance,"yh.db",true);
 Constants.Config.yhDBManager.insertAll(mAdapter.getDatas());
 ```
 
 ## android 6.0权限判断
 ```
  Activity extends BaseActiciy
  requestRunTimePermission(new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, new I_PermissionListener()
                    {
                        @Override
                        public void onSuccess()//所有权限OK
                        {
                            YHViewInject.create().showTips("授权成功");
                            //直接执行相应操作了
                        }
    
                        @Override
                        public void onGranted(List<String> grantedPermission)//部分权限OK
                        {
                        }
    
                        @Override
                        public void onFailure(List<String> deniedPermission)//全部拒绝
                        {
                            YHViewInject.create().showTips("拒绝授权列表：" + Constants.initPermissionNames().get(deniedPermission.get(0)));
                        }
                    });
                    
  Fragment extends BaseFragment
  requestRunTimePermission(new String[]{Manifest.permission.CAMERA}, new I_PermissionListener()
                {
                    @Override
                    public void onSuccess()
                    {
                        YHViewInject.create().showTips("授权成功");
                        //直接执行相应操作了
                        outsideAty.showActivity(outsideAty, HTML5WebViewCustomAD.class);
                    }

                    @Override
                    public void onGranted(List<String> grantedPermission)
                    {

                    }

                    @Override
                    public void onFailure(List<String> deniedPermission)
                    {
                        YHViewInject.create().showTips("您没有授权" + Constants.initPermissionNames().get(deniedPermission.get(0)) + "权限，请在设置中打开授权");
                    }
                });

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
 
 
