# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/yhlyl/java/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#运行时权限判断混淆
-dontwarn com.zhy.m.**
-keep class com.zhy.m.** {*;}
-keep interface com.zhy.m.** { *; }
-keep class **$$PermissionProxy { *; }
#事件总线混淆
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(Java.lang.Throwable);
}
## ----------------------------------
##      高德地图sdk混淆
## ----------------------------------
-libraryjars   libs/android-support-v4.jar
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

-libraryjars   libs/MapApiLocation.jar
-libraryjars   libs/MapApiSearch.jar
-libraryjars   libs/Android_Map_V2.0.4.jar

-dontwarn com.amap.api.**
-dontwarn com.a.a.**
-dontwarn com.autonavi.**

-keep class com.amap.api.**  {*;}
-keep class com.autonavi.**  {*;}
-keep class com.a.a.**  {*;}
