package cn.abtion.taskgo.mvp.view.initiate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.telecom.Call;

import cn.abtion.taskgo.common.Config;

/**
 * Created by heaijia on 2018/1/28.
 */

public class InitiateViewPageAdapter extends FragmentStatePagerAdapter
{

    private InitiateFirstFragment initiateFirstFragment;
    private InitiateSecondFragment initiateSecondFragment;
    private Fragment currentFragment;

    public InitiateViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case Config.FLAG_INITIATE_FIRST:
                if(initiateFirstFragment==null)
                {
                    initiateFirstFragment=new InitiateFirstFragment();
                }
                currentFragment = initiateFirstFragment;
                break;

            case Config.FLAG_INITIATE_SECOND:
                if (initiateSecondFragment==null)
                {
                    initiateSecondFragment=new InitiateSecondFragment();
                }
                currentFragment=initiateSecondFragment;
                break;
             default:
        }
        return currentFragment;
    }

    @Override
    public int getCount() {
        return Config.PAGE_SUMS;
    }
}
