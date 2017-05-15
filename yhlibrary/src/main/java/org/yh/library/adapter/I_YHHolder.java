package org.yh.library.adapter;

/**
 * Created by yhlyl on 2017/5/15.
 */

public interface I_YHHolder<D>
{
    /**
     * 加载Holder，子类应该去重写
     *
     * @param position 所在的位置
     */
    void bindHolder(int position);

    /**
     * 设置数据
     *
     * @param data
     */
    void setData(D data);

    /**
     * 获得数据
     *
     * @return
     */
    D getData();
}
