package org.yh.library.adapter.rv;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 *
 */
public abstract class RecyclerAdapter<D> extends YhRecyclerAdapter<D>
{
    protected Context mContext;
    protected int mLayoutId;
    protected List<D> mDatas;
    protected LayoutInflater mInflater;

    public RecyclerAdapter(final Context context, final int layoutId, List<D> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

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
            public void convert(YHRecyclerViewHolder holder, D t, int position)
            {
                RecyclerAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(YHRecyclerViewHolder holder, D item, int position);


}
