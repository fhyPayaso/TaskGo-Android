package cn.abtion.taskgo.mvp.view.home.activity;

import android.content.Context;
import android.content.Intent;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;

/**
 * @author FanHongyu.
 * @since 18/1/30 12:47.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseLostFoundTaskActivity extends BaseToolBarPresenterActivity{

    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_lost_found_task;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        setActivityTitle("发布任务");

    }

    @Override
    protected void loadData() {

    }



    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,ReleaseLostFoundTaskActivity.class));
    }

}
