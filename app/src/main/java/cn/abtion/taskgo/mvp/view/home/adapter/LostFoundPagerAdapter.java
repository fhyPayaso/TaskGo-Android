package cn.abtion.taskgo.mvp.view.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.abtion.taskgo.mvp.view.home.fragment.FoundTaskListFragment;
import cn.abtion.taskgo.mvp.view.home.fragment.LostTaskListFragment;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 下午11:49
 * fhyPayaso@qq.com
 */
public class LostFoundPagerAdapter extends FragmentPagerAdapter {


    private String[] mTitles = {"失物招领", "寻物启事"};
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
                if (mFoundTaskListFragment == null) {
                    mFoundTaskListFragment = new FoundTaskListFragment();
                }
                currentFragment = mFoundTaskListFragment;
                break;
            case 1:
                if (mLostTaskListFragment == null) {
                    mLostTaskListFragment = new LostTaskListFragment();
                }
                currentFragment = mLostTaskListFragment;
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
