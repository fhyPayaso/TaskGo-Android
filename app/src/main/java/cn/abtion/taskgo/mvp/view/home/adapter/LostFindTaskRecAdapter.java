package cn.abtion.taskgo.mvp.view.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.mvp.model.request.home.LostFindTaskModel;

/**
 * @author fhyPayaso
 * @since 2018/1/27 on 上午12:27
 * fhyPayaso@qq.com
 */
public class LostFindTaskRecAdapter extends BaseRecyclerViewAdapter<LostFindTaskModel> {


    public LostFindTaskRecAdapter(Context context, List<LostFindTaskModel> lostFindTaskModels) {

        super(context, lostFindTaskModels);
    }

    @Override
    public BaseViewHolder<LostFindTaskModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_lost_find_task, parent, false);
        return new ItemHolder(view);
    }


    static class ItemHolder extends BaseViewHolder<LostFindTaskModel> {

        @BindView(R.id.txt_username)
        TextView mTxtUsername;
        @BindView(R.id.ly_avatar)
        LinearLayout mAvatar;
        @BindView(R.id.txt_things_name)
        TextView mTxtThingsName;
        @BindView(R.id.txt_release_time)
        TextView mTxtReleaseTime;


        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(LostFindTaskModel lostFindTaskModel, int position) {

            mTxtUsername.setText(lostFindTaskModel.getUsername() == null ? "N/A" : lostFindTaskModel.getUsername());
            mTxtThingsName.setText(lostFindTaskModel.getThingsName() == null ? "N/A" : lostFindTaskModel.getThingsName());
            mTxtReleaseTime.setText(lostFindTaskModel.getReleaseTime() == null ? "N/A" :lostFindTaskModel.getReleaseTime());
        }
    }
}
