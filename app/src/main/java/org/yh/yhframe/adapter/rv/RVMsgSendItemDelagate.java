package org.yh.yhframe.adapter.rv;


import android.widget.ImageView;

import org.yh.library.YHGlide;
import org.yh.library.adapter.rv.I_ItemViewDelegate;
import org.yh.library.adapter.rv.YHRecyclerViewHolder;
import org.yh.yhframe.R;
import org.yh.yhframe.app.MyApplication;
import org.yh.yhframe.bean.MenuModel;

/**
 * Created by zhy on 16/6/22.
 */
public class RVMsgSendItemDelagate implements I_ItemViewDelegate<MenuModel>
{

    @Override
    public int getItemViewLayoutId()
    {
        return R.layout.item;
    }

    @Override
    public boolean isForViewType(MenuModel item, int position)
    {
        return true;
    }

    @Override
    public void convert(YHRecyclerViewHolder holder, MenuModel item, int position)
    {
        holder.setText(R.id.menu_name, item.getMenuname());
        holder.setText(R.id.menu_price, item.getPrice() + " 元");
        YHGlide.getInstanse(MyApplication.getInstance()).loadImgeForUrl(item.getPic(), (ImageView) holder.getView(R.id.menu_pic));
    }
}
