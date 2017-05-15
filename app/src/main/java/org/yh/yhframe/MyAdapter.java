package org.yh.yhframe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.yh.library.utils.StringUtils;
import org.yh.library.view.yhrecyclerview.YHItemClickListener;
import org.yh.yhframe.bean.MenuModel;

import java.util.ArrayList;

/**
 * Created by yhlyl on 2017/5/12.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener,View.OnLongClickListener
{
    public ArrayList<MenuModel> datas = null;
    private YHItemClickListener yhItemClickListener = null;

    public MyAdapter(ArrayList<MenuModel> datas)
    {
        this.datas = datas;
    }

    public void setYhItemClickListener(YHItemClickListener yhItemClickListener)
    {
        this.yhItemClickListener = yhItemClickListener;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup,
                false);
        final ViewHolder holder = new ViewHolder(view);
        //点击事件
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        MenuModel menuModel = datas.get(position);
        viewHolder.menu_name.setText(menuModel.getMenuname());
        viewHolder.menu_price.setText(menuModel.getPrice() + " 元");
        ImageLoader.getInstance().displayImage(menuModel.getPic(), viewHolder.menu_pic);
        //将position保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(position);
    }

    //获取数据的数量
    @Override
    public int getItemCount()
    {
        return datas.size();
    }

    @Override
    public void onClick(View v)
    {
        if (!StringUtils.isEmpty(yhItemClickListener))
        {
            yhItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v)
    {
        if (!StringUtils.isEmpty(yhItemClickListener))
        {
            yhItemClickListener.onItemLongClick(v,(int)v.getTag());
        }
        return true;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView menu_name;//菜品名称
        public TextView menu_price;//菜品价格
        public ImageView menu_pic;//菜品图片
        public ViewHolder(View view)
        {
            super(view);
            menu_name = (TextView) view.findViewById(R.id.menu_name);
            menu_price = (TextView) view.findViewById(R.id.menu_price);
            menu_pic = (ImageView) view.findViewById(R.id.menu_pic);
        }
    }
}
