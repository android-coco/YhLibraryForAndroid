package org.yh.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yhlyl on 2017/5/15.
 * Holder的基类，都应该继承该类
 */

public abstract class YHHolder<D> extends RecyclerView.ViewHolder implements I_YHHolder<D>
{
    /**
     * Bean对象
     */
    protected D data;
    /**
     * 上下文
     */
    protected Context context;

    /**
     * 所在的Adapter
     *
     * @param itemView
     * @param context
     * @param adapter
     */
    protected RecyclerView.Adapter adapter;

    public YHHolder(View itemView, Context context, RecyclerView.Adapter adapter)
    {
        super(itemView);
        this.context = context;
        this.adapter = adapter;
    }

    public void setData(D data)
    {
        this.data = data;
        //把当前的数据设置到tag中
        this.itemView.setTag(data);
    }

    public D getData()
    {
        return data;
    }
}
