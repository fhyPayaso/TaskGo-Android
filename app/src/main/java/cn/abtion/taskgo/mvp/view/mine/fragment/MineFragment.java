package cn.abtion.taskgo.mvp.view.mine.fragment;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.mvp.contract.mine.MineInformationContract;
import cn.abtion.taskgo.mvp.model.mine.MineInformationModel;
import cn.abtion.taskgo.mvp.presenter.mine.MineInformationPresenter;
import cn.abtion.taskgo.mvp.view.mine.activity.EditDataActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.FeedbackActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.MyFansActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.MyFollowActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.SettingActivity;
import cn.abtion.taskgo.mvp.view.task.activity.my.MyAcceptTaskActivity;
import cn.abtion.taskgo.mvp.view.task.activity.my.MyReleasedTaskActivity;
import cn.abtion.taskgo.utils.ToastUtil;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 下午1:31
 * fhyPayaso@qq.com
 */
public class MineFragment extends BasePresenterFragment<MineInformationContract.Presenter> implements MineInformationContract.View {

    public MineInformationModel mineInformation = null;


    @BindView(R.id.btn_release)
    RelativeLayout mBtnRelease;
    @BindView(R.id.btn_accept)
    RelativeLayout mBtnAccept;
    @BindView(R.id.img_portrait)
    CircleImageView imgPortrait;
    @BindView(R.id.txt_mine_follower)
    TextView txtMineFollower;
    @BindView(R.id.txt_mine_fans)
    TextView txtMineFans;
    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        mPresenter.requestMineInformation();
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.requestMineInformation();
    }

    @OnClick({R.id.rlayout_mine_follow, R.id.rlayout_mine_fans, R.id.btn_release, R.id.btn_accept, R.id.rlayout_mine_real_name, R.id.rlayout_mine_feedback, R.id.rlayout_mine_setting, R.id.rlayout_mine_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlayout_mine_follow:
                MyFollowActivity.startMyFollowActivity(getContext());
                break;
            case R.id.rlayout_mine_fans:
                MyFansActivity.startMyFansActivity(getContext());
                break;
            case R.id.btn_release:
                MyReleasedTaskActivity.startActivity(getContext());
                break;
            case R.id.btn_accept:
                MyAcceptTaskActivity.startActivity(getContext());
                break;
            case R.id.rlayout_mine_real_name:
                ToastUtil.showToast("功能暂未开放，敬请期待");
//                RealNameActivity.startRealNameActivity(getContext());
                break;
            case R.id.rlayout_mine_feedback:
                FeedbackActivity.startFeedbackActivity(getContext());
                break;
            case R.id.rlayout_mine_setting:
                SettingActivity.startSettingActivity(getContext());
                break;
            case R.id.rlayout_mine_edit:
                EditDataActivity.startEditDataActivity(getContext(), mineInformation);
                break;
            default:
                break;
        }
    }

    @Override
    protected MineInformationContract.Presenter initPresenter() {
        return new MineInformationPresenter(this);
    }


    @Override
    public void onMineInformationRequestSuccess(MineInformationModel mineInformationModel) {
        Log.d("test", mineInformationModel.getAvatar());
        mineInformation = mineInformationModel;
        txtMineFollower.setText(String.valueOf(mineInformationModel.getFollowers_count()));
        txtMineFans.setText(String.valueOf(mineInformationModel.getFollowings_count()));
        Glide.with(MineFragment.this).load(mineInformationModel.getAvatar()).into(imgPortrait);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
