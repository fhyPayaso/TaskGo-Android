package cn.abtion.taskgo.mvp.view.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.mvp.model.mine.MyFollowModel;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author：lszr on 2018/1/31 00:15
 * @email：1085963811@qq.com
 */
public class MyFollowListAdapter extends BaseRecyclerViewAdapter<MyFollowModel> {

    public MyFollowListAdapter(Context context, List<MyFollowModel> list) {
        super(context, list);
        mContext = context;
    }

    @Override
    public BaseViewHolder<MyFollowModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_my_follow, parent, false);
        return new ItemHolder(view);
    }

    class ItemHolder extends BaseViewHolder<MyFollowModel> {

        @BindView(R.id.img_my_follow_avatar)
        CircleImageView mImgMyFollowAvatar;
        @BindView(R.id.txt_my_follow_name)
        TextView mTxtMyFollowName;

        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(MyFollowModel myFollowModel, int position) {
            mTxtMyFollowName.setText(myFollowModel.getName()==null?"N/A":myFollowModel.getName());
            Glide.with(mContext).load(myFollowModel.getAvatar()).into(mImgMyFollowAvatar);
        }
    }
}
