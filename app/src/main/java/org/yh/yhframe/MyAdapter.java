package org.yh.yhframe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.yh.library.adapter.YHAdapter;
import org.yh.library.adapter.YHHolder;
import org.yh.yhframe.bean.MenuModel;

/**
 * Created by yhlyl on 2017/5/12.
 */

public class MyAdapter extends YHAdapter<MenuModel>
{
    @Override
    public View getLayoutView(ViewGroup viewGroup, int viewType)
    {
        return null;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public int getLayoutId(int viewType)
    {
        return R.layout.item;
    }

    @Override
    public YHHolder createViewHolder(View itemView, Context context, int viewType)
    {
        return new MyHolder(itemView, context, this);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public class MyHolder extends YHHolder<MenuModel>
    {
        public TextView menu_name;//菜品名称
        public TextView menu_price;//菜品价格
        public ImageView menu_pic;//菜品图片

        public MyHolder(View itemView, Context context, RecyclerView.Adapter adapter)
        {
            super(itemView, context, adapter);
            menu_name = (TextView) itemView.findViewById(R.id.menu_name);
            menu_price = (TextView) itemView.findViewById(R.id.menu_price);
            menu_pic = (ImageView) itemView.findViewById(R.id.menu_pic);
        }
        //将数据与界面进行绑定的操作
        @Override
        public void bindHolder(int position)
        {
            menu_name.setText(data.getMenuname());
            menu_price.setText(data.getPrice() + " 元");
            ImageLoader.getInstance().displayImage(data.getPic(), menu_pic);
        }
    }

}
