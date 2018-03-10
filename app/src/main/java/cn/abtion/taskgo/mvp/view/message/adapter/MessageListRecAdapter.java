package cn.abtion.taskgo.mvp.view.message.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.mvp.model.MessageModel;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author FanHongyu.
 * @since 18/2/3 17:14.
 * email fanhongyu@hrsoft.net.
 */

public class MessageListRecAdapter extends BaseRecyclerViewAdapter<MessageModel> {


    private MessageItemListener messageListener;

    public MessageListRecAdapter(Context context, List<MessageModel> messageModels) {
        super(context, messageModels);
    }

    @Override
    public BaseViewHolder<MessageModel> onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_message, parent, false);
        return new ItemHolder(view);
    }

    public void setMessageListener(MessageItemListener messageListener) {
        this.messageListener = messageListener;
    }

    class ItemHolder extends BaseViewHolder<MessageModel> implements View.OnClickListener {

        @BindView(R.id.img_message_avatar)
        CircleImageView imgMessageAvatar;
        @BindView(R.id.txt_message_name)
        TextView txtUserName;
        @BindView(R.id.txt_message_last)
        TextView txtMessageLast;
        @BindView(R.id.txt_last_time)
        TextView txtLastTime;
        @BindView(R.id.txt_unread_message_number)
        TextView txtUnreadMessageNumber;
        @BindView(R.id.rl_message_main)
        RelativeLayout rlMessageMain;
        @BindView(R.id.txt_delete_message)
        TextView txtDeleteMessage;

        public ItemHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            imgMessageAvatar.setOnClickListener(this);
            txtDeleteMessage.setOnClickListener(this);
            rlMessageMain.setOnClickListener(this);
        }

        @Override
        protected void onBind(MessageModel messageModel, int position) {

            txtUserName.setText(messageModel.getUserName() == null ? "N/A" : messageModel.getUserName());
            txtMessageLast.setText(messageModel.getLastMessage() == null ? "N/A" : messageModel.getLastMessage());
            txtLastTime.setText(messageModel.getLastTime() == null ? "N/A" : messageModel.getLastTime());

            //设置未读数量
            if (messageModel.getUnReadNumber() != 0) {
                txtUnreadMessageNumber.setVisibility(View.VISIBLE);
                txtUnreadMessageNumber.setText(String.valueOf(messageModel.getUnReadNumber()));
            } else {
                txtUnreadMessageNumber.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            switch (v.getId()) {
                case R.id.img_message_avatar:
                    messageListener.onClickAvatar(position);
                    break;
                case R.id.txt_delete_message:
                    messageListener.onClickDelete(position);
                    break;
                case R.id.rl_message_main:
                    messageListener.onClickChat(position);
                default:
                    break;
            }
        }
    }

}
