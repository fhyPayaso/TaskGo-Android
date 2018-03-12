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
import cn.abtion.taskgo.mvp.contract.account.LoginContract;
import cn.abtion.taskgo.mvp.model.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.presenter.account.LoginPresenter;
import cn.abtion.taskgo.mvp.view.MainActivity;
import cn.abtion.taskgo.utils.CacheUtil;
import cn.abtion.taskgo.utils.ToastUtil;

public class LoginActivity extends BaseNoBarPresenterActivity<LoginContract.Presenter> implements LoginContract.View {


    @BindView(R.id.logo_login)
    ImageView mlogoLogin;
    @BindView(R.id.ly_header)
    LinearLayout mlyHeader;
    @BindView(R.id.edit_phone_number)
    EditText meditPhoneNumber;
    @BindView(R.id.ly_phone_number)
    LinearLayout mlyPhoneNumber;
    @BindView(R.id.line1)
    View mline1;
    @BindView(R.id.edit_password)
    EditText meditPassword;
    @BindView(R.id.ly_password)
    LinearLayout mlyPassword;
    @BindView(R.id.line2)
    View mline2;
    @BindView(R.id.btn_login)
    Button mbtnLogin;
    @BindView(R.id.txt_forget_password)
    TextView mtxtForgetPassword;
    @BindView(R.id.txt_register)
    TextView mtxtRegister;
    @BindView(R.id.footer_line1)
    View mfooterLine1;
    @BindView(R.id.footer_line2)
    View mfooterLine2;

    /**
     * 登陆成功后,进入主页面,结束当前页面
     */
    @Override
    public void onLoginSuccess(String token) {
        ToastUtil.showToast("登录成功");
        MainActivity.startActivity(LoginActivity.this);

        CacheUtil.putString(CacheKey.TOKEN, token);

        Log.i("token", "onLoginSuccess: "+token);

        ToastUtil.showToast(token);


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


        emChatTest();
        MainActivity.startActivity(LoginActivity.this);
        //mPresenter.requestLogin(new LoginRequestModel(meditPhoneNumber.getText().toString().trim(),meditPassword.getText().toString().trim()));
    }

    /**
     * 聊天系统登录测试
     */
    private void emChatTest() {

        EMClient.getInstance().login("111111", "111111", new EMCallBack() {
            @Override
            public void onSuccess() {

                Log.i("login", "onSuccess: 登录成功");

            }

            @Override
            public void onError(int code, String error) {

                Log.i("login", "onError: 登录失败，" + error);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }


    @OnClick(R.id.txt_forget_password)
    public void onTxtForgetPasswordClicked() {

        ForgetPasswordActivity.startActivity(LoginActivity.this);

    }

    @OnClick(R.id.txt_register)
    public void onTxtRegisterClicked() {
        RegisterActivity.startActivity(LoginActivity.this);
        //    this.finish();
    }


}
