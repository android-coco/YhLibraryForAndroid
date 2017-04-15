package org.yh.library.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;
import java.util.Objects;

/**
 * Created by yhlyl on 2017/4/16.
 * LiteOrm的封装
 * https://github.com/litesuits/android-lite-orm
 */

public class YhDBManager
{
    private static YhDBManager manager;
    private LiteOrm liteOrm;

    private YhDBManager(Context context, String dbName,boolean isDebug)
    {
        if (liteOrm == null)
        {
            liteOrm = LiteOrm.newSingleInstance(context, dbName);
        }
        liteOrm.setDebugged(isDebug); // open the log
    }

    public static YhDBManager getInstance(Context context, String dbName,boolean isDebug)
    {
        context = context.getApplicationContext();
        if (manager == null)
        {
            synchronized (YhDBManager.class)
            {
                if (manager == null)
                {
                    manager = new YhDBManager(context, dbName,isDebug);
                }
            }
        }
        return manager;
    }

    /**
     * 插入一条记录
     */
    public <T> long insert(T t)
    {
        return liteOrm.save(t);
    }

    /**
     * 插入所有记录
     */
    public <T> void insertAll(List<T> list)
    {
        liteOrm.save(list);
    }

    /**
     * 根据对象查询数据
     */
    public <T> int update(T t)
    {
        return liteOrm.update(t);
    }

    /**
     * 查询所有数据
     */
    public <T> int updateAll(List<T> list)
    {
        return liteOrm.update(list);
    }

    /**
     * 根据id查询
     */
    public <T> T query(long id, Class<T> clazz)
    {
        return liteOrm.queryById(id, clazz);
    }

    /**
     * 查询所有
     */
    public <T> List<T> queryAll(Class<T> cla)
    {
        return liteOrm.query(cla);
    }

    /**
     * 查询  某字段 等于 Value的值
     *
     * @param cla
     * @param field
     * @param value
     * @return
     */
    public <T> List<T> getQueryByWhere(Class<T> cla, String field, Objects value)
    {
        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", value));
    }

    /**
     * 查询  某字段 等于 Value的值  可以指定从1-20，就是分页
     *
     * @param cla
     * @param field
     * @param value
     * @param start
     * @param length
     * @return
     */
    public <T> List<T> getQueryByWhereLength(Class<T> cla, String field, Objects value, int
            start, int length)
    {
        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", value).limit(start,
                length));
    }

    /**
     * 删除一个数据
     */
    public <T> void delete(T t)
    {
        liteOrm.delete(t);
    }

    /**
     * 删除所有数据
     */
    public <T> void deleteAll(Class<T> clazz)
    {
        liteOrm.deleteAll(clazz);
    }

    /**
     * 删除集合中的数据
     */
    public <T> void deleteList(List<T> list)
    {
        liteOrm.delete(list);
    }

    /**
     * 删除数据库
     */
    public void deleteDatabase()
    {
        liteOrm.deleteDatabase();
    }
}
