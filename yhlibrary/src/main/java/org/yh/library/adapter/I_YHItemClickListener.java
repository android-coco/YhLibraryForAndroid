package org.yh.library.adapter;

import android.view.View;

/**
 * 作者：38314 on 2017/5/15 13:17
 * 邮箱：yh_android@163.com
 */
public interface I_YHItemClickListener<T>
{

    /**
     * Recyclerview长按事件
     *
     * @param view     点击的View
     * @param data     点击的对象数据
     */
    void onItemLongClick(View view,T data);
    /**
     * Recyclerview点击事件
     *
     * @param view     点击的View
     * @param data     点击的对象数据
     */
    void onItemClick(View view, T data);
}
