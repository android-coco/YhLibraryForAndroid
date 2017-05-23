package org.yh.yhframe;

import android.view.View;
import android.widget.ListView;

import org.yh.library.adapter.I_YHItemClickListener;
import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.yhframe.adapter.lv.ChatAdapter;
import org.yh.yhframe.base.BaseActiciy;
import org.yh.yhframe.bean.ChatMessage;

public class ListActivity extends BaseActiciy implements I_YHItemClickListener<ChatMessage>
{
    @BindView(id = R.id.id_listview_list)
    private ListView mListView;
    private ChatAdapter chatAdapter;
    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_list);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("ListActivity");
        toolbar.setMainTitleDrawable(R.mipmap.logo_white_210);
        toolbar.setRightTitleDrawable(R.mipmap.icon_home_menu_more);

        mListView.setDivider(null);
        chatAdapter = new ChatAdapter();
        chatAdapter.setDatas(ChatMessage.MOCK_DATAS);
        mListView.setAdapter(chatAdapter);
        chatAdapter.setOnItemClickListener(this);
    }

    @Override
    public boolean onItemLongClick(View view, ChatMessage data,int position)
    {
        YHViewInject.create().showTips("长按：" + data.getContent() +"   " +  position);
        return false;
    }

    @Override
    public void onItemClick(View view, ChatMessage data,int position)
    {
        YHViewInject.create().showTips("点击：" +data.getContent() +"  " +  position);
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        finish();
    }
}
