package org.yh.yhframe;

/**
 * 作者：38314 on 2017/5/11 13:31
 * 邮箱：yh_android@163.com
 */
public class Teacher
{
    private int id;

    private String name;

    private String title;

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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "Teacher [id=" + id + ", name=" + name + ", title=" + title
                + "]";
    }

}
