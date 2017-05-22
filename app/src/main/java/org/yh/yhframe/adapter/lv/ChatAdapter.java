package org.yh.yhframe.adapter.lv;

import android.content.Context;

import org.yh.library.adapter.lv.YHListAdapter;
import org.yh.yhframe.bean.ChatMessage;

import java.util.List;

/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapter extends YHListAdapter<ChatMessage>
{
    public ChatAdapter(Context context, List<ChatMessage> datas)
    {
        super(context, datas);

        addItemViewDelegate(new MsgSendIItemDelagate());
        addItemViewDelegate(new MsgComingIItemDelagate());
    }

}
