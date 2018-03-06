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
import cn.abtion.taskgo.base.activity.BaseNoBarActivity;
import cn.abtion.taskgo.utils.ToastUtil;
import cn.abtion.taskgo.widget.VerificationCountDownTimer;

/**
 * @author heaijia
 * @since 2018/2/2 上午11:33
 * email 549044363@qq.com
 */

public class UpdatePasswordActivity extends BaseNoBarActivity {

    VerificationCountDownTimer vCountDownTimer;

    @BindView(R.id.btn_back_forget)
    ImageView mbtnBackForget;
    @BindView(R.id.ly_header_forget)
    LinearLayout mlyHeaderForget;
    @BindView(R.id.edit_user_number_forget)
    EditText meditUserNumberForget;
    @BindView(R.id.ly_user_number_forget)
    LinearLayout mlyUserNumberForget;
    @BindView(R.id.line3)
    View mline3;
    @BindView(R.id.img_verification_forget)
    ImageView mimgVerificationForget;
    @BindView(R.id.edit_verification_code_forget)
    EditText meditVerificationCodeForget;
    @BindView(R.id.btn_verification)
    Button mbtnVerification;
    @BindView(R.id.ly_verification_code_forget)
    RelativeLayout mlyVerificationCodeForget;
    @BindView(R.id.line4)
    View mline4;
    @BindView(R.id.edit_secret_forget)
    EditText meditSecretForget;
    @BindView(R.id.ly_secret_forget)
    LinearLayout mlySecretForget;
    @BindView(R.id.line5)
    View mline5;
    @BindView(R.id.edit_secret_again_forget)
    EditText meditSecretAgainForget;
    @BindView(R.id.ly_secret_again_forget)
    LinearLayout mlySecretAgainForget;
    @BindView(R.id.line6)
    View mline6;
    @BindView(R.id.rl_context_forget)
    RelativeLayout mrlContextForget;
    @BindView(R.id.btn_certain)
    Button mbtnCertain;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, UpdatePasswordActivity.class));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.ly_header_forget)
    public void onLyHeaderForgetClicked() {
        LoginActivity.startActivity(UpdatePasswordActivity.this);

    }



    @OnClick(R.id.btn_certain)
    public void onViewClicked() {

        ToastUtil.showToast("敬请期待");
        LoginActivity.startActivity(UpdatePasswordActivity.this);
    }



    @OnClick(R.id.btn_verification)
    public void onBtnVerificationClicked() {

        ToastUtil.showToast("你即将获取验证码，请注意查收");
        vCountDownTimer.timerStart(true);
    }

    /**
     * 倒计时生物具体方法
     */
    public void initCountDownTimer() {

        if(!VerificationCountDownTimer.FLAG_FIRST_IN&&
                VerificationCountDownTimer.mcurMillis+60000>System.currentTimeMillis()) {

            setCountDownTimer(VerificationCountDownTimer.mcurMillis+60000-System.currentTimeMillis());
            vCountDownTimer.timerStart(false);

        } else {

            setCountDownTimer(60000);
        }
    }

    public void setCountDownTimer(final long countDownTime) {

        vCountDownTimer = new VerificationCountDownTimer( countDownTime , 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mbtnVerification.setEnabled(false);
                mbtnVerification.setText((millisUntilFinished / 1000) + " s");
            }

            @Override
            public void onFinish() {

                mbtnVerification.setEnabled(true);
                mbtnVerification.setText(getString(R.string.btn_verification_gain));

                if(countDownTime!=60000) {
                    setCountDownTimer(60000);
                }
            }

        };

    }


}
