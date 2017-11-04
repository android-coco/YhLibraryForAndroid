[ ![Download](https://api.bintray.com/packages/androidcoco/maven/WatchLib/images/download.svg?version=1.0.4) ](https://bintray.com/androidcoco/maven/WatchLib/1.0.4/link)

# 手表定位SDK使用说明(非YhLibraryForAndroid说明)

标签（空格分隔）： 学习

---

## 1，环境要求

 - AndroidStuido 一台可以上网的电脑 Liunx/mac/windows
 
## 2, 项目引入
当前使用model的bulid.gradle 最后加入  x.x.x为版本号请使用最新稳定版当前编写文档时最新版本1.0.1
```java
    compile 'org.yh.yhframe:WatchLib:last_version'
```
## 3，配置
项目的bulid.gradle
```java
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
``` 
## 4，百度地图Key配置
```JAVA
<!-- 百度地图Key -->
        <meta-data
            android:name="com.baidu.lbsapi.API_KEY"
            android:value="你自己的key百度申请">
```
## 5，同步项目
## 6，新建一个普通的Activiy
布局就一个Button
加上点击事件
```JAVA
Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ok = (Button) findViewById(R.id.ok);
        ok.setText("普通Activity调用");
        ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //SDK调用位置
                GlobalUtils.HOME_HOST="http://115.159.123.101:8085";//接口地址
                GlobalUtils.DEIVER_SN = "123456789012345";//SN号
                showActivity(Main2Activity.this, LocationActivity.class);
            }
        });
    }

    public void showActivity(Activity aty, Class<?> cls)
    {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }
```
## 7，运行项目完成

## 8，下面与SDK调用无关
由于此SDK使用了自己编写的一个开源框架，所以使用此SDK就可以使用本框架的所有功能
框架地址：里面有使用说明
[框架地址][1]


  [1]: https://github.com/android-coco/YhLibraryForAndroid
