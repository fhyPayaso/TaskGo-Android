package cn.abtion.taskgo.mvp.view.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.mvp.model.request.home.LostFindTaskModel;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * @author fhyPayaso
 * @since 2018/1/27 on 上午12:27
 * fhyPayaso@qq.com
 */
public class LostFoundTaskRecAdapter extends BaseRecyclerViewAdapter<LostFindTaskModel> {



    private TaskListener mTaskListener;

    public LostFoundTaskRecAdapter(Context context, List<LostFindTaskModel> lostFindTaskModels) {

        super(context, lostFindTaskModels);
    }

    @Override
    public BaseViewHolder<LostFindTaskModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_lost_find_task, parent, false);
        return new ItemHolder(view);
    }

    public void setTaskListener(TaskListener listener) {
        mTaskListener = listener;
    }

    class ItemHolder extends BaseViewHolder<LostFindTaskModel> implements View.OnClickListener {

        @BindView(R.id.txt_username)
        TextView mTxtUsername;
        @BindView(R.id.img_avatar)
        CircleImageView imgAvatar;
        @BindView(R.id.txt_things_name)
        TextView mTxtThingsName;
        @BindView(R.id.txt_release_time)
        TextView mTxtReleaseTime;
        @BindView(R.id.txt_accept_task)
        TextView txtAcceptTask;

        private ItemHolder(View itemView) {
            super(itemView);
            imgAvatar.setOnClickListener(this);
            txtAcceptTask.setOnClickListener(this);
        }

        @Override
        protected void onBind(LostFindTaskModel lostFindTaskModel, int position) {

            mTxtUsername.setText(lostFindTaskModel.getUsername() == null ? "N/A" : lostFindTaskModel.getUsername());
            mTxtThingsName.setText(lostFindTaskModel.getThingsName() == null ? "N/A" : lostFindTaskModel.getThingsName());
            mTxtReleaseTime.setText(lostFindTaskModel.getReleaseTime() == null ? "N/A" : lostFindTaskModel.getReleaseTime());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()) {
                case R.id.img_avatar:
                    mTaskListener.onClickAvatar(position);
                    break;
                case R.id.txt_accept_task:
                    mTaskListener.onClickAccept(position);
                    break;
                default:
                    break;
            }
        }
    }
}
