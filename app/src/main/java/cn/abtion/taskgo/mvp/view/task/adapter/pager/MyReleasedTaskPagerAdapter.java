package cn.abtion.taskgo.mvp.view.task.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.abtion.taskgo.mvp.view.task.fragment.myreleased.MyReleasedFinishedFragment;
import cn.abtion.taskgo.mvp.view.task.fragment.myreleased.MyReleasedHasAcceptFragment;
import cn.abtion.taskgo.mvp.view.task.fragment.myreleased.MyReleasedHasNotAcceptFragment;

/**
 * @author fhyPayaso
 * @since 2018/2/3 on 上午9:34
 * fhyPayaso@qq.com
 */
public class MyReleasedTaskPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = {"未接受", "被接受", "已完成"};
    private MyReleasedHasNotAcceptFragment mNotAcceptFragment;
    private MyReleasedHasAcceptFragment mAcceptFragment;
    private MyReleasedFinishedFragment mFinishedFragment;
    private Fragment currentFragment;


    public MyReleasedTaskPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                if (mNotAcceptFragment == null) {
                    mNotAcceptFragment = new MyReleasedHasNotAcceptFragment();
                }
                currentFragment = mNotAcceptFragment;
                break;
            case 1:
                if (mAcceptFragment == null) {
                    mAcceptFragment = new MyReleasedHasAcceptFragment();
                }
                currentFragment = mAcceptFragment;
                break;
            case 2:
                if (mFinishedFragment == null) {
                    mFinishedFragment = new MyReleasedFinishedFragment();
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
        return 3;
    }
}
