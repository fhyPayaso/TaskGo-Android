package cn.abtion.taskgo.network;


import cn.abtion.taskgo.mvp.LoginModle;
import cn.abtion.taskgo.network.response.ApiResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author FanHongyu.
 * @since 18/1/17 22:30.
 * email fanhongyu@hrsoft.net.
 */

public interface ApiService {



    @POST("public/index.php/login")

    Call<ApiResponse> login(@Body LoginModle loginModle);


}
