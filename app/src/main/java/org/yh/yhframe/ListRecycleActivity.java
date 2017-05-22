package org.yh.yhframe;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.yh.library.adapter.lv.ListAdapter;
import org.yh.library.adapter.lv.YHListViewHolder;
import org.yh.library.ui.BindView;
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

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_list);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        mListView.setAdapter(new ListAdapter<String>(this, R.layout.item_list, mDatas)
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
        });

        mListView.setEmptyView(mEmptyView);
//        TextView t1 = new TextView(this);
//        t1.setText("Header 1");
//        TextView t2 = new TextView(this);
//        t2.setText("Header 2");
//        mListView.addHeaderView(t1);
//        mListView.addHeaderView(t2);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
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
}
