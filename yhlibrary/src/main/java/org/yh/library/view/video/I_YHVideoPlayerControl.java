package org.yh.library.view.video;

/**
 * Created by yhlyl on 2017/5/31.
 */

public interface I_YHVideoPlayerControl
{
    //开始播放
    void start();
    //重新播放
    void restart();
    //停止
    void pause();
    //快进
    void seekTo(int pos);

    boolean isIdle();
    boolean isPreparing();
    boolean isPrepared();
    boolean isBufferingPlaying();
    boolean isBufferingPaused();
    boolean isPlaying();
    boolean isPaused();
    boolean isError();
    boolean isCompleted();

    boolean isFullScreen();
    boolean isTinyWindow();
    boolean isNormal();

    int getDuration();
    int getCurrentPosition();
    int getBufferPercentage();

    void enterFullScreen();
    boolean exitFullScreen();
    void enterTinyWindow();
    boolean exitTinyWindow();

    void release();
}
