package cn.abtion.taskgo.mvp.presenter.account;

import android.util.Log;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.contract.account.RegisterContract;
import cn.abtion.taskgo.mvp.model.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.model.account.RegisterRequestModel;
import cn.abtion.taskgo.mvp.model.account.TokenResponse;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import cn.abtion.taskgo.utils.RegexpUtils;
import cn.abtion.taskgo.utils.ToastUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * @author heaijia
 * @since 2018/3/7 下午10:31
 * email 549044363@qq.com
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter{


    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public RegisterPresenter(RegisterContract.View mView) {
        super(mView);
    }

    /**
     * 验证四个数据是否正确
     *
     * @return
     */
    private boolean isSecretTrue(RegisterRequestModel registerRequestModel,String repeatPassword) {

        boolean flag = true;

        if(registerRequestModel.getPhone().equals(Config.EMPTY_FIELD)){
            ToastUtil.showToast("输入的账号格式不正确");
            flag = false;
        }else if(registerRequestModel.getCaptcha().equals(Config.EMPTY_FIELD)){
            ToastUtil.showToast("验证码不能为空");
            flag = false;
        }
        else if(!registerRequestModel.getPassword().equals(repeatPassword)){
            ToastUtil.showToast("两次密码输入不一致");
            flag = false;
        }
        else if((repeatPassword.equals(Config.EMPTY_FIELD))||(registerRequestModel.getPassword().equals(Config.EMPTY_FIELD))){
            ToastUtil.showToast("两次输入密码不能为空");
            flag = false;
        }
        return flag;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void sendCaptcha(String phoneNumber) {

        if(isPhoneTrue(phoneNumber)){
            RetrofitFactory
                    .getRetrofitService()
                    .sendCaptchato(phoneNumber)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver() {
                        @Override
                        public void onDataSuccess(ApiResponse response) {
                            mView.onCaptchaSuccess();
                        }
                    });
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void register(RegisterRequestModel registerRequestModel, String repeatPassword) {

        if (isSecretTrue(registerRequestModel,repeatPassword)) {


            RetrofitFactory
                    .getRetrofitService()
                    .register(registerRequestModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver() {
                        @Override
                        public void onDataSuccess(ApiResponse response) {
                            mView.onRegisterSuccess();
                        }
                    });
        }
    }



    @Override
    @SuppressWarnings("unchecked")
    public void loginAgain(LoginRequestModel loginRequestModel) {


            RetrofitFactory
                    .getRetrofitService()
                    .login(loginRequestModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<TokenResponse>() {
                        @Override
                        public void onDataSuccess(ApiResponse<TokenResponse> response) {
                            mView.onLoginAgainSuccess(response.getData().getToken());
                        }
                    });

    }

    /**
     * 验证电话数据是否正确
     *
     * @return
     */
    private boolean isPhoneTrue(String phoneNumber) {

        boolean flag = true;

        if (phoneNumber.equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast("输入的账号格式不正确");
            flag = false;
        }
        return flag;
    }


}
