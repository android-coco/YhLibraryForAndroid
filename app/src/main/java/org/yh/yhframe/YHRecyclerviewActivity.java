package org.yh.yhframe;

import android.support.v7.widget.LinearLayoutManager;

import org.yh.library.okhttp.YHOkHttp;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.utils.Constants;
import org.yh.library.utils.JsonUitl;
import org.yh.library.utils.LogUtils;
import org.yh.library.utils.StringUtils;
import org.yh.library.view.YHRecycleViewDivider;
import org.yh.library.view.YHRecyclerView;
import org.yh.library.view.yhrecyclerview.ProgressStyle;
import org.yh.yhframe.app.MyApplication;
import org.yh.yhframe.bean.JsonMenuModel;
import org.yh.yhframe.bean.MenuModel;

import java.util.ArrayList;

public class YHRecyclerviewActivity extends BaseActiciy
{
    @BindView(id = R.id.recyclerview)
    private YHRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ArrayList<MenuModel> menuModelListList;
    private int page = 0;
    private String url = "";
    private boolean isRefresh = true;//是否上拉刷新

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_xrecyclerview);
    }

    @Override
    public void initData()
    {
        super.initData();
    }

    private void getDataByLine()
    {
        url = "http://192.168.0.3/CI/api/menu/menulist?page=" + page;
        YHOkHttp.get(url, "", new HttpCallBack()
        {
            @Override
            public void onSuccess(String t)
            {
                super.onSuccess(t);
                JsonMenuModel jsonMenuModel = JsonUitl.stringToT(MyApplication.getInstance
                        ().yhGson, t, JsonMenuModel.class);
                String resultCode = jsonMenuModel.getResultCode();
                if ("0".equals(resultCode))
                {
                    ArrayList<MenuModel> list = (ArrayList<MenuModel>) jsonMenuModel.getDatas();
                    if (isRefresh)
                    {
                        if (!StringUtils.isEmpty(list))
                        {
                            menuModelListList.clear();//必须在数据更新前清空，不能太早
                            for (int i = 0; i < list.size(); i++)
                            {
                                menuModelListList.add(list.get(i));
                            }
                        }
                        //刷新完毕
                        mRecyclerView.refreshComplete();
                        mAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        if (!StringUtils.isEmpty(list))
                        {
                            for (int i = 0; i < list.size(); i++)
                            {
                                menuModelListList.add(list.get(i));
                            }
                            //加载完毕
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            //没有更多
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }else
                {
                    menuModelListList.clear();//必须在数据更新前清空，不能太早
                    //刷新完毕
                    mRecyclerView.refreshComplete();
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg)
            {
                super.onFailure(errorNo, strMsg);
                LogUtils.e(TAG, strMsg);
                menuModelListList.clear();//必须在数据更新前清空，不能太早
                //刷新完毕
                mRecyclerView.refreshComplete();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFinish()
            {
                super.onFinish();
                Constants.Config.yhDBManager.insertAll(menuModelListList);
            }
        }, TAG);
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
        mRecyclerView.addItemDecoration(new YHRecycleViewDivider(
                aty, LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setFootViewText(getString(R.string.listview_loading), "没有更多");
        //mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mRecyclerView.addItemDecoration(new YHRecycleViewDivider(aty, LinearLayoutManager.VERTICAL));




        menuModelListList = new ArrayList<>();
        mAdapter = new MyAdapter(menuModelListList);


        mRecyclerView.setLoadingListener(new YHRecyclerView.LoadingListener()
        {
            @Override
            public void onRefresh()
            {
                page = 1;
                isRefresh = true;
                getDataByLine();
            }

            @Override
            public void onLoadMore()
            {
                page++;
                isRefresh = false;
                getDataByLine();
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
