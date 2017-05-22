package org.yh.library.adapter.lv;

import android.content.Context;

import java.util.List;

/**
 * 普通Adapter一个布局
 *
 * @param <D>
 */
public abstract class ListAdapter<D> extends YHListAdapter<D>
{
    protected List<D> datas;

    public ListAdapter(Context context, final int layoutId, List<D> datas)
    {
        super(context, datas);
        this.datas = datas;
        addItemViewDelegate(new I_ItemViewDelegate<D>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType(D item, int position)
            {
                return true;
            }

            @Override
            public void convert(YHListViewHolder holder, D item, int position)
            {
                ListAdapter.this.convert(holder, item, position);
            }
        });
    }

    protected abstract void convert(YHListViewHolder YHListViewHolder, D item, int position);

}
