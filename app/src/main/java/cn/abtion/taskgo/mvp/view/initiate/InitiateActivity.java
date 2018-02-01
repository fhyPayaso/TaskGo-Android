package cn.abtion.taskgo.mvp.view.initiate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarActivity;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.view.account.LoginActivity;

/**
 * Created by heaijia on 2018/1/28.
 */

public class InitiateActivity extends BaseNoBarActivity {

    ViewPager currentViewPager;

    @BindView(R.id.btn_initiate)
    Button btnInitiate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_initiate_second;
    }

    @Override
    protected void initVariable() {

        InitiateViewPageAdapter initiateViewPageAdapter = new InitiateViewPageAdapter(getSupportFragmentManager());
        currentViewPager.setCurrentItem(Config.FLAG_INITIATE_FIRST);
        currentViewPager.setOffscreenPageLimit(Config.PAGE_SUMS);
        currentViewPager.setAdapter(initiateViewPageAdapter);

        currentViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void initView() {

//        currentViewPager.setCurrentItem(Config.FLAG_INITIATE_FIRST, false);

        Intent intent=getIntent();
        currentViewPager.setCurrentItem(Config.FLAG_INITIATE_FIRST);


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

    @OnClick(R.id.btn_initiate)
    public void onViewClicked() {
        LoginActivity.startActivity(InitiateActivity.this);

    }
}
