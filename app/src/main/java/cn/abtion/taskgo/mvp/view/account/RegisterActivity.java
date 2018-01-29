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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.utils.ToastUtil;

public class RegisterActivity extends BaseNoBarPresenterActivity {


    VerificationCountDownTimer verificationCountDownTimer;

    @BindView(R.id.btn_back_register)
    ImageView btnBackRegister;
    @BindView(R.id.ly_header_register)
    LinearLayout lyHeaderRegister;
    @BindView(R.id.edit_user_number)
    EditText editUserNumber;
    @BindView(R.id.ly_user_number)
    LinearLayout lyUserNumber;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.img_verification)
    ImageView imgVerification;
    @BindView(R.id.edit_verification_code)
    EditText editVerificationCode;
    @BindView(R.id.btn_verification_register)
    Button btnVerificationRegister;
    @BindView(R.id.ly_verification_code)
    RelativeLayout lyVerificationCode;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.edit_secret)
    EditText editSecret;
    @BindView(R.id.ly_secret)
    LinearLayout lySecret;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.edit_secret_again)
    EditText editSecretAgain;
    @BindView(R.id.ly_secret_again)
    LinearLayout lySecretAgain;
    @BindView(R.id.line6)
    View line6;
    @BindView(R.id.txt_taskgo_servise)
    TextView txtTaskgoServise;
    @BindView(R.id.line7)
    View line7;
    @BindView(R.id.txt_servise)
    LinearLayout txtServise;
    @BindView(R.id.ly_agreement)
    LinearLayout lyAgreement;
    @BindView(R.id.rl_context)
    RelativeLayout rlContext;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @BindView(R.id.img_agreement_selector)
    ImageView imgAgreementSelector;

    @Override
    protected BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

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


    @OnClick(R.id.btn_register)
    public void onBtnRegisterClicked() {

        ToastUtil.showToast("还没有网络请求，敬请期待");
        LoginActivity.startActivity(RegisterActivity.this);
        verificationCountDownTimer.cancel();

    }


    @OnClick(R.id.img_agreement_selector)
    public void onViewClicked() {

        imgAgreementSelector.setSelected(true);

    }




    @OnClick(R.id.ly_header_register)
    public void onLyHeaderRegisterClicked() {
        LoginActivity.startActivity(RegisterActivity.this);
    }

    /**
     * 绑定 获得验证码 按钮，启动timerStart
     */
    @OnClick(R.id.btn_verification_register)
    public void onBtnVerificationRegisterClicked() {
        ToastUtil.showToast("你即将获得验证码，请注意查收");
        verificationCountDownTimer.timerStart(true);
    }

    /**
     * 倒计时具体方法
     */
    public void initCountDownTimer() {

        if(!VerificationCountDownTimer.FLAG_FIRST_IN&&
                VerificationCountDownTimer.curMillis+60000>System.currentTimeMillis()) {

            setCountDownTimer(VerificationCountDownTimer.curMillis+60000-System.currentTimeMillis());
            verificationCountDownTimer.timerStart(false);

        } else {

            setCountDownTimer(60000);
        }
    }

    public void setCountDownTimer(final long countDownTime) {

        verificationCountDownTimer = new VerificationCountDownTimer( countDownTime , 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                btnVerificationRegister.setEnabled(false);
                btnVerificationRegister.setText((millisUntilFinished / 1000) + " s");
            }

            @Override
            public void onFinish() {

                btnVerificationRegister.setEnabled(true);
                btnVerificationRegister.setText(getString(R.string.btn_verification_gain));

                if(countDownTime!=60000) {
                    setCountDownTimer(60000);
                }
            }

        };

    }






}
