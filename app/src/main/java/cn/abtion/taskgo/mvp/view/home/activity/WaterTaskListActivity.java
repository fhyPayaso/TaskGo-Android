package cn.abtion.taskgo.mvp.view.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarPresenterActivity;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.model.request.home.WaterTaskModel;
import cn.abtion.taskgo.mvp.view.home.WaterTaskRecAdapter;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 上午5:06
 * fhyPayaso@qq.com
 */
public class WaterTaskListActivity extends BaseToolBarPresenterActivity {


    @BindView(R.id.txt_total_number)
    TextView txtTotalNumber;
    @BindView(R.id.rec_water_task)
    RecyclerView recWaterTask;
    WaterTaskRecAdapter mAdapter;
    LinearLayoutManager mLayoutManager;



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
    }

    private void initRecyclerView() {


        List<WaterTaskModel> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new WaterTaskModel("111", "fhyPayaso",
                    "7795", "送水上门", "12-09 14:20"));
        }
        mAdapter = new WaterTaskRecAdapter(WaterTaskListActivity.this, list);
        mLayoutManager = new LinearLayoutManager(WaterTaskListActivity.this, LinearLayoutManager.VERTICAL, false);
        recWaterTask.setLayoutManager(mLayoutManager);
        recWaterTask.setAdapter(mAdapter);
    }


    @OnClick(R.id.btn_accept_all)
    public void onBtnAcceptAllClicked() {

        ToastUtil.showToast("一键接受");
    }

    @OnClick(R.id.btn_release_task)
    public void onViewClicked() {
        ReleaseWaterTaskActivity.startActivity(WaterTaskListActivity.this);
    }
}
