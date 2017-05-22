package org.yh.library.adapter.lv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class YHListAdapter<D> extends BaseAdapter
{
    protected Context mContext;
    protected List<D> mDatas;

    private ItemViewDelegateManager mItemViewDelegateManager;


    public YHListAdapter(Context context, List<D> datas)
    {
        this.mContext = context;
        this.mDatas = datas;
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
        YHListViewHolder viewHolder;
        if (convertView == null)
        {
            View itemView = LayoutInflater.from(mContext).inflate(layoutId, parent,
                    false);
            viewHolder = new YHListViewHolder(mContext, itemView, parent, position);
            viewHolder.mLayoutId = layoutId;
            onViewHolderCreated(viewHolder, viewHolder.getConvertView());
        }
        else
        {
            viewHolder = (YHListViewHolder) convertView.getTag();
            viewHolder.mPosition = position;
        }


        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    protected void convert(YHListViewHolder YHListViewHolder, D item, int position)
    {
        mItemViewDelegateManager.convert(YHListViewHolder, item, position);
    }

    public void onViewHolderCreated(YHListViewHolder holder, View itemView)
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
     * @return
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
