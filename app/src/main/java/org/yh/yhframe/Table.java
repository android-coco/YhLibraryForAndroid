package org.yh.yhframe;

import com.google.gson.annotations.SerializedName;

import org.yh.library.bean.YHModel;

import java.util.List;

/**
 * 作者：38314 on 2017/5/11 16:05
 * 邮箱：yh_android@163.com
 */
public class Table extends YHModel
{
    @SerializedName("tableName")
    private String name;
    @SerializedName("tableData")
    private List dataList;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List getDataList()
    {
        return dataList;
    }

    public void setDataList(List dataList)
    {
        this.dataList = dataList;
    }

    @Override
    public String toString()
    {
        return "Table{" +
                "name='" + name + '\'' +
                ", dataList=" + dataList +
                "} " + super.toString();
    }
}
