package cn.abtion.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.mvp.contract.mine.ChangePasswordContract;
import cn.abtion.taskgo.mvp.presenter.mine.ChangePasswordPresenter;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author：lszr on 2018/3/6 21:08
 * @email：1085963811@qq.com
 */
public class ChangePasswordActivity extends BaseToolBarPresenterActivity<ChangePasswordContract.Presenter> implements ChangePasswordContract.View {
    @BindView(R.id.edit_old_psaaword)
    EditText editOldPsaaword;
    @BindView(R.id.edit_new_password)
    EditText editNewPassword;
    @BindView(R.id.edit_repeat_password)
    EditText editRepeatPassword;

    @Override
    public void setPresenter(ChangePasswordContract.Presenter presenter) {
    }

    @Override
    public void onChangePasswordSuccess() {
        ToastUtil.showToast(getString(R.string.txt_mine_change_password_success));
        finish();
    }

    @Override
    public void onFormatError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public ChangePasswordContract.Presenter initPresenter() {
        return new ChangePasswordPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initVariable() {
    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_update_password_title));
    }

    @Override
    protected void loadData() {

    }

    public static void startChangePasswordActivity(Context context) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }


    @OnClick(R.id.btn_submmit_change)
    public void onBtnSubmmitClicked() {
        mPresenter.requestChangePassword(editOldPsaaword.getText().toString(),editNewPassword.getText().toString(),editRepeatPassword.getText().toString());
    }
}
