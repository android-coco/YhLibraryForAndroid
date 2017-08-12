package org.yh.yhframe;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.yh.library.ui.BindView;
import org.yh.library.utils.LogUtils;
import org.yh.library.view.video.YHVideoPlayer;
import org.yh.library.view.video.YHVideoPlayerManager;
import org.yh.yhframe.adapter.rv.VideoAdatper;
import org.yh.yhframe.base.BaseActiciy;
import org.yh.yhframe.bean.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频列表
 */
public class VidoRecyclerViewActivity extends BaseActiciy
{
    @BindView(id = R.id.recyclerview)
    private RecyclerView mRecyclerView;
    @BindView(id = R.id.empty_layout)
    private LinearLayout empty_layout;
    @BindView(id = R.id.id_empty_text)
    private TextView id_empty_text;
    VideoAdatper mAdapter;

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_vido_recycler_view);
    }


    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("视频列表");
        toolbar.setMainTitleDrawable(R.mipmap.logo_white_210);
        toolbar.setRightTitleDrawable(R.mipmap.icon_home_menu_more);


        id_empty_text.setText("加载中。。。");

        //lineartlayout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //CardView
        // 两列
        //       int spanCount = 2;
        //GridLayoutManager layoutManager = new GridLayoutManager(this,spanCount);

//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount,
//                StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //分割线为LinearLayoutManager
        //mRecyclerView.addItemDecoration(mRecyclerView.new YHItemDecoration());//分割线
        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setEmptyView(empty_layout);//没有数据的空布局
        mRecyclerView.setHasFixedSize(true);

//        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//        mRecyclerView.setFootViewText(getString(R.string.listview_loading), "加载完毕");
//        mRecyclerView.setPullRefreshEnabled(false);
//        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView
                .OnChildAttachStateChangeListener()

        {
            @Override
            public void onChildViewAttachedToWindow(View view)
            {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view)
            {
                YHVideoPlayer yhVideoPlayer = (YHVideoPlayer) view.findViewById(R.id
                        .nice_video_player);
                if (yhVideoPlayer != null)
                {
                    LogUtils.e(TAG,"fasdfas" + yhVideoPlayer.getCurrentPosition());
                    yhVideoPlayer.release();
                }
            }
        });
        mAdapter = new VideoAdatper();

        //模拟网络请求
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                mAdapter.setDatas(getVideoList());
                mRecyclerView.setAdapter(mAdapter);
                //刷新完毕
//                mRecyclerView.refreshComplete();
            }
        }, 2000);


//        mRecyclerView.refresh();
    }

    public List<Video> getVideoList()
    {
        List<Video> videoList = new ArrayList<>();
        videoList.add(new Video("办公室小野开番外了，居然在办公室开澡堂！老板还点赞？",
                "http://data.fitcome.net/static/img/yh.gif",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4"));

        videoList.add(new Video("小野在办公室用丝袜做茶叶蛋 边上班边看《外科风云》",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/05/2017-05-10_10-09-58.jpg",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/05/2017-05-10_10-20-26.mp4"));

        videoList.add(new Video("花盆叫花鸡，怀念玩泥巴，过家家，捡根竹竿当打狗棒的小时候",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/05/2017-05-03_12-52-08.jpg",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/05/2017-05-03_13-02-41.mp4"));

        videoList.add(new Video("针织方便面，这可能是史上最不方便的方便面",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/04/2017-04-28_18-18-22.jpg",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/04/2017-04-28_18-20-56.mp4"));

        videoList.add(new Video("宵夜的下午茶，办公室不只有KPI，也有诗和远方",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/04/2017-04-26_10-00-28.jpg",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/04/2017-04-26_10-06-25.mp4"));

        videoList.add(new Video("可乐爆米花，嘭嘭嘭......收花的人说要把我娶回家",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/04/2017-04-21_16-37-16.jpg",
                "http://tanzi27niu.cdsb" +
                        ".mobi/wps/wp-content/uploads/2017/04/2017-04-21_16-41-07.mp4"));
        return videoList;
    }


    @Override
    public void onBackPressed()
    {
        if (YHVideoPlayerManager.instance().onBackPressd())
        {
            return;
        }
        super.onBackPressed();
    }

}
