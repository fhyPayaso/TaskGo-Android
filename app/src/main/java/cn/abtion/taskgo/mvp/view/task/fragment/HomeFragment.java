package cn.abtion.taskgo.mvp.view.task.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.mvp.view.task.activity.home.LostAndFoundTaskActivity;
import cn.abtion.taskgo.mvp.view.task.activity.home.WaterTaskListActivity;
import cn.abtion.taskgo.utils.ToastUtil;
import cn.abtion.taskgo.widget.BannerPager;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 下午1:28
 * fhyPayaso@qq.com
 */
public class HomeFragment extends BasePresenterFragment implements BannerPager.OnBannerClick {

    @BindView(R.id.txt_diy_task)
    TextView mTxtDiyTask;
    @BindView(R.id.txt_water_task)
    TextView mTxtWaterTask;
    @BindView(R.id.txt_lost_task)
    TextView mTxtLostTask;
    @BindView(R.id.txt_more_task)
    TextView mTxtMoreTask;
    @BindView(R.id.flipper_header)
    BannerPager mFlipper;
    Unbinder unbinder;

    public final static int IMG_ALPHA = 140;

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

        initBanner();
        initAlpha();
    }

    @Override
    protected void loadData() {

    }

    /**
     * 初始化轮播图
     */
    private void initBanner() {

        mFlipper.addView(getImageView(R.drawable.home_banner1));
        mFlipper.addView(getImageView(R.drawable.home_banner2));
        mFlipper.addView(getImageView(R.drawable.home_banner3));
        mFlipper.addView(getImageView(R.drawable.home_banner4));
        mFlipper.setOnBannerClick(this);
        mFlipper.startFlipping();
    }

    /**
     * 获取资源图片
     *
     * @param res
     * @return
     */
    private ImageView getImageView(int res) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(res);
        return imageView;
    }

    /**
     * 初始化透明度
     */
    private void initAlpha() {

        mTxtDiyTask.getBackground().setAlpha(IMG_ALPHA);
        mTxtLostTask.getBackground().setAlpha(IMG_ALPHA);
        mTxtMoreTask.getBackground().setAlpha(IMG_ALPHA);
        mTxtWaterTask.getBackground().setAlpha(IMG_ALPHA);
    }


    @OnClick(R.id.txt_diy_task)
    public void onMTxtDiyTaskClicked() {
        ToastUtil.showToast(R.string.toast_stay_tuned);
    }

    @OnClick(R.id.txt_water_task)
    public void onMTxtWaterTaskClicked() {
        WaterTaskListActivity.startActivity(getContext());
    }

    @OnClick(R.id.txt_lost_task)
    public void onMTxtLostTaskClicked() {
        LostAndFoundTaskActivity.startActivity(getContext());
    }

    @OnClick(R.id.txt_more_task)
    public void onMTxtMoreTaskClicked() {
        ToastUtil.showToast(R.string.toast_stay_tuned);
    }

    @Override
    public void onBannerClick(int position) {
        ToastUtil.showToast("点击了轮播图");
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
