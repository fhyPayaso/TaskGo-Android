package cn.abtion.taskgo.mvp.view.task.adapter.rec;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.mvp.model.task.model.SimpleUserInfoModel;
import cn.abtion.taskgo.mvp.view.task.adapter.TaskItemListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author FanHongyu.
 * @since 18/3/10 18:04.
 * email fanhongyu@hrsoft.net.
 */

public class FinishUserListAdapter extends BaseRecyclerViewAdapter<SimpleUserInfoModel> {


    private TaskItemListener mTaskItemListener;
    private Context mContext;


    public FinishUserListAdapter(Context context, List<SimpleUserInfoModel> simpleUserInfoModels) {
        super(context, simpleUserInfoModels);
        mContext = context;
    }

    @Override
    public BaseViewHolder<SimpleUserInfoModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_finish_user_info, parent, false);
        return new ItemHolder(view);
    }

    public void setTaskItemListener(TaskItemListener listener) {
        mTaskItemListener = listener;
    }

    class ItemHolder extends BaseViewHolder<SimpleUserInfoModel> implements View.OnClickListener {

        @BindView(R.id.img_avatar)
        CircleImageView imgAvatar;
        @BindView(R.id.txt_username)
        TextView txtUsername;
        @BindView(R.id.btn_task)
        TextView btnTask;

        public ItemHolder(View itemView) {
            super(itemView);
            imgAvatar.setOnClickListener(this);
            btnTask.setOnClickListener(this);
        }

        @Override
        protected void onBind(SimpleUserInfoModel simpleUserInfoModel, int position) {

            Glide.with(mContext).load(simpleUserInfoModel.getAvatarUrl()).into(imgAvatar);
            txtUsername.setText(simpleUserInfoModel.getUsername() == null ? "N/A" : simpleUserInfoModel.getUsername());
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            switch (v.getId()) {
                case R.id.img_avatar:
                    mTaskItemListener.onClickAvatar(position);
                    break;
                case R.id.btn_task:
                    mTaskItemListener.onClickAccept(position);
                    break;
                default:
                    break;
            }
        }
    }
}
