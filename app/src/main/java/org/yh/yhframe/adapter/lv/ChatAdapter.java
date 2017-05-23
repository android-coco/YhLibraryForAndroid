package org.yh.yhframe.adapter.lv;

import org.yh.library.adapter.lv.YHListAdapter;
import org.yh.yhframe.bean.ChatMessage;

/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapter extends YHListAdapter<ChatMessage>
{
    public ChatAdapter()
    {
        addItemViewDelegate(new MsgSendIItemDelagate());
        addItemViewDelegate(new MsgComingIItemDelagate());
    }

}
