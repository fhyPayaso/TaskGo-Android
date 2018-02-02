package cn.abtion.taskgo.network.retrofit;


import cn.abtion.taskgo.mvp.model.request.account.LoginRequestModel;
import cn.abtion.taskgo.network.response.ApiResponse;
import io.reactivex.Observable;
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
    Observable<ApiResponse> rxLogin(@Body LoginRequestModel loginRequest);



    /**
     * 登录接口
     * @param loginRequest
     * @return
     */
    @POST("login")
    Call<ApiResponse> login(@Body LoginRequestModel loginRequest);

}
