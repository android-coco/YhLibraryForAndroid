package org.yh.yhframe;

import com.google.gson.annotations.SerializedName;

import org.yh.library.bean.YHModel;

import java.util.Arrays;

/**
 * 作者：38314 on 2017/5/11 11:15
 * 邮箱：yh_android@163.com
 */
public class Person extends YHModel
{
    @SerializedName("data")
    private User[] users;

    public User[] getUsers()
    {
        return users;
    }

    public void setUsers(User[] users)
    {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "users=" + Arrays.toString(users) +
                "} " + super.toString();
    }
}
