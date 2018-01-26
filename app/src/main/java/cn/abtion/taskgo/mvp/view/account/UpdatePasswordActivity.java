package cn.abtion.taskgo.mvp.view.account;

import android.content.Context;
import android.content.Intent;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;

/**
 * Created by heaijia on 2018/1/26.
 */

public class UpdatePasswordActivity extends BaseNoBarActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, UpdatePasswordActivity.class));
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_password;
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
}
