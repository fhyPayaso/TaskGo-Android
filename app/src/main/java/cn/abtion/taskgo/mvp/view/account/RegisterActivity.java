package cn.abtion.taskgo.mvp.view.account;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarPresenterActivity;
import cn.abtion.taskgo.common.constants.CacheKey;
import cn.abtion.taskgo.mvp.contract.account.RegisterContract;
import cn.abtion.taskgo.mvp.model.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.model.account.RegisterRequestModel;
import cn.abtion.taskgo.mvp.presenter.account.RegisterPresenter;
import cn.abtion.taskgo.mvp.view.MainActivity;
import cn.abtion.taskgo.utils.CacheUtil;
import cn.abtion.taskgo.utils.ToastUtil;
import cn.abtion.taskgo.widget.VerificationCountDownTimer;

public class RegisterActivity extends BaseNoBarPresenterActivity<RegisterContract.Presenter> implements
        RegisterContract.View {

    private boolean flagAgreement = false;

    VerificationCountDownTimer mverificationCountDownTimer;


    @BindView(R.id.edit_user_number)
    EditText meditUserNumber;
    @BindView(R.id.edit_verification_code)
    EditText meditVerificationCode;
    @BindView(R.id.btn_verification_register)
    Button mbtnVerificationRegister;
    @BindView(R.id.edit_secret)
    EditText meditSecret;
    @BindView(R.id.edit_secret_again)
    EditText meditSecretAgain;
    @BindView(R.id.img_agreement_selector)
    ImageView imgAgreementSelector;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initVariable() {

        initCountDownTimer();
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

    @Override
    protected RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }


    @OnClick(R.id.img_agreement_selector)
    public void onViewClicked() {

        if (flagAgreement) {
            flagAgreement = false;
            imgAgreementSelector.setSelected(true);
        } else {
            imgAgreementSelector.setSelected(false);
            flagAgreement = true;
        }
    }


    /**
     * 忘记密码的返回按钮，从忘记密码界面，回到登录界面
     */
    @OnClick(R.id.ly_header_register)
    public void onLyHeaderRegisterClicked() {
        this.finish();
    }


    /**
     * 用户协议 暂未开启
     */
    @OnClick(R.id.txt_servise)
    public void onBtnTxtClicked() {
        ToastUtil.showToast("暂未开启");
    }


    /**
     * 倒计时具体方法
     */
    public void initCountDownTimer() {

        if (!VerificationCountDownTimer.FLAG_FIRST_IN &&
                VerificationCountDownTimer.mcurMillis + 60000 > System.currentTimeMillis()) {

            setCountDownTimer(VerificationCountDownTimer.mcurMillis + 60000 - System.currentTimeMillis());
            mverificationCountDownTimer.timerStart(false);

        } else {

            setCountDownTimer(60000);
        }
    }


    public void setCountDownTimer(final long countDownTime) {

        mverificationCountDownTimer = new VerificationCountDownTimer(countDownTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mbtnVerificationRegister.setEnabled(false);
                mbtnVerificationRegister.setText((millisUntilFinished / 1000) + " s");
            }

            @Override
            public void onFinish() {

                mbtnVerificationRegister.setEnabled(true);
                mbtnVerificationRegister.setText(getString(R.string.btn_verification_gain));

                if (countDownTime != 60000) {
                    setCountDownTimer(60000);
                }
            }
        };
    }





    /**
     * 绑定 获得验证码 按钮，启动timerStart
     */
    @OnClick(R.id.btn_verification_register)
    public void onBtnVerificationRegisterClicked() {

        //点击 发送验证码 按钮，P层进行数据的传输
        mPresenter.sendCaptcha(meditUserNumber.getText().toString().trim());
        ToastUtil.showToast("你即将获得验证码，请注意查收");
        mverificationCountDownTimer.timerStart(true);
    }


    /**
     * 点击 注册 按钮,P层进行数据的传输
     */
    @OnClick(R.id.btn_register)
    public void onBtnRegisterClicked() {

        mPresenter.register(new RegisterRequestModel(meditUserNumber.getText().toString().trim(), meditSecret.getText
                ().toString().trim(), "mobile", meditVerificationCode.getText().toString().trim()), meditSecretAgain
                .getText().toString().trim());


    }


    /**
     * 注册成功回调，直接调用登录接口
     */
    @Override
    public void onRegisterSuccess() {

        mPresenter.loginAgain(new LoginRequestModel(meditUserNumber.getText().toString().trim(), meditSecret.getText
                ().toString().trim()));
    }


    /**
     * 登录成功回调，缓存token
     * @param token
     */
    @Override
    public void onLoginAgainSuccess(String token) {

        Log.i("register", "onLoginAgainSuccess: " + token);

        CacheUtil.putString(CacheKey.TOKEN, token);
        MainActivity.startActivity(RegisterActivity.this);
        finish();
        mverificationCountDownTimer.cancel();
    }


    @Override
    public void onCaptchaSuccess() {
        ToastUtil.showToast("已经发送验证码，请注意查收");
    }
}
