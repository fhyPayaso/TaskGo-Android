package cn.abtion.taskgo.mvp.view.task.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.abtion.taskgo.mvp.view.task.fragment.home.FoundTaskListFragment;
import cn.abtion.taskgo.mvp.view.task.fragment.home.LostTaskListFragment;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 下午11:49
 * fhyPayaso@qq.com
 */
public class LostFoundPagerAdapter extends FragmentPagerAdapter {


    private String[] mTitles = {"寻物启事", "失物招领"};
    private FoundTaskListFragment mFoundTaskListFragment;
    private LostTaskListFragment mLostTaskListFragment;
    private Fragment currentFragment;

    public LostFoundPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                if (mLostTaskListFragment == null) {
                    mLostTaskListFragment = new LostTaskListFragment();
                }
                currentFragment = mLostTaskListFragment;
                break;

            case 1:

                if (mFoundTaskListFragment == null) {
                    mFoundTaskListFragment = new FoundTaskListFragment();
                }
                currentFragment = mFoundTaskListFragment;
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
