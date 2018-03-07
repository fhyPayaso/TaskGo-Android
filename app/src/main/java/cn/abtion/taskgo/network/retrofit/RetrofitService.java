package cn.abtion.taskgo.network.retrofit;


import java.util.List;

import cn.abtion.taskgo.mvp.model.request.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.model.request.account.RegisterRequestModel;
import cn.abtion.taskgo.mvp.model.request.account.UpdatePasswordRequestModel;
import cn.abtion.taskgo.mvp.model.request.home.AcceptLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.request.home.LostFindTaskModel;
import cn.abtion.taskgo.mvp.model.request.home.ReleaseLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.request.home.ReleaseWaterTaskRequest;
import cn.abtion.taskgo.mvp.model.request.home.WaterTaskResponse;
import cn.abtion.taskgo.network.response.ApiResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    @POST("user/login")
    Call<ApiResponse> login(@Body LoginRequestModel loginRequest);


//    @POST("user/register")
//    Call<APIResponse> register(@Body RegisterRequestModel mregisterRequestModel);
//
//    /**
//     * 登录
//     * 手机号登录     获取 token 以及 user_id
//     *  微信/QQ登录   验证完后输入微信或QQ号登录 获取token以及user_id
//     */
//    @POST("user/login")
//    Call<APIResponse> rxlogin(@Body LoginRequestModel mloginRequestModel);
//
//    /**
//     * 忘记密码（修改密码）
//     *
//     */
//    @POST("user/resetPassword")
//    Call<APIResponse> updatepasswordrequestmodel(@Body UpdatePasswordRequestModel mupdatePasswordRequestModel);


    /**
     * 加载水任务列表
     * @return
     */
    @GET("water/show")
    Observable<ApiResponse<List<WaterTaskResponse>>> loadWaterTaskList();

    /**
     * 发布水任务
     * @param request
     * @return
     */
    @POST("water/add")
    Observable<ApiResponse> releaseWaterTask(@Body ReleaseWaterTaskRequest request);

    /**
     * 接受水任务
     * @param taskId
     * @return
     */
    @GET("water/accept/{taskId}")
    Observable<ApiResponse> acceptWaterTask(@Path("taskId") int taskId);

    /**
     * 加载物品任务列表
     * @return
     */
    @GET("thing/list")
    Observable<ApiResponse<List<LostFindTaskModel>>> loadLostFoundTaskList();

    /**
     * 接受物品任务
     * @param request
     * @return
     */
    @GET("thing/accept")
    Observable<ApiResponse> acceptLostFoundTask(@Body AcceptLostFoundTaskRequest request);

    /**
     * 添加物品任务
     * @param request
     * @return
     */
    @POST("thing/add")
    Observable<ApiResponse> releaseLostFoundTask(@Body ReleaseLostFoundTaskRequest request);




}
