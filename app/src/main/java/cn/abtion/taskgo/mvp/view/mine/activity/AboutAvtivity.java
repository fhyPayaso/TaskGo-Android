package cn.abtion.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;

/**
 * @author：lszr on 2018/2/2 21:50
 * @email：1085963811@qq.com
 */
public class AboutAvtivity extends BaseToolBarPresenterActivity {
    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_about));

    }

    @Override
    protected void loadData() {

    }
    public static void startAboutActivity(Context context){
        Intent intent=new Intent(context,AboutAvtivity.class);
        context.startActivity(intent);
    }
}
