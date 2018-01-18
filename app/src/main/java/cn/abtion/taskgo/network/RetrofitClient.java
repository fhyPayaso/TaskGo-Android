package cn.abtion.taskgo.network;



import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.abtion.taskgo.TaskGoApplication;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.common.constants.CacheKey;
import cn.abtion.taskgo.network.converter.ResponseConverterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * @author FanHongyu.
 * @since 18/1/18 15:48.
 * email fanhongyu@hrsoft.net.
 */

public final class RetrofitClient {


    private static OkHttpClient sOkHttpClient;
    private static Retrofit sRetrofit;
    private static ApiService sApiService;


    /**
     * 生成API请求
     *
     * @return ApiService
     */
    public static ApiService getApiService() {

        if (sApiService == null) {
            sApiService = getRetrofit().create(ApiService.class);
        }

        return sApiService;
    }


    /**
     * 构造Retrofit,设置相关参数
     *
     * @return Retrofit
     */
    private static Retrofit getRetrofit() {

        if (sRetrofit == null) {

            sRetrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(Config.APP_SERVER_BASE_URL)
                    //增加对返回值为自定义Response类型的支持,默认是Gson
                    .addConverterFactory(ResponseConverterFactory.create())
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;
    }


    /**
     * 构造OkHttp客户端，设置相关参数
     *
     * @return OkHttp客户端
     */
    private static OkHttpClient getOkHttpClient() {


        if (sOkHttpClient == null) {

            sOkHttpClient = new OkHttpClient.Builder()
                    //设置超时时间为15s
                    .connectTimeout(Config.APP_SERVER_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    //设置出现错误时重新连接
                    .retryOnConnectionFailure(true)
                    //添加应用拦截器，统一打印请求与响应的json
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    //添加网络拦截器，发送请求时加入token
                    .addNetworkInterceptor(getNetworkInterceptor())
                    .build();

        }
        return sOkHttpClient;
    }


    /**
     * 自定义网络拦截器
     *
     * @return Interceptor
     */
    private static Interceptor getNetworkInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                //获取本地缓存的token
                String token = TaskGoApplication.getInstance().getCacheUtil().getString(CacheKey.TOKEN);
                if (token == null) {
                    token = "token";
                }
                // TODO: 18/1/18 修改保存token的方式
                //请求时加入token
                Request request = chain.request().newBuilder()
                        .header("Token", token)
                        .build();
                return chain.proceed(request);
            }
        };
    }
}
