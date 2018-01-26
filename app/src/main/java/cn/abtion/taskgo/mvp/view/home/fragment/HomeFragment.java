package cn.abtion.taskgo.mvp.view.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.widget.BannerFlipper;

import static android.view.MotionEvent.ACTION_UP;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 下午1:28
 * fhyPayaso@qq.com
 */
public class HomeFragment extends BasePresenterFragment {


    @BindView(R.id.home_banner_flipper)
    ViewFlipper homeBannerFlipper;
    Unbinder unbinder;

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariable() {


    }

    @Override
    protected void initView() {

//        ImageView imageView1 = new ImageView(getContext());
//        imageView1.setImageResource(R.drawable.flipper1);
//        ImageView imageView2 = new ImageView(getContext());
//        imageView2.setImageResource(R.drawable.filpper2);
//        ImageView imageView3 = new ImageView(getContext());
//        imageView3.setImageResource(R.drawable.filpper3);
//        ImageView imageView4 = new ImageView(getContext());
//        imageView4.setImageResource(R.drawable.filpper4);
//
//
//        homeBannerFlipper = (BannerFlipper) getRootView().findViewById(R.id.home_banner_flipper);
//
//        homeBannerFlipper.addView(imageView1);
//        homeBannerFlipper.addView(imageView2);
//        homeBannerFlipper.addView(imageView3);
//        homeBannerFlipper.addView(imageView4);
//        //homeBannerFlipper = new BannerFlipper(getContext());

    }

    @Override
    protected void loadData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
