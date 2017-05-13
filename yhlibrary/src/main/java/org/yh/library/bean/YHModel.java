/**
 * 
 */
package org.yh.library.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

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
    public static final String COL_ID = "_id";
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column(COL_ID)
    protected long id;
}
