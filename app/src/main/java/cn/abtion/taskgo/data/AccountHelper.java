package cn.abtion.taskgo.data;

import cn.abtion.taskgo.base.data.DataCallBack;
import cn.abtion.taskgo.mvp.model.request.LoginRequest;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.ResponseCallBack;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import cn.abtion.taskgo.network.retrofit.RetrofitService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 上午2:16
 * fhyPayaso@qq.com
 */
public class AccountHelper {


    /**
     * 登录请求
     */
    @SuppressWarnings("unchecked")
    public static void login(final LoginRequest request, final DataCallBack.SuccessCallback callback) {


        RetrofitFactory.getRetrofitService().login(request).enqueue(new ResponseCallBack<ApiResponse>() {
            @Override
            public void onDataResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                callback.onDataLoaded(null);
            }

            @Override
            public void onDataFailure(Call<ApiResponse> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });


        RetrofitFactory
                .getRetrofitService()
                .rxLogin(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onDataSuccess(ApiResponse response) {
                        callback.onDataLoaded(null);
                    }
                });

    }
}
