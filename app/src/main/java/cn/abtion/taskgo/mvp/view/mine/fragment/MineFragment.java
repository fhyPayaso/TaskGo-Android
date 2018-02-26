package cn.abtion.taskgo.mvp.view.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.mvp.view.mine.activity.EditDataActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.FeedbackActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.MyAcceptTaskActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.MyFansActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.MyFollowActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.MyReleasedTaskActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.RealNameActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.SettingActivity;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 下午1:31
 * fhyPayaso@qq.com
 */
public class MineFragment extends BasePresenterFragment {


    @BindView(R.id.rlayout_mine_follow)
    RelativeLayout rlayoutMineFollow;

    @BindView(R.id.btn_release)
    RelativeLayout mBtnRelease;
    @BindView(R.id.btn_accept)
    RelativeLayout mBtnAccept;
    Unbinder unbinder;
    Unbinder unbinder1;

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {


    }

    @Override
    protected void loadData() {

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
                RealNameActivity.stratRealNameActivity(getContext());
                break;
            case R.id.rlayout_mine_feedback:
                FeedbackActivity.startFeedbackActivity(getContext());
                break;
            case R.id.rlayout_mine_setting:
                SettingActivity.startSettingActivity(getContext());
                break;
            case R.id.rlayout_mine_edit:
                EditDataActivity.startEditDataActivity(getContext());
                break;
            default:
                break;
        }
    }
}
