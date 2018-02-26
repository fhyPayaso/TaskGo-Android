package cn.abtion.taskgo.mvp.view.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.mvp.model.mine.MyFollowModel;

/**
 * @author：lszr on 2018/1/31 00:15
 * @email：1085963811@qq.com
 */
public class MyFollowListAdapter extends BaseRecyclerViewAdapter<MyFollowModel> {
    public MyFollowListAdapter(Context context, List<MyFollowModel> myFollowModels) {
        super(context, myFollowModels);
    }


    @Override
    public BaseViewHolder<MyFollowModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_rec_mine_follow, parent, false);
        return null;
    }

}
