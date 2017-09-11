package org.yh.yhframe.bean;

import com.google.gson.annotations.SerializedName;

import org.yh.library.bean.YHModel;

import java.util.List;

/**
 * Created by yhlyl on 2017/5/13.
 */

public class JsonLoginModel extends YHModel
{
    @SerializedName("result")
    protected String resultCode;//结果Code
    @SerializedName("error_msg")
    protected String msg;//内容
    @SerializedName("datas")
    protected List<UserModel> datas;//数据

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }

    public List<UserModel> getDatas()
    {
        return datas;
    }

    public void setDatas(List<UserModel> datas)
    {
        this.datas = datas;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return "JsonMenuModel{" +
                "resultCode='" + resultCode + '\'' +
                ", msg='" + msg + '\'' +
                ", datas=" + datas +
                '}';
    }
}
