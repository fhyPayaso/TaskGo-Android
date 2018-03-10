package cn.abtion.taskgo.mvp.view.task.activity.my;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarActivity;
import cn.abtion.taskgo.mvp.view.task.adapter.pager.MyAcceptTaskPagerAdapter;

/**
 * @author fhyPayaso
 * @since 2018/2/1 on 下午7:42
 * fhyPayaso@qq.com
 */
public class MyAcceptTaskActivity extends BaseToolBarActivity {

    @BindView(R.id.tl_my_accept_task)
    TabLayout mTabLayout;
    @BindView(R.id.vp_my_accept_task)
    ViewPager mViewPager;



    @Override
    protected int getLayoutId() {

        return R.layout.activity_my_accept_task;
    }

    @Override
    protected void initVariable() {

        setActivityTitle(getString(R.string.title_my_accepted_task));
    }

    @Override
    protected void initView() {

        initViewPager();
    }

    @Override
    protected void loadData() {

    }

    private void initViewPager() {

        MyAcceptTaskPagerAdapter mPagerAdapter = new MyAcceptTaskPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyAcceptTaskActivity.class));
    }


}
