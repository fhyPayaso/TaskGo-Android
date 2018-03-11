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
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.contract.task.FinishUserListContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.model.SimpleUserInfoModel;
import cn.abtion.taskgo.mvp.model.task.request.FinishLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.FinishUserListRequest;
import cn.abtion.taskgo.mvp.presenter.task.FinishUserListPresenter;
import cn.abtion.taskgo.mvp.view.task.adapter.TaskItemListener;
import cn.abtion.taskgo.mvp.view.task.adapter.rec.FinishUserListAdapter;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2018/3/10 on 下午4:23
 * fhyPayaso@qq.com
 */
public class ChooseFinishUserListActivity extends BaseToolBarPresenterActivity<FinishUserListContract.Presenter> implements SwipeRefreshLayout
        .OnRefreshListener, TaskItemListener, FinishUserListContract.View {


    @BindView(R.id.rec_finish_user_list)
    RecyclerView recFinishUserList;
    @BindView(R.id.fresh_task_list)
    SwipeRefreshLayout mSwipeRefresh;

    private static BaseTaskModel mLostFoundTaskModel;
    private FinishUserListAdapter mAdapter;
    private List<SimpleUserInfoModel> mUserInfoList;


    @Override
    public FinishUserListContract.Presenter initPresenter() {
        return new FinishUserListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_finish_user_list;
    }

    @Override
    protected void initVariable() {

        mUserInfoList = new ArrayList<>();
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
                        mPresenter.loadFinishUserList(new FinishUserListRequest(
                                String.valueOf(mLostFoundTaskModel.getTaskId()), String.valueOf(mLostFoundTaskModel.getTaskType())));
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }


    private void initRecyclerView() {

        mAdapter = new FinishUserListAdapter(ChooseFinishUserListActivity.this, mUserInfoList);
        mAdapter.setTaskItemListener(this);
        recFinishUserList.setAdapter(mAdapter);
        recFinishUserList.setLayoutManager(new LinearLayoutManager(ChooseFinishUserListActivity.this,
                LinearLayoutManager.VERTICAL, false));
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
                        mPresenter.loadFinishUserList(new FinishUserListRequest(
                                String.valueOf(mLostFoundTaskModel.getTaskId()), String.valueOf(mLostFoundTaskModel.getTaskType())));
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }


    /**
     * 点击头衔点击事件
     * @param position item位置
     */
    @Override
    public void onClickAvatar(int position) {
        ToastUtil.showToast("点击了头像" + position);
    }

    /**
     * 点击完成按钮点击事件
     * @param position item位置
     */
    @Override
    public void onClickAccept(int position) {


        mPresenter.finishLostFoundTask(new FinishLostFoundTaskRequest(mUserInfoList.get(position).getUserId()
                , String.valueOf(mLostFoundTaskModel.getTaskId())
                , String.valueOf(mLostFoundTaskModel.getTaskType())), position);
    }


    /**
     * 用户列表加载成功回调
     * @param modelList
     */
    @Override
    public void onLoadUserListSuccess(List<SimpleUserInfoModel> modelList) {
        mUserInfoList.clear();
        mUserInfoList.addAll(modelList);
        mAdapter.notifyDataSetChanged();
        mSwipeRefresh.setRefreshing(false);
    }


    /**
     * 完成任务成功回调
     * @param position
     */
    @Override
    public void onFinishSuccess(int position) {
        mAdapter.removeItem(position);
        mAdapter.notifyDataSetChanged();
        ToastUtil.showToast("任务完成成功");
    }
}
