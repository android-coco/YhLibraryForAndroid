package org.yh.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yhlyl on 2017/5/15.
 * 说明：适配器相关的接口，与RecyclerView配套使用
 * D 数据
 * H Holder
 */

public interface I_BaseAdapter<D, H>
{
    /**
     * 返回布局，无论是动态创建还是加载布局都可以
     *
     * @param viewGroup
     * @return
     */
    View getLayoutView(ViewGroup viewGroup, int viewType);

    /**
     * 返回布局id
     *
     * @return
     */
    int getLayoutId(int viewType);

    /**
     * 创建ViewHolder
     *
     * @param itemView
     * @return
     */
    H createViewHolder(View itemView, Context context, int viewType);

    /**
     * 获得数据集
     *
     * @return
     */
    List<D> getDatas();

    /**
     * 设置数据集 刷新用
     *
     * @param dataes
     */
    void setDatas(List<D> dataes);

    /**
     * 添加数据集 加载更多用
     *
     * @param dataes
     */
    void addDatas(List<D> dataes);
}
