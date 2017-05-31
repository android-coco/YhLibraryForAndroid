package org.yh.yhframe;

import android.view.View;
import android.widget.Button;

import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.view.video.YHVideoPlayer;
import org.yh.library.view.video.YHVideoPlayerController;
import org.yh.library.view.video.YHVideoPlayerManager;
import org.yh.yhframe.base.BaseActiciy;

/**
 * 视频播放
 */
public class VideoActivity extends BaseActiciy
{
    @BindView(id = R.id.nice_video_player)
    private YHVideoPlayer mYHVideoPlayer;
    @BindView(id = R.id.enterTinyWindow, click = true)
    private Button enterTinyWindow;
    @BindView(id = R.id.showVideoList, click = true)
    private Button showVideoList;

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_video);
    }

    @Override
    public void initData()
    {
        super.initData();
        mYHVideoPlayer.setUp("https://data.fitcome" +
                ".net/storage/regimen/video/201609/cd2f39c353fa867594d8f801d2adced5.mp4", null);
        YHVideoPlayerController controller = new YHVideoPlayerController(this);
        controller.setTitle("养生");
        controller.setImage("http://data.fitcome.net/static/img/yh.gif");
        mYHVideoPlayer.setController(controller);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("视频播放");
        toolbar.setMainTitleDrawable(R.mipmap.logo_white_210);
        toolbar.setRightTitleDrawable(R.mipmap.icon_home_menu_more);
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

    @Override
    public void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.showVideoList:
                showActivity(aty, VidoRecyclerViewActivity.class);
                break;
            case R.id.enterTinyWindow:
                if (mYHVideoPlayer.isPlaying()
                        || mYHVideoPlayer.isBufferingPlaying()
                        || mYHVideoPlayer.isPaused()
                        || mYHVideoPlayer.isBufferingPaused())
                {
                    mYHVideoPlayer.enterTinyWindow();
                }
                else
                {
                    YHViewInject.create().showTips("要播放后才能进入小窗口");
                }
                break;
        }
    }
}
