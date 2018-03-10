package cn.abtion.taskgo.mvp.view.task.activity.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarActivity;
import cn.abtion.taskgo.mvp.view.task.adapter.pager.MyReleasedTaskPagerAdapter;

/**
 * @author fhyPayaso
 * @since 2018/2/1 on 下午7:42
 * fhyPayaso@qq.com
 */
public class MyReleasedTaskActivity extends BaseToolBarActivity {

    @BindView(R.id.tl_my_released_task)
    TabLayout mTabLayout;
    @BindView(R.id.vp_my_released_task)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_released_task;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        setActivityTitle(getString(R.string.title_my_released_task));
        initViewPager();
    }

    @Override
    protected void loadData() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyReleasedTaskActivity.class));
    }

    private void initViewPager() {

        MyReleasedTaskPagerAdapter mPagerAdapter = new MyReleasedTaskPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
