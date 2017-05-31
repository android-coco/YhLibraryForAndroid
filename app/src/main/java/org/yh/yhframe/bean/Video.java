package org.yh.yhframe.bean;

import org.yh.library.bean.YHModel;

/**
 * Created by yhlyl on 2017/5/31.
 * 视频
 */

public class Video extends YHModel
{
    private String title;//视频名称
    private String imageUrl;//图片地址
    private String videoUrl;//视频地址

    public Video(String title, String imageUrl, String videoUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
