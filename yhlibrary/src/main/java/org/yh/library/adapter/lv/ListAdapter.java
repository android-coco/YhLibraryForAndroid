package org.yh.library.adapter.lv;

/**
 * 普通Adapter一个布局
 *
 * @param <D>
 */
public abstract class ListAdapter<D> extends YHListAdapter<D>
{

    public ListAdapter(final int layoutId)
    {
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

    protected abstract void convert(YHListViewHolder<D> yhListViewHolder, D item, int position);

}
