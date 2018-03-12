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
import cn.abtion.taskgo.mvp.model.mine.MyFansListModel;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author：lszr on 2018/3/12 18:06
 * @email：1085963811@qq.com
 */
public class MyFansListAdapter extends BaseRecyclerViewAdapter<MyFansListModel> {
    private Context mContext;

    public MyFansListAdapter(Context context, List<MyFansListModel> myFansListModels) {
        super(context, myFansListModels);
        mContext = context;
    }

    @Override
    public BaseViewHolder<MyFansListModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_my_follow, parent, false);

        return new ItemHolder(view);
    }

    class ItemHolder extends BaseViewHolder<MyFansListModel> {

        @BindView(R.id.img_my_follow_avatar)
        CircleImageView mImgMyFollowAvatar;
        @BindView(R.id.txt_my_follow_name)
        TextView mTxtMyFollowName;



        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(MyFansListModel myFansListModel, int position) {
            mTxtMyFollowName.setText(myFansListModel.getName()==null?"N/A":myFansListModel.getName());
            Glide.with(mContext).load(myFansListModel.getAvatar()).into(mImgMyFollowAvatar);
        }
    }

}
