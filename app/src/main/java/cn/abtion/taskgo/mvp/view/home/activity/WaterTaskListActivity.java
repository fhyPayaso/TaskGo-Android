package cn.abtion.taskgo.mvp.view.home.activity;

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
import cn.abtion.taskgo.mvp.presenter.task.WaterTaskListPresenter;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.view.home.adapter.BtnTaskRecAdapter;
import cn.abtion.taskgo.mvp.view.home.adapter.TaskItemListener;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.ToastUtil;

import static cn.abtion.taskgo.utils.Utility.runOnUiThread;

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
    @BindView(R.id.btn_release_task)
    FloatingActionButton btnReleaseTask;


    private BtnTaskRecAdapter mAdapter;
    private List<BaseTaskModel> mWaterTaskList;
    private DialogUtil.CustomAlertDialog dialogTaskInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

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

        mAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<BaseTaskModel>() {
            @Override
            public void onItemClicked(BaseTaskModel baseTaskModel, BaseRecyclerViewAdapter.BaseViewHolder holder) {

                showDialogTaskInformation(baseTaskModel);
            }
        });

        recWaterTask.addOnScrollListener(new RecyclerScrollListener(btnReleaseTask) {
            @Override
            public void scrolledToLast() {
                ToastUtil.showToast("到底啦");
            }
        });
    }


    /**
     * 显示任务详细信息
     */
    private void showDialogTaskInformation(BaseTaskModel model) {

        dialogTaskInformation = new DialogUtil().new CustomAlertDialog();
        dialogTaskInformation.initDialog(WaterTaskListActivity.this, R.layout.dialog_water_task_information);
        dialogTaskInformation.setCanceledOntouchOutside(true);
        dialogTaskInformation.showDialog();

        View view = dialogTaskInformation.getView();
        TextView mTxtAddressNumber = view.findViewById(R.id.txt_address_number);
        TextView mTxtWaterTaskType = view.findViewById(R.id.txt_water_task_type);
        TextView mBtnInformationConfirm = view.findViewById(R.id.btn_information_confirm);
        mTxtAddressNumber.setText(model.getMainValue());
        mTxtWaterTaskType.setText(model.getSubTitle());
        mBtnInformationConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTaskInformation.hideDialog();
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
                            mAdapter.clearAllItems();
                            ToastUtil.showToast(R.string.toast_accept_all_successful);
                        }
                        dialog.dismiss();
                    }
                })
                .showNativeDialog();
    }


    /**
     * 发布任务按钮点击事件
     */
    @OnClick(R.id.btn_release_task)
    public void onViewClicked() {
        ReleaseWaterTaskActivity.startActivity(WaterTaskListActivity.this);
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
        ToastUtil.showToast("点击了头像" + position);
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

        mWaterTaskList.clear();
        mWaterTaskList.addAll(modelList);
        mSwipeRefresh.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
        txtTotalNumber.setText(String.valueOf(mWaterTaskList.size()));
    }

    /**
     * 接受成功回调
     *
     * @param position
     */
    @Override
    public void onAcceptSuccess(int position) {

        mAdapter.removeItem(position);
        txtTotalNumber.setText(String.valueOf(mWaterTaskList.size()));
        ToastUtil.showToast("接受任务成功");
    }

    @Override
    public void onAcceptAllSuccess() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, WaterTaskListActivity.class));
    }
}
