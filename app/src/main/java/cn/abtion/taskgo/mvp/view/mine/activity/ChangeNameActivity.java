package cn.abtion.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;

/**
 * @author：lszr on 2018/1/28 22:16
 * @email：1085963811@qq.com
 */
public class ChangeNameActivity extends BaseToolBarPresenterActivity {
    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_name;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_mine_edit_data_name));
    }

    @Override
    protected void loadData() {

    }
    public static void startChangeNameActivity(Context context){
        Intent intent=new Intent(context,ChangeNameActivity.class);
        context.startActivity(intent);
    }
}
