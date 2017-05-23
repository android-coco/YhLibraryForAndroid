package org.yh.yhframe;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import org.yh.library.adapter.I_YHItemClickListener;
import org.yh.library.adapter.lv.ListAdapter;
import org.yh.library.adapter.lv.YHListViewHolder;
import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.yhframe.base.BaseActiciy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListRecycleActivity extends BaseActiciy
{
    private List<String> mDatas = new ArrayList<>(Arrays.asList("MultiItem ListView",
            "RecyclerView",
            "MultiItem RecyclerView"));
    @BindView(id = R.id.id_listview_list)
    private ListView mListView;
    @BindView(id = R.id.id_empty_view)
    private View mEmptyView;
    private ListAdapter<String> listAdapter;

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_list);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("ListRecycler");
        toolbar.setMainTitleDrawable(R.mipmap.logo_white_210);
        toolbar.setRightTitleDrawable(R.mipmap.icon_home_menu_more);


        listAdapter = new ListAdapter<String>(R.layout.item_list)
        {
            @Override
            public void convert(YHListViewHolder holder, String o, int pos)
            {
                holder.setText(R.id.id_item_list_title, o);
            }

            @Override
            public void onViewHolderCreated(YHListViewHolder holder, View itemView)
            {
                super.onViewHolderCreated(holder, itemView);
            }
        };
        mListView.setAdapter(listAdapter);
        listAdapter.setDatas(mDatas);
        mListView.setEmptyView(mEmptyView);
//        TextView t1 = new TextView(this);
//        t1.setText("Header 1");
//        TextView t2 = new TextView(this);
//        t2.setText("Header 2");
//        mListView.addHeaderView(t1);
//        mListView.addHeaderView(t2);

        listAdapter.setOnItemClickListener(new I_YHItemClickListener<String>()
        {
            @Override
            public boolean onItemLongClick(View view, String data, int position)
            {
                YHViewInject.create().showTips("长按：" + data + "   " + position);
                return false;
            }

            @Override
            public void onItemClick(View view, String data, int position)
            {
                Intent intent;
                switch (position)
                {
                    case 0:
                    default:
                        intent = new Intent(aty, ListActivity.class);
                        break;
                    case 1:
                        intent = new Intent(aty, YHRecyclerviewActivity.class);
                        break;
                    case 2:
                        intent = new Intent(aty, YHRecyclerviewActivity.class);
                        break;

                }
                if (intent != null)
                {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        finish();
    }
}
