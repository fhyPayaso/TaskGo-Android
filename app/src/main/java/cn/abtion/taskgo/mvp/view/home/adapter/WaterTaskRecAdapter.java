package cn.abtion.taskgo.mvp.view.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.mvp.model.request.home.WaterTaskModel;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author FanHongyu.
 * @since 18/1/26 16:24.
 * email fanhongyu@hrsoft.net.
 */

public class WaterTaskRecAdapter extends BaseRecyclerViewAdapter<WaterTaskModel> {


    public WaterTaskRecAdapter(Context context, List<WaterTaskModel> waterTaskModels) {
        super(context, waterTaskModels);
    }

    @Override
    public BaseViewHolder<WaterTaskModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_water_task, parent, false);
        return new ItemHolder(view);
    }

    static class ItemHolder extends BaseViewHolder<WaterTaskModel> {

        @BindView(R.id.img_avatar)
        CircleImageView imgAvatar;
        @BindView(R.id.txt_username)
        TextView txtUsername;
        @BindView(R.id.txt_address)
        TextView txtAddress;
        @BindView(R.id.txt_task_type)
        TextView txtTaskType;
        @BindView(R.id.txt_release_time)
        TextView txtReleaseTime;


        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(WaterTaskModel waterTaskModel, int position) {

            txtUsername.setText(waterTaskModel.getUsername() == null ? "N/A" :waterTaskModel.getUsername());
            txtAddress.setText(waterTaskModel.getAddress() == null ? "N/A":waterTaskModel.getAddress());
            txtTaskType.setText(waterTaskModel.getType() == null ?"N/A" :waterTaskModel.getType());
            txtReleaseTime.setText(waterTaskModel.getReleaseTime() == null ? "N/A" : waterTaskModel.getReleaseTime());
        }
    }
}
