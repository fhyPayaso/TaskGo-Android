package cn.abtion.taskgo.mvp.view.initiate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.frgment.BaseFragment;
import cn.abtion.taskgo.mvp.view.account.LoginActivity;
import cn.abtion.taskgo.mvp.view.account.RegisterActivity;

/**
 * Created by heaijia on 2018/1/28.
 */

public class InitiateSecondFragment extends BaseFragment {



    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }


    @BindView(R.id.onclick_initiate_second)
    ImageView onclickInitiateSecond;
    @BindView(R.id.btn_initiate)
    Button btnInitiate;
    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_initiate_second;
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

    @OnClick(R.id.btn_initiate)
    public void onViewClicked() {

        Intent intent =new Intent();
        getActivity().startActivity(intent);

    }
}
