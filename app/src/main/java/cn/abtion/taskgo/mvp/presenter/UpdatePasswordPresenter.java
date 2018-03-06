package cn.abtion.taskgo.mvp.presenter;

import cn.abtion.taskgo.base.data.DataCallBack;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.UpdatePasswordContract;

/**
 * @author：lszr on 2018/3/6 20:14
 * @email：1085963811@qq.com
 */
public class UpdatePasswordPresenter extends BasePresenter<UpdatePasswordContract.View>implements UpdatePasswordContract.Presenter,DataCallBack.SuccessCallback {


    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public UpdatePasswordPresenter(UpdatePasswordContract.View mView) {
        super(mView);
    }

    @Override
    public void requestUpdatePassword(String phone, String password) {

    }

    @Override
    public void onDataLoaded(Object o) {

    }
}
