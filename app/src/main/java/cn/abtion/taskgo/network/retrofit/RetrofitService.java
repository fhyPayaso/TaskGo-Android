package cn.abtion.taskgo.network.retrofit;


import java.util.List;

import butterknife.BindView;
import cn.abtion.taskgo.mvp.model.account.ForgotPasswordModel;
import cn.abtion.taskgo.mvp.model.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.model.account.RegisterRequestModel;
import cn.abtion.taskgo.mvp.model.mine.ChangePasswordRequestModel;
import cn.abtion.taskgo.mvp.model.task.ReleaseWaterTaskRequest;
import cn.abtion.taskgo.mvp.model.task.WaterTaskResponse;
import cn.abtion.taskgo.network.response.ApiResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @GET("user/getCaptcha/{mobile}")
    Observable<ApiResponse> sendCaptchato(@Path("mobile") String mobile);

    @POST("user/register")
    Observable<ApiResponse> register(@Body RegisterRequestModel registerRequestModel);

    @POST("user/forgotPassword")
    Observable<ApiResponse> forgetPassWord(@Body ForgotPasswordModel forgotPasswordModel);


    /**
     * 加载水任务列表
     * @return
     */
    @GET("water/show")
    Observable<ApiResponse<List<WaterTaskResponse>>> loadWaterTaskList();

    @POST("water/add")
    Observable<ApiResponse> releaseWaterTask(@Body ReleaseWaterTaskRequest request);

    @GET("water/accept")
    Observable<ApiResponse> acceptWaterTask(@Query("taskId")int taskId);


}
