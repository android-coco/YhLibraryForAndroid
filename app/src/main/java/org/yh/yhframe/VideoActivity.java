package org.yh.yhframe;

import android.view.View;
import android.widget.Button;

import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.StringUtils;
import org.yh.library.view.video.YHVideoPlayer;
import org.yh.library.view.video.YHVideoPlayerController;
import org.yh.library.view.video.YHVideoPlayerManager;
import org.yh.yhframe.base.BaseActiciy;

/**
 * 视频播放
 */
public class VideoActivity extends BaseActiciy
{
    public static final String VIDEO_PATH = "videopath";
    public static final String IMG_PATH = "imgpath";
    private static final String viodeo_path = "https://data.fitcome" +
            ".net/storage/regimen/video/201609/cd2f39c353fa867594d8f801d2adced5.mp4";
    private static final String img_path = "http://data.fitcome.net/static/img/yh.gif";
    @BindView(id = R.id.nice_video_player)
    private YHVideoPlayer mYHVideoPlayer;
    @BindView(id = R.id.enterTinyWindow, click = true)
    private Button enterTinyWindow;
    @BindView(id = R.id.showVideoList, click = true)
    private Button showVideoList;

    private String mPath;//视频播放路径 上个界面传来
    private String imgPath;//视频截图路径 上个界面传来

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_video);
    }

    @Override
    public void initData()
    {
        super.initData();
        mPath = getIntent().getStringExtra(VIDEO_PATH);
        imgPath = getIntent().getStringExtra(IMG_PATH);

        mPath = StringUtils.isEmpty(mPath) ? viodeo_path : mPath;
        imgPath = StringUtils.isEmpty(imgPath) ? img_path : imgPath;


        YHVideoPlayerController controller = new YHVideoPlayerController(this);
        mYHVideoPlayer.setUp(mPath, null);
        controller.setTitle(StringUtils.isEmpty(mPath) ? "养生" : "小视频");
        controller.setImage(imgPath);
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
