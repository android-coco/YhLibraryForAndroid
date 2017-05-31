package org.yh.yhframe.adapter.rv;

import org.yh.library.adapter.rv.YhRecyclerAdapter;
import org.yh.yhframe.bean.Video;

/**
 * Created by yhlyl on 2017/5/31.
 * 视频Adatper
 */

public class VideoAdatper extends YhRecyclerAdapter<Video>
{
    public VideoAdatper()
    {
        addItemViewDelegate(new VideoItemDelagate());
    }

}
