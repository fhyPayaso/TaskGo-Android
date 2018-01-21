package cn.abtion.taskgo.mvp.contract;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.request.LoginRequest;

/**
 * @author FanHongyu.
 * @since 18/1/20 14:26.
 * email fanhongyu@hrsoft.net.
 */

public interface LoginContract {


    interface View extends BaseContract.View<Presenter> {

        /**
         * 通知V层登录成功
         */
        void onLoginSuccess();
    }

    interface Presenter extends BaseContract.Presenter {

        /**
         * P层进行登录
         */
        void requestLogin(String phone,String password);
    }

}
