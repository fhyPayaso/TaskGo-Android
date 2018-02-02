package cn.abtion.taskgo.network.retrofit;


import cn.abtion.taskgo.mvp.model.request.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.model.request.account.RegisterRequestModel;
import cn.abtion.taskgo.mvp.model.request.account.UpdatePasswordRequestModel;
import cn.abtion.taskgo.network.response.ApiResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author FanHongyu.
 * @since 18/1/17 22:30.
 * email fanhongyu@hrsoft.net.
 */

public interface RetrofitService {




    /**
     * 登录接口
     * @param loginRequest
     * @return
     */
    @POST("login")
    Call<ApiResponse> login(@Body LoginRequestModel loginRequest);


    @POST("user/register")
    Call<APIResponse> register(@Body RegisterRequestModel mregisterRequestModel);

    /**
     * 登录
     * 手机号登录     获取 token 以及 user_id
     *  微信/QQ登录   验证完后输入微信或QQ号登录 获取token以及user_id
     */
    @POST("user/login")
    Call<APIResponse> rxlogin(@Body LoginRequestModel mloginRequestModel);

    /**
     * 忘记密码（修改密码）
     *
     */
    @POST("user/resetPassword")
    Call<APIResponse> updatepasswordrequestmodel(@Body UpdatePasswordRequestModel mupdatePasswordRequestModel);





}
