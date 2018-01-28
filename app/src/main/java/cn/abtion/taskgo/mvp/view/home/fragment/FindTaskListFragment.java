package cn.abtion.taskgo.mvp.view.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.model.request.home.LostFindTaskModel;
import cn.abtion.taskgo.mvp.view.home.adapter.LostFindTaskRecAdapter;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 下午11:32
 * fhyPayaso@qq.com
 */
public class FindTaskListFragment extends BasePresenterFragment {

    @BindView(R.id.rec_lost_find_task)
    RecyclerView recLostFindTask;
    Unbinder unbinder;
    LostFindTaskRecAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

    @Override
    protected BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_task_list;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        initRecyclerView();
    }

    @Override
    protected void loadData() {

    }


    private void initRecyclerView() {


        List<LostFindTaskModel> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new LostFindTaskModel("111", "fhyPayaso",
                    "呱太", "12-09 14:20"));
        }
        mAdapter = new LostFindTaskRecAdapter(getContext(),list);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recLostFindTask.setLayoutManager(mLayoutManager);
        recLostFindTask.setAdapter(mAdapter);
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
