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
import cn.abtion.taskgo.mvp.contract.mine.MyFansContract;
import cn.abtion.taskgo.mvp.model.mine.MyFansListModel;
import cn.abtion.taskgo.mvp.presenter.mine.MyFansPresneter;
import cn.abtion.taskgo.mvp.view.mine.adapter.MyFansListAdapter;

/**
 * @author：lszr on 2018/1/30 23:28
 * @email：1085963811@qq.com
 */
public class MyFansActivity extends BaseToolBarPresenterActivity<MyFansContract.Presenter> implements MyFansContract.View {
    @BindView(R.id.rec_my_fans)
    RecyclerView recMyFans;
    private List<MyFansListModel> myFansListModels;
    private MyFansListAdapter myFansListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_fans;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_mine_fans_title));
        mPresenter.requestFollowList();
    }

    @Override
    protected void loadData() {

    }

    public static void startMyFansActivity(Context context) {
        Intent intent = new Intent(context, MyFansActivity.class);
        context.startActivity(intent);
    }


    @Override
    public MyFansContract.Presenter initPresenter() {
        return new MyFansPresneter(this);
    }


    @Override
    public void getFansListSuccess(List<MyFansListModel> list) {
        myFansListModels = list;
        initRec();
    }

    private void initRec() {
        myFansListAdapter=new MyFansListAdapter(MyFansActivity.this,myFansListModels);
        recMyFans.setAdapter(myFansListAdapter);
        recMyFans.setLayoutManager(new LinearLayoutManager(MyFansActivity.this, LinearLayoutManager.VERTICAL, false));

        myFansListAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<MyFansListModel>() {
            @Override
            public void onItemClicked(MyFansListModel myFansListModel, BaseRecyclerViewAdapter.BaseViewHolder holder) {
                PersonalPageActivity.startPersonalPageActivity(MyFansActivity.this,myFansListModel.getFollower_id());

            }
        });
    }


}
