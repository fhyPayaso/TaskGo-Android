package cn.abtion.taskgo.network.retrofit;


import java.util.List;


import cn.abtion.taskgo.mvp.model.task.request.AcceptLostFoundTaskRequest;

import cn.abtion.taskgo.mvp.model.task.request.FinishLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.FinishUserListRequest;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseLostFoundTaskRequest;

import cn.abtion.taskgo.mvp.model.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.model.mine.ChangePasswordRequestModel;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseWaterTaskRequest;
import cn.abtion.taskgo.mvp.model.task.response.BaseTaskResponse;
import cn.abtion.taskgo.mvp.model.task.response.LostFoundTaskResponse;
import cn.abtion.taskgo.mvp.model.task.response.UserInfoReponse;
import cn.abtion.taskgo.mvp.model.task.response.WaterTaskResponse;
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
    Observable<ApiResponse> login(@Body LoginRequestModel loginRequest);


    @POST("user/resetPassword")
    Call<ApiResponse> changePassword(@Body ChangePasswordRequestModel changePasswordRequestModel);


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
     * 完成水任务
     * @param taskId
     * @return
     */
    @GET("water/finish/{taskId}")
    Observable<ApiResponse> finishWaterTask(@Path("taskId") int taskId);


    /**
     * 加载物品任务列表
     * @return
     */
    @GET("thing/list")
    Observable<ApiResponse<List<LostFoundTaskResponse>>> loadLostFoundTaskList();

    /**
     * 添加物品任务
     * @param request
     * @return
     */
    @POST("thing/add")
    Observable<ApiResponse> releaseLostFoundTask(@Body ReleaseLostFoundTaskRequest request);

    /**
     * 接受物品任务
     * @param request
     * @return
     */
    @POST("thing/accept")
    Observable<ApiResponse> acceptLostFoundTask(@Body AcceptLostFoundTaskRequest request);


    /**
     * 物品任务详细信息
     * @param taskId
     * @return
     */
    @GET("thing/show/{task_id}")
    Observable<ApiResponse<LostFoundTaskResponse>> loadTaskInfo(@Path("task_id") int taskId);

    /**
     * 加载我接受的任务列表
     * @param status
     * @return
     */
    @POST("me/accepttask")
    Observable<ApiResponse<List<BaseTaskResponse>>> loadMyAcceptTask(@Query("task_status")int status);


    /**
     * 加载我发布的任务列表
     * @param status
     * @return
     */
    @POST("me/finishtask")
    Observable<ApiResponse<List<BaseTaskResponse>>> loadMyReleaseTask(@Query("task_status")int status);


    /**
     * 完成物品任务
     * @param request
     * @return
     */
    @GET("thing/finish")
    Observable<ApiResponse> finishLostFoundTask(@Body FinishLostFoundTaskRequest request);


    /**
     * 加载完成用户列表
     * @param request
     * @return
     */
    @POST("thing/user")
    Observable<ApiResponse<List<Integer>>> loadFinishUserList(@Body FinishUserListRequest request);


    /**
     * 查看其他用户简易信息
     * @param userId
     * @return
     */
    @GET("user/info/show/{user_id}")
    Observable<ApiResponse<UserInfoReponse>> loadSimpleUserInfo(@Path("user_id")int userId);



}
