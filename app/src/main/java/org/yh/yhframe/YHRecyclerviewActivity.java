package org.yh.yhframe;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import org.yh.library.adapter.I_YHItemClickListener;
import org.yh.library.okhttp.YHOkHttp;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.Constants;
import org.yh.library.utils.JsonUitl;
import org.yh.library.utils.LogUtils;
import org.yh.library.utils.StringUtils;
import org.yh.library.view.YHRecyclerView;
import org.yh.library.view.yhrecyclerview.ProgressStyle;
import org.yh.yhframe.app.MyApplication;
import org.yh.yhframe.bean.JsonMenuModel;
import org.yh.yhframe.bean.MenuModel;
import org.yh.yhframe.frame.BaseActiciy;

import java.util.ArrayList;

public class YHRecyclerviewActivity extends BaseActiciy implements I_YHItemClickListener<MenuModel>
{
    @BindView(id = R.id.recyclerview)
    private YHRecyclerView mRecyclerView;
    @BindView(id = R.id.empty_layout)
    private LinearLayout empty_layout;
    private MyAdapter mAdapter;
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
        //家里
        //url = "http://192.168.0.3/CI/api/menu/menulist?page=" + page;
        //公司
        url = "http://192.168.0.197:8080/Ci/api/menu/menulist?page=" + page;
        YHOkHttp.get(url, "", new HttpCallBack()
        {
            @Override
            public void onSuccess(String t)
            {
                super.onSuccess(t);
                JsonMenuModel jsonMenuModel = JsonUitl.stringToT(MyApplication.getInstance().yhGson, t, JsonMenuModel.class);
                String resultCode = jsonMenuModel.getResultCode();
                if ("0".equals(resultCode))
                {
                    ArrayList<MenuModel> list = (ArrayList<MenuModel>) jsonMenuModel.getDatas();
                    if (isRefresh)
                    {
                        mAdapter.getDatas().clear();//必须在数据更新前清空，不能太早
                        if (!StringUtils.isEmpty(list))
                        {
                            mAdapter.setDatas(list);
                            //刷新完毕
                            mRecyclerView.refreshComplete();
                        }
                    } else
                    {
                        if (!StringUtils.isEmpty(list))
                        {
                            //每页数据16条,如果少于16条代表没有更多数据
                            //让用户少刷新一次
                            if (list.size() < 16)
                            {
                                //没有更多
                                mRecyclerView.setNoMore(true);
                                mAdapter.notifyDataSetChanged();
                            }else
                            {
                                mAdapter.addDatas(list);
                                //加载完毕
                                mRecyclerView.loadMoreComplete();
                            }
                        } else
                        {
                            //没有更多
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                } else
                {
                    mAdapter.getDatas().clear();//必须在数据更新前清空，不能太早
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
                mAdapter.getDatas().clear();//必须在数据更新前清空，不能太早
                //刷新完毕
                mRecyclerView.refreshComplete();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFinish()
            {
                super.onFinish();
                Constants.Config.yhDBManager.insertAll(mAdapter.getDatas());
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
        mRecyclerView.addItemDecoration(mRecyclerView.new YHItemDecoration());//分割线
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setEmptyView(empty_layout);//没有数据的空布局

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setFootViewText(getString(R.string.listview_loading), "没有更多");
        //mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);

        mAdapter = new MyAdapter();
        mAdapter.setOnItemClickListener(this);

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

    @Override
    public void onItemLongClick(View view, MenuModel data)
    {
        YHViewInject.create().showTips("长按：" + data.getMenuname());
    }

    @Override
    public void onItemClick(View view, MenuModel data)
    {
        YHViewInject.create().showTips("点击了：" + data.getMenuname());
    }
}
