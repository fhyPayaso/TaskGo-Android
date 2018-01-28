package cn.abtion.taskgo.mvp.view.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.mvp.view.home.activity.LostAndFoundTaskActivity;
import cn.abtion.taskgo.mvp.view.home.activity.WaterTaskListActivity;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 下午1:28
 * fhyPayaso@qq.com
 */
public class HomeFragment extends BasePresenterFragment {
    @BindView(R.id.flipper_header)
    ViewFlipper mFlipper;
    @BindView(R.id.txt_diy_task)
    TextView mTxtDiyTask;
    @BindView(R.id.txt_water_task)
    TextView mTxtWaterTask;
    @BindView(R.id.txt_lost_task)
    TextView mTxtLostTask;
    @BindView(R.id.txt_more_task)
    TextView mTxtMoreTask;
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

        initFlipper();
        initAlpha();
    }

    @Override
    protected void loadData() {

    }


    private void initFlipper() {

        mFlipper.addView(getImageView(R.drawable.home_banner1));
        mFlipper.addView(getImageView(R.drawable.home_banner2));
        mFlipper.addView(getImageView(R.drawable.home_banner3));
        mFlipper.addView(getImageView(R.drawable.home_banner4));
        mFlipper.startFlipping();
    }

    private ImageView getImageView(int res) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(res);
        return imageView;
    }

    private void initAlpha() {

        int alpha = 140;
        mTxtDiyTask.getBackground().setAlpha(alpha);
        mTxtLostTask.getBackground().setAlpha(alpha);
        mTxtMoreTask.getBackground().setAlpha(alpha);
        mTxtWaterTask.getBackground().setAlpha(alpha);
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

    @OnClick(R.id.txt_diy_task)
    public void onMTxtDiyTaskClicked() {
        ToastUtil.showToast("敬请期待");
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
        ToastUtil.showToast("敬请期待");
    }
}
