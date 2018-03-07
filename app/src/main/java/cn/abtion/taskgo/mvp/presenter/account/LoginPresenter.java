package cn.abtion.taskgo.mvp.presenter.account;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.account.LoginContract;
import cn.abtion.taskgo.mvp.model.account.LoginRequestModel;
import cn.abtion.taskgo.network.ResponseCallBack;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import cn.abtion.taskgo.utils.RegexpUtils;
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
            RetrofitFactory.getRetrofitService().login(new LoginRequestModel(phone,password)).enqueue(new ResponseCallBack<ApiResponse>() {
                @Override
                public void onDataResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    LoginContract.View view = mView;
                    //如果view不存在,不做任何操作
                    if (view == null) {
                        return;
                    }

                    //通知V层登录成功
                    mView.onLoginSuccess();
                }

                @Override
                public void onDataFailure(Call<ApiResponse> call, Throwable t) {

                }

                @Override
                public void dismissDialog() {

                }
            });

        }
    }


    /**
     * 验证数据是否正确
     *
     * @return
     */
    private boolean isDataTrue(String phone,String password) {

        boolean flag = true;
        if (phone.isEmpty()){
            mView.onLoginFaild("手机号不得为空");
            flag=false;
        }else if(password.isEmpty()){
            mView.onLoginFaild("密码不得为空");
            flag=false;
        }else if(!RegexpUtils.checkMobile(phone)){
            mView.onLoginFaild("账号有误，请重新输入");
            flag=false;
        }else if (password.length()<6||password.length()>20){
            mView.onLoginFaild("密码长度有误，应为6-20位");
            flag=false;
        }
        return flag;
    }



}
