package cn.abtion.taskgo.mvp.view.mine.fragment;

import android.content.DialogInterface;
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
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.contract.task.MyTaskListContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.presenter.task.MyTaskListPresenter;
import cn.abtion.taskgo.mvp.view.home.adapter.BtnTaskRecAdapter;
import cn.abtion.taskgo.mvp.view.home.adapter.TaskItemListener;
import cn.abtion.taskgo.mvp.view.mine.adapter.MyAcceptUnfinishedAdapter;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.ToastUtil;

import static cn.abtion.taskgo.utils.Utility.runOnUiThread;

/**
 * @author fhyPayaso
 * @since 2018/2/3 on 上午9:40
 * fhyPayaso@qq.com
 */
public class MyAcceptUnfinishedFragment extends BasePresenterFragment<MyTaskListContract.Presenter> implements TaskItemListener,
        SwipeRefreshLayout.OnRefreshListener,MyTaskListContract.View{

    @BindView(R.id.rec_task_list)
    RecyclerView mRecTaskList;
    @BindView(R.id.fresh_task_list)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;

    private List<BaseTaskModel> mTaskLst;
    private MyAcceptUnfinishedAdapter mAdapter;
    private DialogUtil.CustomAlertDialog dialogTaskInformation;


    @Override
    protected MyTaskListContract.Presenter initPresenter() {
        return new MyTaskListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_accept_unfinished;
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
                        mPresenter.loadMyAcceptTaskList(0);
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }

    private void initRecyclerView() {


        mAdapter = new MyAcceptUnfinishedAdapter(getContext(), mTaskLst);
        mAdapter.setTaskItemListener(this);
        mAdapter.setButtonContent("完成");
        mRecTaskList.setAdapter(mAdapter);
        mRecTaskList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        mAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<BaseTaskModel>() {
            @Override
            public void onItemClicked(BaseTaskModel baseTaskModel, BaseRecyclerViewAdapter.BaseViewHolder holder) {
                if (baseTaskModel.getTaskType() == 0) {
                    showWaterTaskInformation(baseTaskModel);
                } else if (baseTaskModel.getTaskType() == 1) {
                    showLostFoundTaskInformation(baseTaskModel);
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


    /**
     * 显示送水任务详细信息
     */
    private void showWaterTaskInformation(BaseTaskModel model) {


        dialogTaskInformation = new DialogUtil().new CustomAlertDialog();
        dialogTaskInformation
                .initDialog(getContext(), R.layout.dialog_water_task_information)
                .setCanceledOntouchOutside(true)
                .showDialog();

        View view = dialogTaskInformation.getView();
        TextView mTxtAddressNumber = view.findViewById(R.id.txt_address_number);
        TextView mTxtWaterTaskType = view.findViewById(R.id.txt_water_task_type);
        TextView btnConfirm = view.findViewById(R.id.btn_information_confirm);
        mTxtAddressNumber.setText(model.getMainValue());
        mTxtWaterTaskType.setText(model.getSubTitle());
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTaskInformation.hideDialog();
            }
        });
    }

    /**
     * 显示物品任务详细信息
     */
    private void showLostFoundTaskInformation(BaseTaskModel model) {

        dialogTaskInformation = new DialogUtil().new CustomAlertDialog();
        dialogTaskInformation
                .initDialog(getContext(), R.layout.dialog_lost_found_information)
                .setCanceledOntouchOutside(true)
                .showDialog();

        View view = dialogTaskInformation.getView();
        TextView txtItemName = view.findViewById(R.id.txt_item_name);
        TextView txtLostFoundType = view.findViewById(R.id.txt_item_task_type);
        TextView btnConfirm = view.findViewById(R.id.btn_information_confirm);
        txtItemName.setText(model.getMainValue());
        txtLostFoundType.setText(model.getSubTitle() == null ? "" : model.getSubTitle());
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTaskInformation.hideDialog();
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
                        mPresenter.loadMyAcceptTaskList(1);
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }

    @Override
    public void onClickAvatar(int position) {

        ToastUtil.showToast("点击了头像" + position);
    }

    @Override
    public void onClickAccept(final int position) {

        DialogUtil.NativeDialog dialogAccept = new DialogUtil().new NativeDialog();
        dialogAccept
                .singleInit(getContext())
                .setTitle("确认完成该任务？")
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

                        mAdapter.removeItem(position);
                        dialog.dismiss();
                    }
                })
                .showNativeDialog();
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
        mAdapter.removeItem(position);
        ToastUtil.showToast("完成");
    }
}
