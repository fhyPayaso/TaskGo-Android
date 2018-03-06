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
import cn.abtion.taskgo.widget.VerificationCountDownTimer;

public class RegisterActivity extends BaseNoBarPresenterActivity {

    int tag =1;

    VerificationCountDownTimer mverificationCountDownTimer;

    @BindView(R.id.btn_back_register)
    ImageView mbtnBackRegister;
    @BindView(R.id.ly_header_register)
    LinearLayout mlyHeaderRegister;
    @BindView(R.id.edit_user_number)
    EditText meditUserNumber;
    @BindView(R.id.ly_user_number)
    LinearLayout mlyUserNumber;
    @BindView(R.id.line3)
    View mline3;
    @BindView(R.id.img_verification)
    ImageView mimgVerification;
    @BindView(R.id.edit_verification_code)
    EditText meditVerificationCode;
    @BindView(R.id.btn_verification_register)
    Button mbtnVerificationRegister;
    @BindView(R.id.ly_verification_code)
    RelativeLayout mlyVerificationCode;
    @BindView(R.id.line4)
    View mline4;
    @BindView(R.id.edit_secret)
    EditText meditSecret;
    @BindView(R.id.ly_secret)
    LinearLayout mlySecret;
    @BindView(R.id.line5)
    View mline5;
    @BindView(R.id.edit_secret_again)
    EditText meditSecretAgain;
    @BindView(R.id.ly_secret_again)
    LinearLayout mlySecretAgain;
    @BindView(R.id.line6)
    View mline6;
    @BindView(R.id.txt_taskgo_servise)
    TextView mtxtTaskgoServise;
    @BindView(R.id.line7)
    View mline7;
    @BindView(R.id.txt_servise)
    LinearLayout mtxtServise;
    @BindView(R.id.ly_agreement)
    LinearLayout mlyAgreement;
    @BindView(R.id.rl_context)
    RelativeLayout mrlContext;
    @BindView(R.id.btn_register)
    Button mbtnRegister;

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


//        BmobSMS.initialize(this, "d56e0fe7bec328b922ea78d75e0c568c");
    }


    @OnClick(R.id.btn_register)
    public void onBtnRegisterClicked() {

        ToastUtil.showToast("还没有网络请求，敬请期待");
        LoginActivity.startActivity(RegisterActivity.this);
        mverificationCountDownTimer.cancel();

    }


    @OnClick(R.id.img_agreement_selector)
    public void onViewClicked() {
        tag ++;

        if(tag%2==0)//说明遇到点击为已阅读
        {
            imgAgreementSelector.setSelected(true);
        }
        if (tag %2!=0)
        {
            imgAgreementSelector.setSelected(false);
        }


    }


    /**
     * 忘记密码的返回按钮，从忘记密码界面，回到登录界面
     */
    @OnClick(R.id.ly_header_register)
    public void onLyHeaderRegisterClicked() {
//        LoginActivity.startActivity(RegisterActivity.this);
        this.finish();
    }

    /**
     * 绑定 获得验证码 按钮，启动timerStart
     */
    @OnClick(R.id.btn_verification_register)
    public void onBtnVerificationRegisterClicked() {
        ToastUtil.showToast("你即将获得验证码，请注意查收");
        mverificationCountDownTimer.timerStart(true);

    }

    @OnClick(R.id.txt_servise)
    public void onBtnTxtClicked()
    {

    }


    /**
     * 倒计时具体方法
     */
    public void initCountDownTimer() {

        if(!VerificationCountDownTimer.FLAG_FIRST_IN&&
                VerificationCountDownTimer.mcurMillis+60000>System.currentTimeMillis()) {

            setCountDownTimer(VerificationCountDownTimer.mcurMillis+60000-System.currentTimeMillis());
            mverificationCountDownTimer.timerStart(false);

        } else {

            setCountDownTimer(60000);
        }
    }



    public void setCountDownTimer(final long countDownTime) {

        mverificationCountDownTimer = new VerificationCountDownTimer( countDownTime , 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mbtnVerificationRegister.setEnabled(false);
                mbtnVerificationRegister.setText((millisUntilFinished / 1000) + " s");
            }

            @Override
            public void onFinish() {

                mbtnVerificationRegister.setEnabled(true);
                mbtnVerificationRegister.setText(getString(R.string.btn_verification_gain));

                if(countDownTime!=60000) {
                    setCountDownTimer(60000);
                }
            }
        };
    }
}
