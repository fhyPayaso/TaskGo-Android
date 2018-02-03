package cn.abtion.taskgo.mvp.view.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.mvp.view.mine.activity.MyAcceptTaskActivity;
import cn.abtion.taskgo.mvp.view.mine.activity.MyReleasedTaskActivity;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 下午1:31
 * fhyPayaso@qq.com
 */
public class MineFragment extends BasePresenterFragment {

    @BindView(R.id.btn_release)
    Button mBtnRelease;
    @BindView(R.id.btn_accept)
    Button mBtnAccept;
    Unbinder unbinder;

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

    @OnClick({R.id.btn_release, R.id.btn_accept})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_release:
                MyReleasedTaskActivity.startActivity(getContext());
                break;
            case R.id.btn_accept:
                MyAcceptTaskActivity.startActivity(getContext());
                break;
            default:
                break;
        }
    }
}
