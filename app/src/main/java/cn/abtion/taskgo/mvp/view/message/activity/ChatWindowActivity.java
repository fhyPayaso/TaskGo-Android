package cn.abtion.taskgo.mvp.view.message.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.data.ChatHelper;
import cn.abtion.taskgo.mvp.view.message.adapter.ChatWindowRecAdapter;

/**
 * @author FanHongyu.
 * @since 18/2/3 17:09.
 * email fanhongyu@hrsoft.net.
 */

public class ChatWindowActivity extends BaseToolBarPresenterActivity implements EMMessageListener {


    @BindView(R.id.rec_chat)
    RecyclerView recChat;
    @BindView(R.id.edit_chat_content)
    EditText editChatContent;

    private ChatWindowRecAdapter mAdapter;
    private String testChatId = "222222";

    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_window;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        setActivityTitle("username");
        initRecyclerView();
    }

    @Override
    protected void loadData() {

    }


    private void initRecyclerView() {


        //添加信息监听
        EMClient.getInstance().chatManager().addMessageListener(this);
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(testChatId,
                EMConversation.EMConversationType.Chat, true);

        mAdapter = new ChatWindowRecAdapter(ChatWindowActivity.this, conversation);
        recChat.setAdapter(mAdapter);
        recChat.setLayoutManager(new LinearLayoutManager(ChatWindowActivity.this, LinearLayoutManager.VERTICAL, false));
        ChatHelper.refresh(mAdapter, recChat);
    }


    @OnClick(R.id.btn_send)
    public void onViewClicked() {

        EMMessage emMessage = EMMessage.createTxtSendMessage(editChatContent.getText().toString(), testChatId);
        EMClient.getInstance().chatManager().sendMessage(emMessage);
        editChatContent.setText("");
        ChatHelper.refresh(mAdapter, recChat);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ChatWindowActivity.class));
    }


    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        ChatHelper.refresh(mAdapter, recChat);
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
