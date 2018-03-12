package cn.abtion.taskgo.mvp.presenter.account;

import android.util.Log;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.contract.account.ForgetPasswordContract;
import cn.abtion.taskgo.mvp.model.account.ForgotPasswordModel;
import cn.abtion.taskgo.mvp.model.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.view.account.ForgetPasswordActivity;
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
 * @since 2018/3/9 上午9:28
 * email 549044363@qq.com
 */

public class ForgetPasswordPresent extends BasePresenter<ForgetPasswordContract.View>implements ForgetPasswordContract.Presenter {


    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public ForgetPasswordPresent(ForgetPasswordContract.View mView) {
        super(mView);
    }


    @Override
    public void sendRequestNewInformation(ForgotPasswordModel forgotPasswordModel) {
        if (isInformationTrue(forgotPasswordModel)) {


            RetrofitFactory
                    .getRetrofitService()
                    .forgetPassWord(forgotPasswordModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver() {
                        @Override
                        public void onDataSuccess(ApiResponse response) {
                            mView.onSendInformationSuccess();

                        }
                    });
        }
    }



    @Override
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
//                            mView.onSendInformationSuccess();
                        }
                    });
        }
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

    /**
     * 验证数据是否正确
     *
     * @return
     */


    private boolean isInformationTrue(ForgotPasswordModel forgotPasswordModel) {

        boolean flag = true;


        if (forgotPasswordModel.getMobile().equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast("账号不可为空");
            flag = false;
        } else if (!RegexpUtils.checkMobile(forgotPasswordModel.getMobile())) {
            ToastUtil.showToast("账号个数有误，请重新输入");
            flag = false;
        } else if (forgotPasswordModel.getNew_password().length() < Config.PASSWORD_MIN) {
            ToastUtil.showToast("请输入6位以上的密码");
            flag = false;
        } else if (forgotPasswordModel.getNew_passwordagain().length() > Config.PASSWORD_MAX) {
            ToastUtil.showToast("密码位数最多不能超过20位");
            flag = false;
        } else if (!forgotPasswordModel.getNew_password().equals(forgotPasswordModel.getNew_passwordagain())) {
            ToastUtil.showToast("两次密码输入不一致");
            flag = false;
        } else if ((forgotPasswordModel.getNew_password().equals(Config.EMPTY_FIELD)) || (forgotPasswordModel.getNew_passwordagain().equals(Config.EMPTY_FIELD))) {
            ToastUtil.showToast("两次输入密码不能为空");
            flag = false;
        }

        return flag;
    }

}
