package cn.abtion.taskgo.mvp.view.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarPresenterActivity;
import cn.abtion.taskgo.mvp.contract.account.ForgetPasswordContract;
import cn.abtion.taskgo.mvp.model.account.ForgotPasswordModel;
import cn.abtion.taskgo.mvp.presenter.account.ForgetPasswordPresent;
import cn.abtion.taskgo.utils.ToastUtil;
import cn.abtion.taskgo.widget.VerificationCountDownTimer;

/**
 * @author heaijia
 * @since 2018/3/9 上午9:39
 * email 549044363@qq.com
 */

public class ForgetPasswordActivity extends BaseNoBarPresenterActivity<ForgetPasswordContract.Presenter> implements ForgetPasswordContract.View {


    VerificationCountDownTimer vCountDownTimer;
    @BindView(R.id.btn_back_forget)
    ImageView mbtnBackForget;
    @BindView(R.id.ly_header_forget)
    LinearLayout mlyHeaderForget;
    @BindView(R.id.ly_user_number_forget)
    LinearLayout mlyUserNumberForget;
    @BindView(R.id.img_verification_forget)
    ImageView mimgVerificationForget;
    @BindView(R.id.edit_verification_code_forget)
    EditText meditVerificationCodeForget;
    @BindView(R.id.btn_verification)
    Button mbtnVerification;
    @BindView(R.id.ly_verification_code_forget)
    RelativeLayout mlyVerificationCodeForget;
    @BindView(R.id.edit_secret_forget)
    EditText meditSecretForget;
    @BindView(R.id.ly_secret_forget)
    LinearLayout mlySecretForget;
    @BindView(R.id.edit_secret_again_forget)
    EditText meditSecretAgainForget;
    @BindView(R.id.ly_secret_again_forget)
    LinearLayout mlySecretAgainForget;
    @BindView(R.id.rl_context_forget)
    RelativeLayout mrlContextForget;
    @BindView(R.id.btn_certain)
    Button mbtnCertain;
    @BindView(R.id.edit_user_number_forget)
    EditText editUserNumberForget;


    public static void startActivity(Context context) {

//        Intent intent=new Intent(context,ForgetPasswordActivity.class);
//        context.startActivity(intent);

        context.startActivity(new Intent(context, ForgetPasswordActivity.class));
    }


    @Override
    public void onSendInformationSuccess() {
        ToastUtil.showToast("密码已找回，请重新登录");
        LoginActivity.startActivity(ForgetPasswordActivity.this);

        finish();
    }

    @Override
    protected ForgetPasswordContract.Presenter initPresenter() {
        return new ForgetPasswordPresent(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_password;
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

    @OnClick(R.id.ly_header_forget)
    public void onLyHeaderForgetClicked() {
        LoginActivity.startActivity(ForgetPasswordActivity.this);
    }


    /**
     * 倒计时生物具体方法
     */
    public void initCountDownTimer() {

        if (!VerificationCountDownTimer.FLAG_FIRST_IN &&
                VerificationCountDownTimer.mcurMillis + 60000 > System.currentTimeMillis()) {

            setCountDownTimer(VerificationCountDownTimer.mcurMillis + 60000 - System.currentTimeMillis());
            vCountDownTimer.timerStart(false);

        } else {

            setCountDownTimer(60000);
        }
    }

    public void setCountDownTimer(final long countDownTime) {

        vCountDownTimer = new VerificationCountDownTimer(countDownTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mbtnVerification.setEnabled(false);
                mbtnVerification.setText((millisUntilFinished / 1000) + " s");
            }

            @Override
            public void onFinish() {

                mbtnVerification.setEnabled(true);
                mbtnVerification.setText(getString(R.string.btn_verification_gain));

                if (countDownTime != 60000) {
                    setCountDownTimer(60000);
                }
            }

        };

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_verification)
    public void onViewClicked() {
        /**
         * 点击 发送验证码 按钮，P层进行数据的传输
         */
        mPresenter.sendCaptcha(editUserNumberForget.getText().toString().trim());

        ToastUtil.showToast("你即将获取验证码，请注意查收");
        vCountDownTimer.timerStart(true);

    }


    @OnClick(R.id.btn_certain)
    public void onClicked() {

        mPresenter.sendRequestNewInformation(new ForgotPasswordModel(editUserNumberForget.getText().toString().trim(),meditVerificationCodeForget.getText().toString().trim(),meditSecretForget.getText().toString().trim(),meditSecretAgainForget.getText().toString().trim()));
    }
}
