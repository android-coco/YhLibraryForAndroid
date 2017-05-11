/**
 * 
 */
package org.yh.library.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**   
 *    
 * 项目名称：healthplus   
 * 类名称：YHModel
 * 类描述：   实体类最大父类
 * 创建人：hao   
 * 创建时间：2015-5-6 下午3:39:03   
 * 修改人：hao   
 * 修改时间：2015-5-6 下午3:39:03   
 * 修改备注：   
 * @version 1.0
 *    
 */
@SuppressWarnings("serial")
public class YHModel implements Serializable
{
    //结果
    @SerializedName("result")
	protected String result;

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "YHModel{" +
                "result='" + result + '\'' +
                '}';
    }
}
