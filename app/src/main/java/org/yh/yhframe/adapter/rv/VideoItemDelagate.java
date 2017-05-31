package org.yh.yhframe.adapter.rv;

import org.yh.library.adapter.rv.I_ItemViewDelegate;
import org.yh.library.adapter.rv.YHRecyclerViewHolder;
import org.yh.library.view.video.YHVideoPlayer;
import org.yh.library.view.video.YHVideoPlayerController;
import org.yh.yhframe.R;
import org.yh.yhframe.bean.Video;

/**
 * Created by yhlyl on 2017/5/31.
 */

public class VideoItemDelagate implements I_ItemViewDelegate<Video>
{


    @Override
    public int getItemViewLayoutId()
    {
        return R.layout.item_video;
    }

    @Override
    public boolean isForViewType(Video item, int position)
    {
        return true;
    }

    @Override
    public void convert(YHRecyclerViewHolder holder, Video item, int position)
    {
        YHVideoPlayer yhVideoPlayer = (YHVideoPlayer) holder.getView(R.id.nice_video_player);
        YHVideoPlayerController mController = new YHVideoPlayerController(holder.getContext());
        mController.setTitle(item.getTitle());
        mController.setImage(item.getImageUrl());
        yhVideoPlayer.setController(mController);
        yhVideoPlayer.setUp(item.getVideoUrl(), null);
    }
}
