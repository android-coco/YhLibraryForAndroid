package org.yh.yhframe;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.yh.library.YHFragment;
import org.yh.library.adapter.I_YHItemClickListener;
import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.Constants;
import org.yh.library.utils.JsonUitl;
import org.yh.library.utils.LogUtils;
import org.yh.library.utils.StringUtils;
import org.yh.library.view.YHRecyclerView;
import org.yh.library.view.yhrecyclerview.ProgressStyle;
import org.yh.yhframe.adapter.rv.MyRecyclerAdatpter;
import org.yh.yhframe.base.BaseActiciy;
import org.yh.yhframe.bean.JsonMenuModel;
import org.yh.yhframe.bean.MenuModel;

import java.util.ArrayList;

import static org.yh.yhframe.app.MyApplication.HOME_HOST;


public class DemoActivity extends BaseActiciy
        implements NavigationView.OnNavigationItemSelectedListener,I_YHItemClickListener<MenuModel>
{
    YHFragment oneFragment = null;
    YHFragment twoFragment = null;
    YHFragment threeFragment = null;
    YHFragment fourFragment = null;
    DrawerLayout drawer = null;

    /**
     * 服务器端一共多少页
     */
    private static int TOTAL_PAGE = 2;//假设10页
    @BindView(id = R.id.nav_view1)
    private YHRecyclerView mRecyclerView;
    private MyRecyclerAdatpter mAdapter;
    private int page = 0;
    ArrayList<MenuModel> data = null;
    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_demo);
    }

    @Override
    public void initData()
    {
        super.initData();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();
        data = new ArrayList<>();
    }
    private void getDataByLine()
    {
//        YHLoadingBar.make(empty_layout).show();
        YHRequestFactory.getRequestManger().get(HOME_HOST, "api/menu/menulist?page=" + page, null, new HttpCallBack()
        {
            @Override
            public void onSuccess(String t)
            {
                super.onSuccess(t);
                JsonMenuModel jsonMenuModel = JsonUitl.stringToTObject(t, JsonMenuModel.class);
                TOTAL_PAGE = jsonMenuModel.getTotalPage();
                String resultCode = jsonMenuModel.getResultCode();
                if ("0".equals(resultCode))
                {
                    if (StringUtils.isEmpty(jsonMenuModel.getDatas()))
                    {
                    } else
                    {
                        data.addAll(jsonMenuModel.getDatas());
                        mAdapter.setDatas(data);
                    }
                } else
                {
                }
                //刷新完毕
                mRecyclerView.refreshComplete();
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
//                YHLoadingBar.cancel(empty_layout);
                Constants.Config.yhDBManager.insertAll(mAdapter.getDatas());
//                QueryBuilder<MenuModel> qb = new QueryBuilder<>(
//                        MenuModel.class).where(MenuModel.COL_NAME + " =? ",
//                        new Object[]
//                                {"冬菇蒸滑鸡饭（不辣）"}).orderBy(MenuModel.COL_MENUID);
//                ArrayList<MenuModel> x =Constants.Config.yhDBManager.getDb().query(qb);
//                LogUtils.e(TAG,"fadfadsfa:" + x + "");
            }
        }, TAG);
    }
    @Override
    public void initWidget()
    {
        super.initWidget();
        changeFragment(R.id.content,oneFragment);
        FloatingActionButton fab = bindView(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = bindView(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //禁止手势滑动
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //打开手势滑动
        //drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        toggle.syncState();

        //导航图标
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        NavigationView navigationView =bindView(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

//        NavigationView navigationView1 =bindView(R.id.nav_view1);
//        navigationView1.setNavigationItemSelectedListener(this);
//        navigationView1.getMenu().getItem(0).setChecked(true);


        //lineartlayout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        //CardView
        // 两列
        //int spanCount = 3;
        //GridLayoutManager layoutManager = new GridLayoutManager(this,spanCount);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        //StaggeredGridLayoutManager 不能加分割线
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount,
//                StaggeredGridLayoutManager.VERTICAL);


        //分割线为LinearLayoutManager
        mRecyclerView.addItemDecoration(mRecyclerView.new YHItemDecoration());//分割线
        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setEmptyView(empty_layout);//没有数据的空布局

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulseRise);//可以自定义下拉刷新的样式
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);//可以自定义上拉加载的样式
        mRecyclerView.setFootViewText(getString(R.string.listview_loading), "我是有底线的。");
        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);//箭头
//        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
//        mRecyclerView.addHeaderView(header);
//        View header1 = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
//        mRecyclerView.addHeaderView(header1);
//        header1.setBackgroundColor(0xff556B2F);
//        View header2 = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
//        mRecyclerView.addHeaderView(header2);
//        header2.setBackgroundColor(0xff1874CD);


        mAdapter = new MyRecyclerAdatpter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setLoadingListener(new YHRecyclerView.LoadingListener()
        {
            @Override
            public void onRefresh()
            {
                page = 1;
                mAdapter.getDatas().clear();//必须在数据更新前清空，不能太早
                getDataByLine();
            }

            @Override
            public void onLoadMore()
            {
                page++;
                if (page <= TOTAL_PAGE)
                {//小于总页数就加载更多
                    // loading more
                    getDataByLine();
                } else
                {
                    //the end
                    mRecyclerView.setNoMore(true);
                }
            }
        });
        mRecyclerView.refresh();
    }
    //获取Back键的按下事件
    @Override
    public void onBackPressed()
    {


        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings)
//        {
//            return true;
//        }
//        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            changeFragment(R.id.content,oneFragment);
            Snackbar.make(drawer, "nav_camera", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_gallery)
        {
            changeFragment(R.id.content,twoFragment);
            Snackbar.make(drawer, "nav_camera", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_slideshow)
        {
            changeFragment(R.id.content,threeFragment);
            Snackbar.make(drawer, "nav_camera", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_manage)
        {
            changeFragment(R.id.content,fourFragment);
            Snackbar.make(drawer, "nav_manage", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_share)
        {
            Snackbar.make(drawer, "nav_share", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if (id == R.id.nav_send)
        {
            Snackbar.make(drawer, "nav_send", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    @Override
    protected void onMenuClick()
    {
        super.onMenuClick();
        Snackbar.make(drawer, "onMenuClick", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        //显示右侧栏
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        drawer.openDrawer(GravityCompat.END);
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        Snackbar.make(drawer, "onBackClick", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onItemLongClick(View view, MenuModel data, int postion)
    {
        YHViewInject.create().showTips("长按：" + data.getMenuname() + " " + postion);
        return true;
    }

    @Override
    public void onItemClick(View view, MenuModel data, int postion)
    {
        YHViewInject.create().showTips("点击了：" + data.getMenuname() + " " + postion);
    }
}
