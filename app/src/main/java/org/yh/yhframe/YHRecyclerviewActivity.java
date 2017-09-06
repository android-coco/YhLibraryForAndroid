package org.yh.yhframe;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.yh.library.adapter.I_YHItemClickListener;
import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.Constants;
import org.yh.library.utils.JsonUitl;
import org.yh.library.utils.LogUtils;
import org.yh.library.view.YHRecyclerView;
import org.yh.library.view.yhrecyclerview.ProgressStyle;
import org.yh.yhframe.adapter.rv.MyRecyclerAdatpter;
import org.yh.yhframe.app.MyApplication;
import org.yh.yhframe.base.BaseActiciy;
import org.yh.yhframe.bean.JsonMenuModel;
import org.yh.yhframe.bean.MenuModel;

import java.util.ArrayList;

import static org.yh.yhframe.app.MyApplication.HOME_HOST;
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                       //
/**
  * YHRecyclerviewActivity
  * @author 38314
  * @time 2017/5/24 10:36
  */
public class YHRecyclerviewActivity extends BaseActiciy implements I_YHItemClickListener<MenuModel>
{
    /**服务器端一共多少页*/
    private static int TOTAL_PAGE = 2;//假设10页
    @BindView(id = R.id.recyclerview)
    private YHRecyclerView mRecyclerView;
    @BindView(id = R.id.empty_layout)
    private LinearLayout empty_layout;
    @BindView(id = R.id.id_empty_text)
    private TextView id_empty_text;
    private MyRecyclerAdatpter mAdapter;
    private int page = 0;
    ArrayList<MenuModel> data = null;
    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_xrecyclerview);
    }

    @Override
    public void initData()
    {
        super.initData();
        data = new ArrayList<>();
    }

    private void getDataByLine()
    {
//        YHLoadingBar.make(empty_layout).show();
        YHRequestFactory.getRequestManger().get(HOME_HOST, "api/menu/menulist?page=" + page,null, new HttpCallBack()
        {
            @Override
            public void onSuccess(String t)
            {
                super.onSuccess(t);
                JsonMenuModel jsonMenuModel = JsonUitl.stringToTObject(MyApplication.getInstance().yhGson, t, JsonMenuModel.class);
                TOTAL_PAGE = jsonMenuModel.getTotalPage();
                String resultCode = jsonMenuModel.getResultCode();
                if ("0".equals(resultCode))
                {
                    data.addAll(jsonMenuModel.getDatas());
                    mAdapter.setDatas(data);
                } else
                {
                    mAdapter.notifyDataSetChanged();
                }
                //刷新完毕
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onFailure(int errorNo, String strMsg)
            {
                super.onFailure(errorNo, strMsg);
                LogUtils.e(TAG, strMsg);
                id_empty_text.setText("加载失败");
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

        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("XRecyclerview");
        toolbar.setMainTitleDrawable(R.mipmap.logo_white_210);
        toolbar.setRightTitleDrawable(R.mipmap.icon_home_menu_more);


        id_empty_text.setText("加载中。。。");
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
        //mRecyclerView.addItemDecoration(mRecyclerView.new YHItemDecoration());//分割线
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setEmptyView(empty_layout);//没有数据的空布局

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
                if (page <= TOTAL_PAGE) {//小于总页数就加载更多
                    // loading more
                    getDataByLine();
                } else {
                    //the end
                    mRecyclerView.setNoMore(true);
                }
            }
        });
        mRecyclerView.refresh();
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        finish();
    }

    @Override
    protected void onMenuClick()
    {
        super.onMenuClick();
        YHViewInject.create().showTips("更多被点击");
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
