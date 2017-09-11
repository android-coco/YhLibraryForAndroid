package org.yh.yhframe.bean;

import com.google.gson.annotations.SerializedName;

import org.yh.library.bean.YHModel;

/**
 * 作者：游浩 on 2017/9/11 11:43
 * https://github.com/android-coco/YhLibraryForAndroid
 * 邮箱：yh_android@163.com
 */
public class UserModel extends YHModel
{
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
    @SerializedName("pass")
    private String pass;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", pass='" + pass + '\'' +
                "} ";
    }
}
