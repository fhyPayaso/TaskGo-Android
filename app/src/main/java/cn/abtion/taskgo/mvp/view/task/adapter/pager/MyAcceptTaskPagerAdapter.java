package cn.abtion.taskgo.mvp.view.task.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.abtion.taskgo.mvp.view.task.fragment.myaccept.MyAcceptFinishedFragment;
import cn.abtion.taskgo.mvp.view.task.fragment.myaccept.MyAcceptUnfinishedFragment;

/**
 * @author fhyPayaso
 * @since 2018/2/3 on 上午9:34
 * fhyPayaso@qq.com
 */
public class MyAcceptTaskPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = {"未完成", "已完成"};
    private MyAcceptUnfinishedFragment mUnfinishedFragment;
    private MyAcceptFinishedFragment mFinishedFragment;
    private Fragment currentFragment;


    public MyAcceptTaskPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (mUnfinishedFragment == null) {
                    mUnfinishedFragment = new MyAcceptUnfinishedFragment();
                }
                currentFragment = mUnfinishedFragment;
                break;
            case 1:
                if (mFinishedFragment == null) {
                    mFinishedFragment = new MyAcceptFinishedFragment();
                }
                currentFragment = mFinishedFragment;
                break;
            default:
                break;
        }
        return currentFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
