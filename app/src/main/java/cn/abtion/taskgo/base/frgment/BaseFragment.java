package cn.abtion.taskgo.base.frgment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author FanHongyu.
 * @since 18/1/17 00:50.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BaseFragment extends Fragment {


    /**
     * 当前Fragment RootView
     */
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mView);
        initFragment();
        return mView;
    }

    /**
     * 页面初始化操作.
     */
    protected void initFragment() {
        initVariable();
        initView();
        loadData();
    }


    /**
     * 获取LayoutId.
     *
     * @return LayoutId 布局文件Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化变量.
     */
    protected abstract void initVariable();

    /**
     * 初始化View的状态，挂载各种监听事件.
     */
    protected abstract void initView();

    /**
     * 初始化加载页面数据.
     */
    protected abstract void loadData();


    /**
     * 获取当前Fragment的RootView
     *
     * @return RootView
     */
    protected View getRootView() {
        return mView;
    }


}
