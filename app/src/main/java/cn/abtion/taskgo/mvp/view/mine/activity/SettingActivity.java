package cn.abtion.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarActivity;

/**
 * @author：lszr on 2018/1/30 21:13
 * @email：1085963811@qq.com
 */
public class SettingActivity extends BaseToolBarActivity {
    @BindView(R.id.switch_mine_remind_push)
    Switch mSwitchMineRemindPush;
    @BindView(R.id.switch_mine_remind_voice)
    Switch mSwitchMineRemindVoice;
    @BindView(R.id.switch_mine_remind_shock)
    Switch mSwitchMineRemindShock;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setSwitchListener();
        setActivityTitle(getString(R.string.txt_mine_setting));

    }

    @Override
    protected void loadData() {

    }




    public void setSwitchListener() {
        mSwitchMineRemindPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        mSwitchMineRemindShock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        mSwitchMineRemindVoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    public static void startSettingActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }


    @OnClick({R.id.rlayout_mine_setting_change_password, R.id.rlayout_mine_setting_about, R.id.rlayout_mine_exchange_account, R.id.rlayout_mine_exit_systom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlayout_mine_setting_change_password:
                ChangePasswordActivity.startChangePasswordActivity(SettingActivity.this);
                break;
            case R.id.rlayout_mine_setting_about:
                AboutAvtivity.startAboutActivity(SettingActivity.this);
                break;
            case R.id.rlayout_mine_exchange_account:
                break;
            case R.id.rlayout_mine_exit_systom:
                break;
            default:
                break;
        }
    }
}
