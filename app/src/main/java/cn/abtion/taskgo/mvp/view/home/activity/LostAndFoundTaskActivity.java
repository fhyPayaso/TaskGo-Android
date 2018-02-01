package cn.abtion.taskgo.mvp.view.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarActivity;
import cn.abtion.taskgo.mvp.view.home.adapter.LostFoundPagerAdapter;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 下午11:30
 * fhyPayaso@qq.com
 */
public class LostAndFoundTaskActivity extends BaseToolBarActivity {


    @BindView(R.id.tl_lost_and_found)
    TabLayout mTabLayout;
    @BindView(R.id.vp_lost_and_found)
    ViewPager mViewPager;
    LostFoundPagerAdapter mPagerAdapter;
    @BindView(R.id.btn_release_task)
    FloatingActionButton btnReleaseTask;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lost_and_find;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        initToolBar();
        initViewPager();
    }

    @Override
    protected void loadData() {

    }


    private void initToolBar() {
        setActivityTitle("物品任务");
        setToolBarMenu(R.drawable.ic_search);
        getToolBar().findViewById(R.id.img_toolbar_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchTaskActivity.startActivity(LostAndFoundTaskActivity.this);
            }
        });
    }

    private void initViewPager() {
        mPagerAdapter = new LostFoundPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LostAndFoundTaskActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_release_task)
    public void onViewClicked() {
        ReleaseLostFoundTaskActivity.startActivity(this);
    }
}
