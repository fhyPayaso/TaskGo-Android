package cn.abtion.taskgo.mvp.view.account;

import android.app.Activity;
import android.content.Intent;
import android.media.session.MediaSession;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarPresenterActivity;
import cn.abtion.taskgo.common.constants.CacheKey;
import cn.abtion.taskgo.data.ChatHelper;
import cn.abtion.taskgo.mvp.contract.account.LoginContract;
import cn.abtion.taskgo.mvp.model.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.presenter.account.LoginPresenter;
import cn.abtion.taskgo.mvp.view.MainActivity;
import cn.abtion.taskgo.utils.CacheUtil;
import cn.abtion.taskgo.utils.ToastUtil;

public class LoginActivity extends BaseNoBarPresenterActivity<LoginContract.Presenter> implements LoginContract.View {


    @BindView(R.id.edit_phone_number)
    EditText meditPhoneNumber;
    @BindView(R.id.edit_password)
    EditText meditPassword;
    @BindView(R.id.btn_login)
    Button mbtnLogin;
    @BindView(R.id.txt_forget_password)
    TextView mtxtForgetPassword;
    @BindView(R.id.txt_register)
    TextView mtxtRegister;


    public static void startActivity(Activity context) {
        context.startActivity(new Intent(context, LoginActivity.class));
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

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        ChatHelper.loginEM();
        mPresenter.requestLogin(new LoginRequestModel(meditPhoneNumber.getText().toString().trim(), meditPassword
                .getText().toString().trim()));
    }

    @OnClick(R.id.txt_forget_password)
    public void onTxtForgetPasswordClicked() {
        ForgetPasswordActivity.startActivity(LoginActivity.this);
    }

    @OnClick(R.id.txt_register)
    public void onTxtRegisterClicked() {
        RegisterActivity.startActivity(LoginActivity.this);
    }



    /**
     * 登陆成功后,进入主页面,结束当前页面
     */
    @Override
    public void onLoginSuccess(String token) {
        CacheUtil.putString(CacheKey.TOKEN, token);
        ToastUtil.showToast("登录成功");
        MainActivity.startActivity(LoginActivity.this);
        finish();
    }

    /**
     * 输入的账号格式有误
     * 重写V层的方法
     */
    @Override
    public void onLoginFailed(String showError) {
        ToastUtil.showToast(showError);
    }
}
