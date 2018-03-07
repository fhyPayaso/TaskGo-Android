package cn.abtion.taskgo.base.presenter;

import cn.abtion.taskgo.base.contract.BaseContract;

/**
 * @author FanHongyu.
 * @since 18/1/16 16:15.
 * email fanhongyu@hrsoft.net.
 */

public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter {


    protected T mView;

    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    @SuppressWarnings("unchecked")
    public BasePresenter(T mView) {

        //将V层的引用捆绑到P层
        this.mView = mView;

        //将P层自身捆绑到V层
        this.mView.setPresenter(this);
    }


    /**
     * 销毁P层,销毁V与P之间的引用,双向解绑
     */
    @SuppressWarnings("unchecked")
    @Override
    public void destroy() {

        T view = mView;

        //销毁V层对P层的引用
        if (view != null) {
            view.setPresenter(null);
        }

        //销毁P层对V层的引用
        mView = null;

    }


}
