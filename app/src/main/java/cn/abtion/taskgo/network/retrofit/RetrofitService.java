package cn.abtion.taskgo.network.retrofit;


import java.util.List;

import cn.abtion.taskgo.mvp.model.account.ForgotPasswordModel;
import cn.abtion.taskgo.mvp.model.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.model.account.RegisterRequestModel;
import cn.abtion.taskgo.mvp.model.account.TokenResponse;
import cn.abtion.taskgo.mvp.model.mine.ChangePasswordRequestModel;
import cn.abtion.taskgo.mvp.model.mine.MineInformationModel;
import cn.abtion.taskgo.mvp.model.mine.MyFansListModel;
import cn.abtion.taskgo.mvp.model.mine.MyFollowModel;
import cn.abtion.taskgo.mvp.model.mine.PersonalPageModel;
import cn.abtion.taskgo.mvp.model.mine.UpdateInformationModel;
import cn.abtion.taskgo.mvp.model.task.request.AcceptLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.FinishLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.FinishUserListRequest;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseWaterTaskRequest;
import cn.abtion.taskgo.mvp.model.task.response.BaseTaskResponse;
import cn.abtion.taskgo.mvp.model.task.response.LostFoundTaskResponse;
import cn.abtion.taskgo.mvp.model.task.response.SimpleUserInfoResponse;
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
     *
     * @param loginRequest
     * @return
     */
    @POST("user/login")
    Observable<ApiResponse<TokenResponse>> login(@Body LoginRequestModel loginRequest);

    /**
     * 修改密码接口
     *
     * @param changePasswordRequestModel
     * @return
     */
    @POST("user/resetPassword")
    Call<ApiResponse> changePassword(@Body ChangePasswordRequestModel changePasswordRequestModel);

    /**
     * 获取验证码接口
     *
     * @param mobile
     * @return
     */
    @GET("user/getCaptcha/{mobile}")
    Observable<ApiResponse> sendCaptchato(@Path("mobile") String mobile);

    /**
     * 注册接口
     *
     * @param registerRequestModel
     * @return
     */
    @POST("user/register")
    Observable<ApiResponse> register(@Body RegisterRequestModel registerRequestModel);


    /**
     * 忘记密码接口
     *
     * @param forgotPasswordModel
     * @return
     */
    @POST("user/forgotPassword")
    Observable<ApiResponse> forgetPassWord(@Body ForgotPasswordModel forgotPasswordModel);


    /**
     * 个人主页面信息
     * @return
     */
    @GET("user/info/show")
    Call<ApiResponse<MineInformationModel>> mineInformation();

    /**
     * 关注某人
     * @param followerId
     * @return
     */
    @GET("user/follow/{follower_id}")
    Call<ApiResponse<MineInformationModel>> followSB(@Path("follower_id") String followerId);

    /**
     * 取消关注
     * @param unFollowId
     * @return
     */
    @GET("user/unFollow/{follower_id}")
    Call<ApiResponse<MineInformationModel>> cancelFollow(@Path("follower_id") String unFollowId);

    /**
     * 获取关注列表
     * @return
     */
    @GET("user/getFollowers")
    Call<ApiResponse<List<MyFollowModel>>> followList();

    /**
     * 获取粉丝列表
     * @return
     */
    @GET("user/getFollowings")
    Call<ApiResponse<List<MyFansListModel>>> fansList();

    /**
     * 更新个人信息
     * @param UpdateInformationModel
     * @return
     */
    @POST("user/info/update")
    Call<ApiResponse> updateInformation(@Body UpdateInformationModel UpdateInformationModel);

    /**
     * 获得个人主页信息
     * @param userId
     * @return
     */
    @GET("user/info/show/{user_id}")
    Call<ApiResponse<PersonalPageModel>> getPersonalPageInformation(@Path("user_id")String userId);


    /**
     * 检查token是否过期
     *
     * @param token
     * @return
     */
    @GET("user/checkToken/{token}")
    Observable<ApiResponse> checkToke(@Path("token") String token);

    /**
     * 加载水任务列表
     *
     * @return
     */
    @GET("water/show")
    Observable<ApiResponse<List<WaterTaskResponse>>> loadWaterTaskList();

    /**
     * 发布水任务
     *
     * @param request
     * @return
     */
    @POST("water/add")
    Observable<ApiResponse> releaseWaterTask(@Body ReleaseWaterTaskRequest request);

    /**
     * 接受水任务
     *
     * @param taskId
     * @return
     */
    @GET("water/accept/{taskId}")
    Observable<ApiResponse> acceptWaterTask(@Path("taskId") int taskId);

    /**
     * 接受全部水任务
     *
     * @return
     */
    @GET("water/all/accept")
    Observable<ApiResponse> acceptAllWaterTask();

    /**
     * 完成水任务
     *
     * @param taskId
     * @return
     */
    @GET("water/finish/{taskId}")
    Observable<ApiResponse> finishWaterTask(@Path("taskId") int taskId);

    /**
     * 加载物品任务列表
     *
     * @return
     */
    @GET("thing/list")
    Observable<ApiResponse<List<LostFoundTaskResponse>>> loadLostFoundTaskList();

    /**
     * 添加物品任务
     *
     * @param request
     * @return
     */
    @POST("thing/add")
    Observable<ApiResponse> releaseLostFoundTask(@Body ReleaseLostFoundTaskRequest request);

    /**
     * 接受物品任务
     *
     * @param request
     * @return
     */
    @POST("thing/accept")
    Observable<ApiResponse> acceptLostFoundTask(@Body AcceptLostFoundTaskRequest request);


    /**
     * 物品任务详细信息
     *
     * @param taskId
     * @return
     */
    @GET("thing/show/{task_id}")
    Observable<ApiResponse<LostFoundTaskResponse>> loadTaskInfo(@Path("task_id") int taskId);

    /**
     * 加载我接受的任务列表
     *
     * @param status
     * @return
     */
    @POST("me/accepttask")
    Observable<ApiResponse<List<BaseTaskResponse>>> loadMyAcceptTask(@Query("task_status") int status);


    /**
     * 加载我发布的任务列表
     *
     * @param status
     * @return
     */
    @POST("me/finishtask")
    Observable<ApiResponse<List<BaseTaskResponse>>> loadMyReleaseTask(@Query("task_status") int status);


    /**
     * 完成物品任务
     *
     * @param request
     * @return
     */
    @POST("thing/finish")
    Observable<ApiResponse> finishLostFoundTask(@Body FinishLostFoundTaskRequest request);


    /**
     * 加载完成用户列表
     *
     * @param request
     * @return
     */
    @POST("thing/user")
    Observable<ApiResponse<List<SimpleUserInfoResponse>>> loadFinishUserList(@Body FinishUserListRequest request);

}
