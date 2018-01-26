package cn.abtion.taskgo.mvp.view.account;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarPresenterActivity;
import cn.abtion.taskgo.mvp.contract.LoginContract;
import cn.abtion.taskgo.mvp.presenter.LoginPresenter;
import cn.abtion.taskgo.mvp.view.MainActivity;

public class LoginActivity extends BaseNoBarPresenterActivity<LoginContract.Presenter> implements LoginContract.View {


    /**
     * 登陆成功后,进入主页面,结束当前页面
     */
    @Override
    public void onLoginSuccess() {

//        ToastUtil.showToast("登录成功");
//        MainActivity.startActivity(this);
//        this.finish();
    }


    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        MainActivity.startActivity(LoginActivity.this);
    }
}
