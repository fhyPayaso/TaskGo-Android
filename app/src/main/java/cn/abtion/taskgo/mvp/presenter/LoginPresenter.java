package cn.abtion.taskgo.mvp.presenter;

import android.content.Intent;

import butterknife.BindView;
import cn.abtion.taskgo.base.data.DataCallBack;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.data.AccountHelper;
import cn.abtion.taskgo.mvp.contract.LoginContract;
import cn.abtion.taskgo.mvp.model.request.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.view.MainActivity;
import cn.abtion.taskgo.mvp.view.account.LoginActivity;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.ResponseCallBack;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import cn.abtion.taskgo.utils.RegexpUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author FanHongyu.
 * @since 18/1/20 14:31.
 * email fanhongyu@hrsoft.net.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {


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







            mView.onLoginSuccess("登录成功");
        }

    }


    /**
     * 验证数据是否正确
     *
     * @return
     */
    private boolean isDataTrue(String phone,String password) {

        boolean flag = true;

        if(phone.equals(Config.EMPTY_FIELD)) {
            mView.onLoginFailed("账号不可为空");
            flag = false;
        }else if(!RegexpUtils.checkMobile(phone)){
            mView.onLoginFailed("账号有误，请重新输入");
            flag = false;
        }else if(password.length() < Config.PASSWORD_MIN){
            mView.onLoginFailed("请输入6位以上的密码");
            flag = false;
        }else if(password.length()>Config.PASSWORD_MAX){
            mView.onLoginFailed("密码位数最多不能超过20位");
            flag = false;
        }

        return flag;
    }



}
