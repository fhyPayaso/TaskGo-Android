package cn.abtion.taskgo.mvp.view.task.fragment.myreleased;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.base.adapter.RecyclerScrollListener;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.contract.task.MyTaskListContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.response.LostFoundTaskResponse;
import cn.abtion.taskgo.mvp.presenter.task.MyTaskListPresenter;
import cn.abtion.taskgo.mvp.view.mine.activity.PersonalPageActivity;
import cn.abtion.taskgo.mvp.view.task.activity.home.LostAndFoundTaskActivity;
import cn.abtion.taskgo.mvp.view.task.adapter.rec.NoBtnTaskRecAdapter;
import cn.abtion.taskgo.mvp.view.task.adapter.TaskItemListener;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.ToastUtil;

import static cn.abtion.taskgo.utils.Utility.runOnUiThread;

/**
 * @author fhyPayaso
 * @since 2018/2/3 on 上午9:45
 * fhyPayaso@qq.com
 */
public class MyReleasedFinishedFragment extends BasePresenterFragment<MyTaskListContract.Presenter> implements TaskItemListener,
        SwipeRefreshLayout.OnRefreshListener,MyTaskListContract.View{

    @BindView(R.id.rec_task_list)
    RecyclerView mRecTaskList;
    @BindView(R.id.fresh_task_list)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;

    private List<BaseTaskModel> mTaskLst;
    private NoBtnTaskRecAdapter mAdapter;
    private DialogUtil.CustomAlertDialog dialogTaskInformation;


    @Override
    protected MyTaskListContract.Presenter initPresenter() {
        return new MyTaskListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_released_finished;
    }

    @Override
    protected void initVariable() {
        mTaskLst = new ArrayList<>();
    }

    @Override
    protected void initView() {

        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void loadData() {

    }

    /**
     * 初始化SwipeRefreshLayout
     */
    private void initRefreshLayout() {

        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeRefresh.setRefreshing(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.loadMyReleaseTaskList(2);
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }

    private void initRecyclerView() {


        mAdapter = new NoBtnTaskRecAdapter(getContext(), mTaskLst);
        mAdapter.setTaskItemListener(this);
        mRecTaskList.setAdapter(mAdapter);
        mRecTaskList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        mAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<BaseTaskModel>() {
            @Override
            public void onItemClicked(BaseTaskModel baseTaskModel, BaseRecyclerViewAdapter.BaseViewHolder holder) {
                if (baseTaskModel.getTaskType() == 1) {
                    mPresenter.loadTaskInfo(baseTaskModel.getTaskId());
                }
            }
        });

        mRecTaskList.addOnScrollListener(new RecyclerScrollListener() {
            @Override
            public void scrolledToLast() {
                ToastUtil.showToast("到底了");
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
                        mPresenter.loadMyReleaseTaskList(2);
                    }
                });
            }
        }, Config.REFRESH_TIME);

    }

    @Override
    public void onClickAvatar(int position) {
        PersonalPageActivity.startPersonalPageActivity(getContext(),mTaskLst.get(position).getUserId());
    }

    @Override
    public void onClickAccept(int position) {

    }


    @Override
    public void onLoadSuccess(List<BaseTaskModel> taskModelList) {
        mTaskLst.clear();
        mTaskLst.addAll(taskModelList);
        mSwipeRefresh.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinishSuccess(int position) {

    }

    @Override
    public void onLoadTaskInfoSuccess(LostFoundTaskResponse response) {
        dialogTaskInformation = new DialogUtil().new CustomAlertDialog();
        LostAndFoundTaskActivity.showLostFoundTaskInfo(getContext(),dialogTaskInformation,response);
    }


}
