package cn.abtion.taskgo.mvp.view.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarPresenterActivity;
import cn.abtion.taskgo.mvp.contract.LoginContract;
import cn.abtion.taskgo.mvp.presenter.LoginPresenter;
import cn.abtion.taskgo.mvp.view.MainActivity;

public class LoginActivity extends BaseNoBarPresenterActivity<LoginContract.Presenter> implements LoginContract.View {


    @BindView(R.id.logo_login)
    ImageView logoLogin;
    @BindView(R.id.ly_header)
    LinearLayout lyHeader;
    @BindView(R.id.edit_phone_number)
    EditText editPhoneNumber;
    @BindView(R.id.ly_phone_number)
    LinearLayout lyPhoneNumber;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.ly_password)
    LinearLayout lyPassword;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_forget_password)
    TextView txtForgetPassword;
    @BindView(R.id.txt_register)
    TextView txtRegister;
    @BindView(R.id.footer_line1)
    View footerLine1;
    @BindView(R.id.footer_line2)
    View footerLine2;

    /**
     * 登陆成功后,进入主页面,结束当前页面
     */
    @Override
    public void onLoginSuccess() {

//        ToastUtil.showToast("登录成功");
//        MainActivity.startActivity(this);
//        this.finish();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
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


    @OnClick(R.id.txt_forget_password)
    public void onTxtForgetPasswordClicked() {

        UpdatePasswordActivity.startActivity(LoginActivity.this);

    }

    @OnClick(R.id.txt_register)
    public void onTxtRegisterClicked() {
        RegisterActivity.startActivity(LoginActivity.this);
    }
}
