package cn.abtion.taskgo.mvp.contract.account;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.model.account.RegisterRequestModel;
import cn.abtion.taskgo.mvp.view.account.LoginActivity;

/**
 * @author heaijia
 * @since 2018/3/7 下午10:24
 * email 549044363@qq.com
 */

public interface RegisterContract {


    interface Presenter extends BaseContract.Presenter {

        void sendCaptcha(String phoneNumber);
        void register(RegisterRequestModel registerRequestModel,String repeatPassword);
        void loginAgain(LoginRequestModel loginRequestModel);
    }


    interface View extends BaseContract.View<Presenter> {

        void onRegisterSuccess();
        void onCaptchaSuccess();
        void onLoginAgainSuccess(String Token);
    }
}
