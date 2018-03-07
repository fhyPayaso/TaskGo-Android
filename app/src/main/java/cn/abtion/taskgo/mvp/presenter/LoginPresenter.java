package cn.abtion.taskgo.mvp.presenter;

import butterknife.BindView;
import cn.abtion.taskgo.base.data.DataCallBack;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.data.AccountHelper;
import cn.abtion.taskgo.mvp.contract.LoginContract;
import cn.abtion.taskgo.mvp.model.request.account.LoginRequestModel;

/**
 * @author FanHongyu.
 * @since 18/1/20 14:31.
 * email fanhongyu@hrsoft.net.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter,DataCallBack.SuccessCallback {




    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public LoginPresenter(LoginContract.View mView) {
        super(mView);
    }


    @Override
    public void requestLogin(String phone,String password) {

        if (isDataTrue(phone, password)) {
            AccountHelper.login(new LoginRequestModel(phone,password),this);
        }
    }


    /**
     * 验证数据是否正确
     *
     * @return
     */
    private boolean isDataTrue(String phone,String password) {

        boolean flag = true;







        return flag;
    }


    /**
     * 数据加载成功的方法
     * @param o
     */
    @Override
    public void onDataLoaded(Object o) {

        LoginContract.View view = mView;
        //如果view不存在,不做任何操作
        if (view == null) {
            return;
        }

        //通知V层登录成功
        mView.onLoginSuccess();
    }

}
