package org.yh.yhframe;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.yhframe.adapter.lv.ChatAdapter;
import org.yh.yhframe.base.BaseActiciy;
import org.yh.yhframe.bean.ChatMessage;

public class ListActivity extends BaseActiciy
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
        mListView.setDivider(null);
        chatAdapter = new ChatAdapter(this, ChatMessage.MOCK_DATAS);
        mListView.setAdapter(chatAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                YHViewInject.create().showTips(chatAdapter.getDatas().get(position) + "");
            }
        });
    }
}
