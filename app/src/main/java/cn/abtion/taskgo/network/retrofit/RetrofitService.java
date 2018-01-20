package cn.abtion.taskgo.network.retrofit;


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



    @POST("public/index.php/login")
    Call<ApiResponse> login(@Body LoginModel loginModel);



    @POST("public/index.php/login")
    Observable<ApiResponse> rxLogin(@Body LoginModel loginModel);




}
