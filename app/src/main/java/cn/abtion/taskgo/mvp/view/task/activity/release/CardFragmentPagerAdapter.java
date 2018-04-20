package cn.abtion.taskgo.mvp.view.task.activity.release;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.abtion.taskgo.mvp.model.task.model.ChooseCardModel;
import cn.abtion.taskgo.mvp.view.task.fragment.home.CardFragment;

/**
 * @author FanHongyu.
 * @since 18/4/20 21:13.
 * email fanhongyu@hrsoft.net.
 */

public class CardFragmentPagerAdapter extends FragmentPagerAdapter {


    private CardFragment mFragment1, mFragment2, mFragment3;
    private Fragment mcurrentFragment;


    private List<ChooseCardModel> mCardModelList;


    public CardFragmentPagerAdapter(FragmentManager fm, List<ChooseCardModel> list) {
        super(fm);
        this.mCardModelList = list;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (mFragment1 == null) {
                    mFragment1 = new CardFragment();
                    mFragment1.setChooseCardModel(mCardModelList.get(0));
                }
                mcurrentFragment = mFragment1;
                break;
            case 1:
                if (mFragment2 == null) {
                    mFragment2 = new CardFragment();
                    mFragment2.setChooseCardModel(mCardModelList.get(1));
                }
                mcurrentFragment = mFragment2;
                break;

            case 2:
                if (mFragment3 == null) {
                    mFragment3 = new CardFragment();
                    mFragment3.setChooseCardModel(mCardModelList.get(2));
                }
                mcurrentFragment = mFragment3;
                break;
            default:

                break;
        }

        return mcurrentFragment;
    }

    @Override
    public int getCount() {
        return mCardModelList.size();
    }


}
