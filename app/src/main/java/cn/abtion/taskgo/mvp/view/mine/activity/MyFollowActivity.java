package cn.abtion.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;

/**
 * @author：lszr on 2018/1/27 02:10
 * @email：1085963811@qq.com
 */
public class MyFollowActivity extends BaseToolBarPresenterActivity{
    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_follow;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_mine_follow_title));


    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public static void startMyFollowActivity(Context context){
        Intent intent=new Intent(context,MyFollowActivity.class);
        context.startActivity(intent);
    }
}