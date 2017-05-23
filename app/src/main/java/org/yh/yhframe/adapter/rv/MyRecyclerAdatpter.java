package org.yh.yhframe.adapter.rv;

import org.yh.library.adapter.rv.YhRecyclerAdapter;
import org.yh.yhframe.bean.MenuModel;

/**
 * Created by yhlyl on 2017/5/22.
 */

public class MyRecyclerAdatpter extends YhRecyclerAdapter<MenuModel>
{
    public MyRecyclerAdatpter()
    {
        addItemViewDelegate(new RvMsgComingItemDelagate());
        addItemViewDelegate(new RVMsgSendItemDelagate());
    }
}
