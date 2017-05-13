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

    @Override
    public String toString()
    {
        return "JsonMenuModel{" +
                "resultCode='" + resultCode + '\'' +
                ", datas=" + datas +
                '}';
    }
}
