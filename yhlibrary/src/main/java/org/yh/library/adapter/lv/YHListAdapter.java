package org.yh.library.adapter.lv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.yh.library.adapter.I_YHItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class YHListAdapter<D> extends BaseAdapter
{
    protected List<D> mDatas = new ArrayList<>();

    private ItemViewDelegateManager mItemViewDelegateManager;
    protected I_YHItemClickListener<D> mOnItemClickListener;

    public YHListAdapter()
    {
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public YHListAdapter addItemViewDelegate(I_ItemViewDelegate<D> itemViewDelegate)
    {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    private boolean useItemViewDelegateManager()
    {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    @Override
    public int getViewTypeCount()
    {
        if (useItemViewDelegateManager())
        {
            return mItemViewDelegateManager.getItemViewDelegateCount();
        }
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position)
    {
        if (useItemViewDelegateManager())
        {
            int viewType = mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
            return viewType;
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        I_ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(mDatas
                .get(position), position);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        YHListViewHolder<D> viewHolder;
        if (convertView == null)
        {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent,
                    false);
            viewHolder = new YHListViewHolder(parent.getContext(), itemView, parent, position);
            viewHolder.mLayoutId = layoutId;
            onViewHolderCreated(viewHolder, viewHolder.getConvertView());
        } else
        {
            viewHolder = (YHListViewHolder<D>) convertView.getTag();
            viewHolder.mPosition = position;
        }
        setListener(viewHolder, position);
        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    protected void setListener(final YHListViewHolder<D> viewHolder, final int position)
    {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mOnItemClickListener != null)
                {
                    mOnItemClickListener.onItemClick(v, mDatas.get(position), position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                if (mOnItemClickListener != null)
                {
                    return mOnItemClickListener.onItemLongClick(v, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    public void setOnItemClickListener(I_YHItemClickListener<D> onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
    }

    protected void convert(YHListViewHolder<D> yhListViewHolder, D item, int position)
    {
        mItemViewDelegateManager.convert(yhListViewHolder, item, position);
    }

    public void onViewHolderCreated(YHListViewHolder<D> holder, View itemView)
    {
    }

    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    @Override
    public D getItem(int position)
    {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * 设置新的数据，刷新的时候使用
     *
     * @param datas
     */
    public final void setDatas(List<D> datas)
    {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    /**
     * 添加新的数据，加载更多时使用
     *
     * @param dataes
     */
    public final void addDatas(List<D> dataes)
    {
        this.mDatas.addAll(dataes);
        notifyDataSetChanged();
    }

    /**
     * 添加一个元素
     *
     * @param data
     */
    public final void addData(D data)
    {
        this.mDatas.add(data);
        notifyDataSetChanged();
    }

    /**
     * 获取集合
     *
     * @return list
     */
    public List<D> getDatas()
    {
        return mDatas;
    }

    public void setData(D data)
    {
        this.mDatas.clear();
        this.mDatas.add(data);
        notifyDataSetChanged();
    }

}
