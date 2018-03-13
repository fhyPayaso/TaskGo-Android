package cn.abtion.taskgo.mvp.view.task.activity.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.base.adapter.RecyclerScrollListener;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.contract.task.WaterTaskListContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.presenter.task.WaterTaskListPresenter;
import cn.abtion.taskgo.mvp.view.mine.activity.PersonalPageActivity;
import cn.abtion.taskgo.mvp.view.task.activity.SearchTaskActivity;
import cn.abtion.taskgo.mvp.view.task.activity.release.ReleaseWaterTaskActivity;
import cn.abtion.taskgo.mvp.view.task.adapter.rec.BtnTaskRecAdapter;
import cn.abtion.taskgo.mvp.view.task.adapter.TaskItemListener;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 上午5:06
 * fhyPayaso@qq.com
 */
public class WaterTaskListActivity extends BaseToolBarPresenterActivity<WaterTaskListContract.Presenter> implements
        TaskItemListener, SwipeRefreshLayout.OnRefreshListener, WaterTaskListContract.View {


    @BindView(R.id.txt_total_number)
    TextView txtTotalNumber;
    @BindView(R.id.rec_water_task)
    RecyclerView recWaterTask;
    @BindView(R.id.fresh_task_list)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.btn_releasze_task)
    FloatingActionButton btnReleaszeTask;


    private BtnTaskRecAdapter mAdapter;
    private List<BaseTaskModel> mWaterTaskList;


    @Override
    public WaterTaskListContract.Presenter initPresenter() {
        return new WaterTaskListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_water_task;
    }

    @Override
    protected void initVariable() {

        mWaterTaskList = new ArrayList<>();
    }

    @Override
    protected void initView() {

        initToolBar();
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void loadData() {

    }


    /**
     * 初始化ToolBar
     */
    private void initToolBar() {

        setActivityTitle(getString(R.string.title_water_task));
        setToolBarMenu(R.drawable.ic_search);
        getToolBar().findViewById(R.id.img_toolbar_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchTaskActivity.startActivity(WaterTaskListActivity.this);
            }
        });
        txtTotalNumber.setText("0");
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
                        mPresenter.loadWaterTaskList();
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

        mAdapter = new BtnTaskRecAdapter(WaterTaskListActivity.this, mWaterTaskList);
        mAdapter.setTaskItemListener(this);
        recWaterTask.setAdapter(mAdapter);
        recWaterTask.setLayoutManager(new LinearLayoutManager(WaterTaskListActivity.this, LinearLayoutManager
                .VERTICAL, false));

        recWaterTask.addOnScrollListener(new RecyclerScrollListener(btnReleaszeTask) {
            @Override
            public void scrolledToLast() {
                ToastUtil.showToast("到底啦");
            }
        });
    }

    @OnClick(R.id.btn_accept_all)
    public void onBtnAcceptAllClicked() {

        DialogUtil.NativeDialog dialogAcceptAll = new DialogUtil().new NativeDialog();
        dialogAcceptAll
                .singleInit(WaterTaskListActivity.this)
                .setTitle(getString(R.string.dialog_title_if_accept_all))
                .setMessage(getString(R.string.dialog_message_clear_list))
                .setNegativeButton(getString(R.string.txt_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.txt_confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (mWaterTaskList.size() == 0) {

                            ToastUtil.showToast(R.string.toast_no_task_to_accept);
                        } else {
                            mPresenter.acceptAllWaterTask();
                        }
                        dialog.dismiss();
                    }
                })
                .showNativeDialog();
    }


    /**
     * 下拉刷新事件
     */
    @Override
    public void onRefresh() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.loadWaterTaskList();
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        initRefreshLayout();
    }

    @Override
    public void onClickAvatar(int position) {
        PersonalPageActivity.startPersonalPageActivity(WaterTaskListActivity.this, mWaterTaskList.get(position)
                .getUserId());
    }

    /**
     * 接受任务点击事件
     *
     * @param position item位置
     */
    @Override
    public void onClickAccept(final int position) {

        DialogUtil.NativeDialog dialogAccept = new DialogUtil().new NativeDialog();
        dialogAccept
                .singleInit(WaterTaskListActivity.this)
                .setTitle(getString(R.string.dialog_title_if_accept))
                .setMessage(getString(R.string.dialog_message_remove_task))
                .setNegativeButton(getString(R.string.txt_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.txt_confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mPresenter.acceptWaterTask(mWaterTaskList.get(position).getTaskId(), position);
                        dialog.dismiss();
                    }
                })
                .showNativeDialog();
    }

    /**
     * 加载成功回调
     *
     * @param modelList
     */
    @Override
    public void onLoadDataSuccess(List<BaseTaskModel> modelList) {

        txtTotalNumber.setText(String.valueOf(mWaterTaskList.size()));
        mWaterTaskList.clear();
        mWaterTaskList.addAll(modelList);
        mAdapter.notifyDataSetChanged();
        mSwipeRefresh.setRefreshing(false);
    }


    /**
     * 接受成功回调
     *
     * @param position
     */
    @Override
    public void onAcceptSuccess(int position) {

        mAdapter.removeItem(position);
        mWaterTaskList.clear();
        txtTotalNumber.setText(String.valueOf(mWaterTaskList.size()));
        ToastUtil.showToast("接受任务成功");
    }

    @Override
    public void onAcceptAllSuccess() {

        mAdapter.clearAllItems();
        ToastUtil.showToast(R.string.toast_accept_all_successful);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, WaterTaskListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_releasze_task)
    public void onViewClicked() {
        ReleaseWaterTaskActivity.startActivity(WaterTaskListActivity.this);
    }
}
