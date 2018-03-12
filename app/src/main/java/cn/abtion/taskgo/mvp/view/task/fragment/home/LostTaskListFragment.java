package cn.abtion.taskgo.mvp.view.task.fragment.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.base.adapter.RecyclerScrollListener;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.contract.task.LostFoundTaskListContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.request.AcceptLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.response.LostFoundTaskResponse;
import cn.abtion.taskgo.mvp.presenter.task.LostFoundTaskListPresenter;
import cn.abtion.taskgo.mvp.view.mine.activity.PersonalPageActivity;
import cn.abtion.taskgo.mvp.view.task.activity.home.LostAndFoundTaskActivity;
import cn.abtion.taskgo.mvp.view.task.adapter.rec.BtnTaskRecAdapter;
import cn.abtion.taskgo.mvp.view.task.adapter.TaskItemListener;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.ToastUtil;

import static cn.abtion.taskgo.utils.Utility.runOnUiThread;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 下午11:31
 * fhyPayaso@qq.com
 */
public class LostTaskListFragment extends BasePresenterFragment<LostFoundTaskListContract.Presenter> implements
        TaskItemListener, SwipeRefreshLayout.OnRefreshListener, LostFoundTaskListContract.View {

    @BindView(R.id.rec_lost_find_task)
    RecyclerView recLostFindTask;
    @BindView(R.id.fresh_task_list)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;

    private static final int TYPE_LOST_TASK = 0;
    private BtnTaskRecAdapter mAdapter;
    private List<BaseTaskModel> mLostFindTaskLst;


    @Override
    protected LostFoundTaskListContract.Presenter initPresenter() {
        return new LostFoundTaskListPresenter(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lost_task_list;
    }

    @Override
    protected void initVariable() {

        mLostFindTaskLst = new ArrayList<>();
    }

    @Override
    protected void initView() {

        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void loadData() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        initRefreshLayout();
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
                        mPresenter.loadTaskList();
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }

    private void initRecyclerView() {

        mAdapter = new BtnTaskRecAdapter(getContext(), mLostFindTaskLst);
        mAdapter.setTaskItemListener(this);
        recLostFindTask.setAdapter(mAdapter);
        recLostFindTask.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        mAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<BaseTaskModel>() {
            @Override
            public void onItemClicked(BaseTaskModel baseTaskModel, BaseRecyclerViewAdapter.BaseViewHolder holder) {
                mPresenter.loadTaskInfo(baseTaskModel.getTaskId());
            }
        });

        recLostFindTask.addOnScrollListener(new RecyclerScrollListener((FloatingActionButton) getActivity()
                .findViewById(R.id.btn_release_task)) {
            @Override
            public void scrolledToLast() {
                ToastUtil.showToast("到底了");
            }
        });
    }




    @Override
    public void onClickAvatar(int position) {
        PersonalPageActivity.startPersonalPageActivity(getContext(),mLostFindTaskLst.get(position).getUserId());
    }

    @Override
    public void onClickAccept(final int position) {

        DialogUtil.NativeDialog dialogAccept = new DialogUtil().new NativeDialog();
        dialogAccept
                .singleInit(getContext())
                .setTitle(getString(R.string.dialog_title_if_accept))
                .setMessage("物品任务可多人接受")
                .setNegativeButton(getString(R.string.txt_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.txt_confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        BaseTaskModel model = mLostFindTaskLst.get(position);
                        mPresenter.acceptTask(new AcceptLostFoundTaskRequest(model.getTaskId()
                                , TYPE_LOST_TASK, model.getMainValue()), position);
                        dialog.dismiss();
                    }
                })
                .showNativeDialog();
    }

    @Override
    public void onRefresh() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.loadTaskList();
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }


    @Override
    public void onLoadDataSuccess(List<BaseTaskModel> lostTaskList, List<BaseTaskModel> foundTaskList) {

        mLostFindTaskLst.clear();
        mLostFindTaskLst.addAll(lostTaskList);
        mSwipeRefresh.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAcceptSuccess(int position) {

        ToastUtil.showToast("接受任务成功");
    }



    /**
     * 显示任务详细信息
     */
    @Override
    public void onLoadTaskInfoSuccess(LostFoundTaskResponse response) {

        DialogUtil.CustomAlertDialog dialogTaskInformation = new DialogUtil().new CustomAlertDialog();
        LostAndFoundTaskActivity.showLostFoundTaskInfo(getContext(),dialogTaskInformation,response);
    }
}
