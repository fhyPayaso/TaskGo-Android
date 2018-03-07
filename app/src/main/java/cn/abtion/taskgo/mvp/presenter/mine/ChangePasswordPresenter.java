package cn.abtion.taskgo.mvp.presenter.mine;

import cn.abtion.taskgo.base.data.DataCallBack;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.mine.ChangePasswordContract;
import cn.abtion.taskgo.mvp.model.mine.ChangePasswordRequestModel;
import cn.abtion.taskgo.network.ResponseCallBack;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author：lszr on 2018/3/6 21:11
 * @email：1085963811@qq.com
 */
public class ChangePasswordPresenter extends BasePresenter<ChangePasswordContract.View> implements ChangePasswordContract.Presenter,DataCallBack.SuccessCallback {
    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public ChangePasswordPresenter(ChangePasswordContract.View mView) {
        super(mView);
    }



    @Override
    public void onDataLoaded(Object o) {

    }

    @Override
    public void requestChangePassword(String oldPassword, String newPassword, String repeatPassword) {
        if (isDataTrue(oldPassword,newPassword,repeatPassword)){
            RetrofitFactory.getRetrofitService().changePassword(new ChangePasswordRequestModel(oldPassword, newPassword)).enqueue(new ResponseCallBack<ApiResponse>() {
                @Override
                public void onDataResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    mView.onChangePasswordSuccess();
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


    public boolean isDataTrue(String oldPassword, String newPassword, String repeatPassword){
        boolean flag=true;
        if(oldPassword.isEmpty()){
            mView.onFormatError("旧密码不得为空");
            flag=false;
        }else if (newPassword.isEmpty()){
            mView.onFormatError("新密码不得为空");
            flag=false;
        }else if (repeatPassword.isEmpty()){
            mView.onFormatError("重复新密码不得为空");
            flag=false;
        }else if (oldPassword.length()<6||oldPassword.length()>20){
            mView.onFormatError("旧密码长度不符合规范");
            flag=false;
        }else if(newPassword.length()<6||newPassword.length()>20){
            mView.onFormatError("新密码长度不符合规范");

            flag=false;
        }else if(repeatPassword.length()<6||repeatPassword.length()>20){
            mView.onFormatError("重复新密码长度不符合规范");
            flag=false;
        }else if(!newPassword.equals(repeatPassword)){
            mView.onFormatError("两次输入密码不同");
            flag=false;
        }
        return flag;
    }
}
