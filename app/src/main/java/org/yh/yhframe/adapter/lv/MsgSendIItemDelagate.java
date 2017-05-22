package org.yh.yhframe.adapter.lv;


import org.yh.library.adapter.lv.I_ItemViewDelegate;
import org.yh.library.adapter.lv.YHListViewHolder;
import org.yh.yhframe.R;
import org.yh.yhframe.bean.ChatMessage;

/**
 * Created by zhy on 16/6/22.
 */
public class MsgSendIItemDelagate implements I_ItemViewDelegate<ChatMessage>
{

    @Override
    public int getItemViewLayoutId()
    {
        return R.layout.main_chat_send_msg;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position)
    {
        return !item.isComMeg();
    }

    @Override
    public void convert(YHListViewHolder holder, ChatMessage chatMessage, int position)
    {
        holder.setText(R.id.chat_send_content, chatMessage.getContent());
        holder.setText(R.id.chat_send_name, chatMessage.getName());
        holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon());
    }
}
