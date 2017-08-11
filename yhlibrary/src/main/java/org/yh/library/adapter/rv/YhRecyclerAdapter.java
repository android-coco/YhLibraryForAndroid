package org.yh.library.adapter.rv;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.yh.library.adapter.I_YHItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhy on 16/4/9.
 * RecyclerAdapter
 */
public class YhRecyclerAdapter<D> extends RecyclerView.Adapter<YHRecyclerViewHolder>
{
    protected List<D> mDatas = new ArrayList<>();

    protected ItemViewDelegateManager mItemViewDelegateManager;
    protected I_YHItemClickListener<D> mOnItemClickListener;


    public YhRecyclerAdapter()
    {
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public int getItemViewType(int position)
    {
        if (!useItemViewDelegateManager())
        {
            return super.getItemViewType(position);
        }
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
    }


    @Override
    public YHRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        I_ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate
                (viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        YHRecyclerViewHolder holder = YHRecyclerViewHolder.createViewHolder(parent.getContext(), parent,
                layoutId);
        onViewHolderCreated(holder, holder.getConvertView());
        return holder;
    }

    public void onViewHolderCreated(YHRecyclerViewHolder holder, View itemView)
    {

    }

    public void convert(YHRecyclerViewHolder holder, D item)
    {
        mItemViewDelegateManager.convert(holder, item, holder.getAdapterPosition());
        holder.setData(item);
    }

    protected boolean isEnabled(int viewType)
    {
        return true;
    }


    protected void setListener(final YHRecyclerViewHolder viewHolder,final int postion)
    {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mOnItemClickListener != null)
                {
                    mOnItemClickListener.onItemClick(v, (D) v.getTag(), postion);
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
                    return mOnItemClickListener.onItemLongClick(v, (D) v.getTag(), postion);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(YHRecyclerViewHolder holder, int position)
    {
        setListener(holder, position);
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount()
    {
        if (mDatas == null || mDatas.size() == 0)
        {
            return 0;
        } else
        {
            return mDatas.size();
        }
    }


    public YhRecyclerAdapter addItemViewDelegate(I_ItemViewDelegate<D> itemViewDelegate)
    {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public YhRecyclerAdapter addItemViewDelegate(int viewType, I_ItemViewDelegate<D>
            itemViewDelegate)
    {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager()
    {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }


    public void setOnItemClickListener(I_YHItemClickListener<D> onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
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
