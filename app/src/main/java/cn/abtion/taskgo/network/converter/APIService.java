package cn.abtion.taskgo.network.converter;

import java.util.List;
import java.util.Map;


import cn.abtion.taskgo.mvp.model.request.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.model.request.account.RegisterRequestModel;
import cn.abtion.taskgo.mvp.model.request.account.UpdatePasswordRequestModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author heaijia
 * @since 2018/2/2 上午11:33
 * email 549044363@qq.com
 */


public interface APIService {
    /**
     * 注册
     * 手机号登录   获取 token 以及 user_id
     * 微信/QQ登录  验证完后输入微信或QQ号登录 获取token以及user_id
     */
    @POST("user/register")
    Call<APIResponse> register(@Body RegisterRequestModel mregisterRequestModel);

    /**
     * 登录
     * 手机号登录     获取 token 以及 user_id
     *  微信/QQ登录   验证完后输入微信或QQ号登录 获取token以及user_id
     */
    @POST("user/login")
    Call<APIResponse> login(@Body LoginRequestModel mloginRequestModel);

    /**
     * 忘记密码（修改密码）
     *
     */
    @POST("user/resetPassword")
    Call<APIResponse> updatepasswordrequestmodel(@Body UpdatePasswordRequestModel mupdatePasswordRequestModel);






}
