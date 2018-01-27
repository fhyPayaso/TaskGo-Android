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
import cn.abtion.taskgo.mvp.view.account.RegisterActivity;

/**
 * Created by heaijia on 2018/1/27.
 */

public class FirstInitiateActivity extends BaseNoBarActivity {

    @BindView(R.id.onclick_initiate_first)
    ImageView onclickInitiateFirst;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_initiate_first;
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

    @OnClick(R.id.onclick_initiate_first)
    public void onViewClicked() {

        SecondInitiateActivity.startActivity(FirstInitiateActivity.this);
    }
}
