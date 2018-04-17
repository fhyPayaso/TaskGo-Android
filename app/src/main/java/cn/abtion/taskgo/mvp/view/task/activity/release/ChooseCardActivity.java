package cn.abtion.taskgo.mvp.view.task.activity.release;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.widget.VerticalPager.CommonFragment;
import cn.abtion.taskgo.widget.VerticalPager.CustPagerTransformer;

/**
 * @author FanHongyu.
 * @since 18/4/5 11:21.
 * email fanhongyu@hrsoft.net.
 */

public class ChooseCardActivity extends BaseToolBarPresenterActivity {


    @BindView(R.id.viewpager)
    ViewPager viewPager;


    private View positionView;
    private List<CommonFragment> fragments = new ArrayList<>();
    private final String[] imageArray = {"http://img3.imgtn.bdimg.com/it/u=2447064320,457706827&fm=27&gp=0.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523513761&di" +
            "=2afa2fb5b0449dc78cf63ec7ddc41d0c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang" +
            ".com%2Fuploads%2Fblog%2F201609%2F10%2F20160910182510_3CTxi.thumb.224_0.jpeg"
            , "http://img0.imgtn.bdimg.com/it/u=644974980,896370294&fm=27&gp=0.jpg"};


    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_card;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle("发布任务");
        fillViewPager();

    }

    @Override
    protected void loadData() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ChooseCardActivity.class));
    }

    /**
     * 填充ViewPager
     */
    private void fillViewPager() {

        // 1. viewPager添加parallax效果，使用PageTransformer就足够了
        viewPager.setPageTransformer(false, new CustPagerTransformer(this));
        // 2. viewPager添加adapter
        for (int i = 0; i < 3; i++) {
            fragments.add(new CommonFragment());
        }
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                CommonFragment fragment = fragments.get(position);
                fragment.bindData(imageArray[position]);
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        // 3. viewPager滑动时，调整指示器
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                updateIndicatorTv();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        updateIndicatorTv();
    }

    /**
     * 更新指示器
     */
    private void updateIndicatorTv() {
        int totalNum = viewPager.getAdapter().getCount();
        int currentItem = viewPager.getCurrentItem() + 1;
        //indicatorTv.setText(Html.fromHtml("<font color='#12edf0'>" + currentItem + "</font>  /  " + totalNum));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
