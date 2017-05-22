package org.yh.library.adapter.rv;

import android.support.v4.util.SparseArrayCompat;


/**
 * Created by zhy on 16/6/22.
 * ItemView委托管理
 */
public class ItemViewDelegateManager<D>
{
    SparseArrayCompat<I_ItemViewDelegate<D>> delegates = new SparseArrayCompat();

    public int getItemViewDelegateCount()
    {
        return delegates.size();
    }

    public ItemViewDelegateManager<D> addDelegate(I_ItemViewDelegate<D> delegate)
    {
        int viewType = delegates.size();
        if (delegate != null)
        {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    public ItemViewDelegateManager<D> addDelegate(int viewType, I_ItemViewDelegate<D> delegate)
    {
        if (delegates.get(viewType) != null)
        {
            throw new IllegalArgumentException(
                    "An I_ItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered I_ItemViewDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    public ItemViewDelegateManager<D> removeDelegate(I_ItemViewDelegate<D> delegate)
    {
        if (delegate == null)
        {
            throw new NullPointerException("I_ItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemViewDelegateManager<D> removeDelegate(int itemType)
    {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(D item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--)
        {
            I_ItemViewDelegate<D> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position))
            {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No I_ItemViewDelegate added that matches position=" + position + " in data " +
                        "source");
    }

    public void convert(YHRecyclerViewHolder holder, D item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            I_ItemViewDelegate<D> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(item, position))
            {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data " +
                        "source");
    }


    public I_ItemViewDelegate getItemViewDelegate(int viewType)
    {
        return delegates.get(viewType);
    }

    public int getItemViewLayoutId(int viewType)
    {
        return getItemViewDelegate(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(I_ItemViewDelegate itemViewDelegate)
    {
        return delegates.indexOfValue(itemViewDelegate);
    }
}
