package cn.abtion.taskgo.initiate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarActivity;
import cn.abtion.taskgo.mvp.view.account.LoginActivity;
import cn.abtion.taskgo.mvp.view.account.RegisterActivity;

/**
 * Created by heaijia on 2018/1/27.
 */

public class SecondInitiateActivity extends BaseNoBarActivity {


    @BindView(R.id.onclick_initiate_second)
    ImageView onclickInitiateSecond;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_initiate_second;
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

    @OnClick(R.id.onclick_initiate_second)
    public void onViewClicked() {
        LoginActivity.startActivity(SecondInitiateActivity.this);
    }
}
