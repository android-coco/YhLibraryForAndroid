package org.yh.yhframe.bean;

import com.google.gson.annotations.SerializedName;

import org.yh.library.bean.YHModel;

import java.util.List;

/**
 * Created by yhlyl on 2017/5/13.
 */

public class JsonMenuModel extends YHModel
{
    @SerializedName("result")
    protected String resultCode;//结果Code
    @SerializedName("totalpage")
    protected int totalPage;//最大页数
    @SerializedName("data")
    protected List<MenuModel> datas;//数据

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }

    public List<MenuModel> getDatas()
    {
        return datas;
    }

    public void setDatas(List<MenuModel> datas)
    {
        this.datas = datas;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    @Override
    public String toString()
    {
        return "JsonMenuModel{" +
                "resultCode='" + resultCode + '\'' +
                ", totalPage='" + totalPage + '\'' +
                ", datas=" + datas +
                '}';
    }
}
