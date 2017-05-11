package org.yh.yhframe;

import org.yh.library.bean.YHModel;

import java.util.Date;

/**
 * 作者：38314 on 2017/5/11 13:30
 * 邮箱：yh_android@163.com
 */
public class Student extends YHModel
{
    private int id;
    private String name;
    private Date birthDay;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getBirthDay()
    {
        return birthDay;
    }

    public void setBirthDay(Date birthDay)
    {
        this.birthDay = birthDay;
    }

    @Override
    public String toString()
    {
        return "Student [birthDay=" + birthDay + ", id=" + id + ", name="
                + name + "]";
    }
}
