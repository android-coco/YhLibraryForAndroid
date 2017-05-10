package org.yh.yhframe;

import org.yh.library.bean.YHModel;

/**
 * Created by yhlyl on 2017/5/11.
 */

public class User extends YHModel
{
    private String sex;
    private String username;
    private String pass;

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getUserName()
    {
        return username;
    }

    public void setUser(String user)
    {
        this.username = user;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "sex='" + sex + '\'' +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                "} " + super.toString();
    }
}
