package cn.abtion.taskgo.base.frgment;

import android.content.Context;

import cn.abtion.taskgo.base.contract.BaseContract;

/**
 * @author FanHongyu.
 * @since 18/1/17 00:57.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BasePresenterFragment<Presenter extends BaseContract.Presenter> extends BaseFragment
        implements BaseContract.View<Presenter> {


    /**
     * V层对P层的引用
     */
    protected Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //在界面绑定的时候就触发
        initPresenter();
    }

    /**
     * V层创建 与之绑定的P层对象
     *
     * @return 与之绑定的P层对象
     */
    protected abstract Presenter initPresenter();

    /**
     * 设置对presenter的引用
     *
     * @param presenter P层引用
     */
    @Override
    public void setPresenter(Presenter presenter) {
        this.mPresenter = presenter;
    }

}
