package org.yh.yhframe;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import org.yh.library.ui.BindView;
import org.yh.library.view.YHRecyclerView;
import org.yh.library.view.yhrecyclerview.ProgressStyle;

import java.util.ArrayList;

public class YHRecyclerviewActivity extends BaseActiciy
{
    @BindView(id = R.id.recyclerview)
    private YHRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_xrecyclerview);

    }

    @Override
    public void initData()
    {
        super.initData();
        listData = new ArrayList<>();
        mAdapter = new MyAdapter(listData);

    }

    @Override
    public void initWidget()
    {
        super.initWidget();

        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("XRecyclerview");
        toolbar.setMainTitleDrawable(R.mipmap.logo_white_210);
        toolbar.setRightTitleDrawable(R.mipmap.icon_home_menu_more);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        mRecyclerView.setFootViewText(getString(R.string.listview_loading),"没有更多");
        //mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mRecyclerView.setLoadingListener(new YHRecyclerView.LoadingListener()
        {
            @Override
            public void onRefresh()
            {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable()
                {
                    public void run()
                    {
                        listData.clear();
                        for (int i = 0; i < 15; i++)
                        {
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);
                //refresh data here
            }

            @Override
            public void onLoadMore()
            {
                if (times < 2)
                {
                    new Handler().postDelayed(new Runnable()
                    {
                        public void run()
                        {
                            for (int i = 0; i < 15; i++)
                            {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                else
                {
                    new Handler().postDelayed(new Runnable()
                    {
                        public void run()
                        {
                            for (int i = 0; i < 9; i++)
                            {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times++;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.refresh();
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        finish();
    }
}
