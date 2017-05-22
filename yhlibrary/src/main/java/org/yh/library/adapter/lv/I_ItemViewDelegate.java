package org.yh.library.adapter.lv;


/**
 * Created by zhy on 16/6/22.
 * ItemView委托接口
 */
public interface I_ItemViewDelegate<D>
{
    //获取布局ID
    int getItemViewLayoutId();
    //是控件是item类型
    boolean isForViewType(D item, int position);
    //加载布局和数据
    void convert(YHListViewHolder holder, D item, int position);

}
