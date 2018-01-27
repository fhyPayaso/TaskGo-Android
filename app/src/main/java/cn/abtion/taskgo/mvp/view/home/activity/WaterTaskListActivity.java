package cn.abtion.taskgo.mvp.view.home.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.model.request.home.WaterTaskModel;
import cn.abtion.taskgo.mvp.view.home.adapter.TaskListener;
import cn.abtion.taskgo.mvp.view.home.adapter.WaterTaskRecAdapter;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 上午5:06
 * fhyPayaso@qq.com
 */
public class WaterTaskListActivity extends BaseToolBarPresenterActivity implements TaskListener, SwipeRefreshLayout
        .OnRefreshListener {


    @BindView(R.id.txt_total_number)
    TextView txtTotalNumber;
    @BindView(R.id.rec_water_task)
    RecyclerView recWaterTask;
    @BindView(R.id.fresh_task_list)
    SwipeRefreshLayout mSwipeRefresh;
    WaterTaskRecAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    List<WaterTaskModel> mWaterTaskList;


    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_water_task;
    }

    @Override
    protected void initVariable() {


    }

    @Override
    protected void initView() {

        initToolBar();

        mWaterTaskList = new ArrayList<>();
        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeRefresh.setRefreshing(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            mWaterTaskList.add(new WaterTaskModel("111", "fhyPayaso",
                                    "7795", "送水上门", "12-09 14:20"));
                        }
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }, 2000);

        initRecyclerView();
    }

    @Override
    protected void loadData() {

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


    private void initToolBar() {

        setActivityTitle("送水任务");
        setToolBarMenu(R.drawable.ic_search);

        getToolBar().findViewById(R.id.img_toolbar_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchTaskActivity.startActivity(WaterTaskListActivity.this);
            }
        });
    }

    private void initRecyclerView() {

        mAdapter = new WaterTaskRecAdapter(WaterTaskListActivity.this, mWaterTaskList);
        mAdapter.setTaskListener(this);
        mLayoutManager = new LinearLayoutManager(WaterTaskListActivity.this, LinearLayoutManager.VERTICAL, false);
        recWaterTask.setLayoutManager(mLayoutManager);
        recWaterTask.setAdapter(mAdapter);
        mAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<WaterTaskModel>() {
            @Override
            public void onItemClicked(WaterTaskModel waterTaskModel, BaseRecyclerViewAdapter.BaseViewHolder holder) {

                DialogUtil.CustomAlertDialog dialog = new DialogUtil().new CustomAlertDialog();
                dialog.initDialog(WaterTaskListActivity.this, R.layout.dialog_water_task_information);
                dialog.setCanceledOntouchOutside(true);
                dialog.showDialog();

            }
        });
    }


    @OnClick(R.id.btn_accept_all)
    public void onBtnAcceptAllClicked() {

        DialogUtil.NativeDialog dialogAcceptAll = new DialogUtil().new NativeDialog();
        dialogAcceptAll
                .singleInit(WaterTaskListActivity.this)
                .setTitle("是否接受全部任务?")
                .setMessage("确认后将清空列表")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mAdapter.clearAllItems();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if ( mWaterTaskList .size() == 0) {

                            ToastUtil.showToast("当前没有可接受的任务");

                        } else {
                            mAdapter.clearAllItems();
                            ToastUtil.showToast("接受了全部任务");
                        }
                        dialog.dismiss();
                    }
                })
                .showNativeDialog();
    }


    @OnClick(R.id.btn_release_task)
    public void onViewClicked() {
        ReleaseWaterTaskActivity.startActivity(WaterTaskListActivity.this);
    }

    @Override
    public void onRefresh() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 3; i++) {
                            mWaterTaskList.add(new WaterTaskModel("111", "fhyPayaso",
                                    "7795", "送水上门", "12-09 14:20"));
                        }

                        mAdapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }, 2000);

    }

    @Override
    public void onClickAvatar(int position) {
        ToastUtil.showToast("点击了头像" + position);
    }

    @Override
    public void onClickAccept(final int position) {

        DialogUtil.NativeDialog dialogAccept = new DialogUtil().new NativeDialog();
        dialogAccept
                .singleInit(WaterTaskListActivity.this)
                .setTitle("是否接受该任务?")
                .setMessage("确认后将从任务列表消失")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showToast("铁头娃接受了任务" + position);
                        mAdapter.removeItem(position);
                        dialog.dismiss();
                    }
                })
                .showNativeDialog();
    }
}
