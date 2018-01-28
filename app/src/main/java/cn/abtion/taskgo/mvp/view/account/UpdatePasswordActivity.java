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

/**
 * Created by heaijia on 2018/1/26.
 */

public class UpdatePasswordActivity extends BaseNoBarActivity {


    @BindView(R.id.btn_back_forget)
    ImageView btnBackForget;
    @BindView(R.id.ly_header_forget)
    LinearLayout lyHeaderForget;
    @BindView(R.id.edit_user_number_forget)
    EditText editUserNumberForget;
    @BindView(R.id.ly_user_number_forget)
    LinearLayout lyUserNumberForget;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.img_verification_forget)
    ImageView imgVerificationForget;
    @BindView(R.id.edit_verification_code_forget)
    EditText editVerificationCodeForget;
    @BindView(R.id.btn_verification)
    Button btnVerification;
    @BindView(R.id.ly_verification_code_forget)
    RelativeLayout lyVerificationCodeForget;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.edit_secret_forget)
    EditText editSecretForget;
    @BindView(R.id.ly_secret_forget)
    LinearLayout lySecretForget;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.edit_secret_again_forget)
    EditText editSecretAgainForget;
    @BindView(R.id.ly_secret_again_forget)
    LinearLayout lySecretAgainForget;
    @BindView(R.id.line6)
    View line6;
    @BindView(R.id.rl_context_forget)
    RelativeLayout rlContextForget;
    @BindView(R.id.btn_certain)
    Button btnCertain;


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

    @OnClick(R.id.btn_verification)
    public void onBtnVerificationClicked() {

        ToastUtil.showToast("你即将获取验证码，请注意查收");
    }

    @OnClick(R.id.btn_certain)
    public void onViewClicked() {

        ToastUtil.showToast("敬请期待");
        LoginActivity.startActivity(UpdatePasswordActivity.this);
    }
}
