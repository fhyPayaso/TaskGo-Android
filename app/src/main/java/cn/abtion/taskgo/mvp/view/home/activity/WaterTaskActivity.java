package cn.abtion.taskgo.mvp.view.home.activity;

import android.content.Context;
import android.content.Intent;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 上午5:06
 * fhyPayaso@qq.com
 */
public class WaterTaskActivity extends BaseNoBarPresenterActivity{


    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
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

    }

    @Override
    protected void loadData() {

    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,WaterTaskActivity.class));
    }
}
