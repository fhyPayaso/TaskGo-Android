package cn.abtion.taskgo.mvp.contract.account;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.model.account.ForgotPasswordModel;

/**
 * @author heaijia
 * @since 2018/3/9 上午9:25
 * email 549044363@qq.com
 */

public interface ForgetPasswordContract {

    interface Presenter extends BaseContract.Presenter{
        /**
         * P层进行登录成功
         */
        void sendRequestNewInformation(ForgotPasswordModel forgotPasswordModel);

        void sendCaptcha(String phoneNumber);
    }

    interface View extends BaseContract.View<ForgetPasswordContract.Presenter>{
        /**
         * 通知V层登录成功，并进行相关的操作
         */

        void onSendInformationSuccess();
    }
}
