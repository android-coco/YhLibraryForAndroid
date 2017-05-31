package org.yh.library.view.video;

/**
 * Created by yhlyl on 2017/5/31.
 * 视频播放器管理器.
 */

public class YHVideoPlayerManager
{
    private YHVideoPlayer mVideoPlayer;

    private YHVideoPlayerManager()
    {
    }

    private static YHVideoPlayerManager sInstance;

    public static synchronized YHVideoPlayerManager instance()
    {
        if (sInstance == null)
        {
            sInstance = new YHVideoPlayerManager();
        }
        return sInstance;
    }

    public void setCurrentNiceVideoPlayer(YHVideoPlayer videoPlayer)
    {
        mVideoPlayer = videoPlayer;
    }

    public void releaseYHVideoPlayer()
    {
        if (mVideoPlayer != null)
        {
            mVideoPlayer.release();
            mVideoPlayer = null;
        }
    }

    public boolean onBackPressd()
    {
        if (mVideoPlayer != null)
        {
            if (mVideoPlayer.isFullScreen())
            {
                return mVideoPlayer.exitFullScreen();
            }
            else if (mVideoPlayer.isTinyWindow())
            {
                return mVideoPlayer.exitTinyWindow();
            }
            else
            {
                mVideoPlayer.release();
                return false;
            }
        }
        return false;
    }
}
