package cn.abtion.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.mvp.contract.mine.MyFollowContract;
import cn.abtion.taskgo.mvp.model.mine.MyFollowModel;
import cn.abtion.taskgo.mvp.presenter.mine.MyFollowPresenter;
import cn.abtion.taskgo.mvp.view.mine.adapter.MyFollowListAdapter;

/**
 * @author：lszr on 2018/1/27 02:10
 * @email：1085963811@qq.com
 */
public class MyFollowActivity extends BaseToolBarPresenterActivity<MyFollowContract.Presenter> implements MyFollowContract.View {


    public MyFollowListAdapter myFollowListAdapter;
    @BindView(R.id.rec_my_follow)
    RecyclerView recMyFollow;

    private List<MyFollowModel> myFollowModels;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_follow;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_mine_follow_title));
        mPresenter.requestFollowList();

    }

    @Override
    protected void loadData() {

    }


    public void initRec() {
        myFollowListAdapter=new MyFollowListAdapter(MyFollowActivity.this,myFollowModels);
        recMyFollow.setAdapter(myFollowListAdapter);
        recMyFollow.setLayoutManager(new LinearLayoutManager(MyFollowActivity.this, LinearLayoutManager.VERTICAL, false));

        myFollowListAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<MyFollowModel>() {
            @Override
            public void onItemClicked(MyFollowModel myFollowModel, BaseRecyclerViewAdapter.BaseViewHolder holder) {
                PersonalPageActivity.startPersonalPageActivity(MyFollowActivity.this,myFollowModel.getUser_id());
            }
        });
    }


    public static void startMyFollowActivity(Context context) {
        Intent intent = new Intent(context, MyFollowActivity.class);
        context.startActivity(intent);
    }


    @Override
    public MyFollowContract.Presenter initPresenter() {
        return new MyFollowPresenter(this);
    }

    @Override
    public void getListSuccess(List<MyFollowModel> list) {
        myFollowModels=list;
        initRec();

    }
}
