package cn.abtion.taskgo.mvp.view.task.activity.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.adapter.RecyclerScrollListener;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.model.SimpleUserInfoModel;
import cn.abtion.taskgo.mvp.view.task.adapter.TaskItemListener;
import cn.abtion.taskgo.mvp.view.task.adapter.rec.FinishUserListAdapter;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2018/3/10 on 下午4:23
 * fhyPayaso@qq.com
 */
public class ChooseFinishUserListActivity extends BaseToolBarPresenterActivity implements SwipeRefreshLayout
        .OnRefreshListener,TaskItemListener {


    @BindView(R.id.rec_finish_user_list)
    RecyclerView recFinishUserList;
    @BindView(R.id.fresh_task_list)
    SwipeRefreshLayout mSwipeRefresh;

    private static BaseTaskModel mLostFoundTaskModel;
    private FinishUserListAdapter mAdapter;
    private List<SimpleUserInfoModel> mUserInfoList;

    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_finish_user_list;
    }

    @Override
    protected void initVariable() {

        mUserInfoList = new ArrayList<>();
        mUserInfoList.add(new SimpleUserInfoModel("http://p0y1qzu73.bkt.clouddn.com/17-12-18/59916075.jpg","fhy"));
    }

    @Override
    protected void initView() {
        ToastUtil.showToast("请选择想要完成该任务的用户");
        initToolBar();
        initSwipeRefresh();
        initRecyclerView();
    }

    @Override
    protected void loadData() {

    }


    private void initToolBar() {
        setActivityTitle("用户选择");
    }


    public static void startActivity(Context context, BaseTaskModel taskModel) {
        context.startActivity(new Intent(context, ChooseFinishUserListActivity.class));
        mLostFoundTaskModel = taskModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    private void initSwipeRefresh() {

        mSwipeRefresh.setRefreshing(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }


    private void initRecyclerView() {

        mAdapter = new FinishUserListAdapter(ChooseFinishUserListActivity.this,mUserInfoList);
        mAdapter.setTaskItemListener(this);
        recFinishUserList.setAdapter(mAdapter);
        recFinishUserList.setLayoutManager(new LinearLayoutManager(ChooseFinishUserListActivity.this,
                LinearLayoutManager.VERTICAL,false));
        recFinishUserList.addOnScrollListener(new RecyclerScrollListener() {
            @Override
            public void scrolledToLast() {
                ToastUtil.showToast("到底啦");
            }
        });
    }

    @Override
    public void onRefresh() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }

    @Override
    public void onClickAvatar(int position) {

    }

    @Override
    public void onClickAccept(int position) {

    }
}
