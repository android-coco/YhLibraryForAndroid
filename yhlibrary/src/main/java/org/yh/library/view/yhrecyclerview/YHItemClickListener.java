package org.yh.library.view.yhrecyclerview;

import android.view.View;

/**
 * 作者：38314 on 2017/5/15 13:17
 * 邮箱：yh_android@163.com
 */
public interface YHItemClickListener
{
    void onItemClick(View view, int postion);

    void onItemLongClick(View view,int postion);
}
