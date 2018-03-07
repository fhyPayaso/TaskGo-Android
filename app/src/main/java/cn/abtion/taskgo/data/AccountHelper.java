package cn.abtion.taskgo.data;

import cn.abtion.taskgo.base.data.DataCallBack;
import cn.abtion.taskgo.mvp.model.request.account.LoginRequestModel;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.ResponseCallBack;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
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
    public static void login(final LoginRequestModel request, final DataCallBack.SuccessCallback callback) {


        RetrofitFactory.getRetrofitService().login(request).enqueue(new ResponseCallBack<ApiResponse>() {

            /**
             * 请求成功时回掉
             * @param call
             * @param response
             */
            @Override
            public void onDataResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                callback.onDataLoaded(null);
            }

            /**
             * 请求失败时回掉
             * @param call
             * @param t
             */
            @Override
            public void onDataFailure(Call<ApiResponse> call, Throwable t) {

            }

            /**
             *无论成功或者失败时都回调，用于dismissDialog或隐藏其他控件
             */
            @Override
            public void dismissDialog() {

            }
        });


//        RetrofitFactory
//                .getRetrofitService()
//                .rxLogin(request)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver() {
//                    @Override
//                    public void onDataSuccess(ApiResponse response) {
//                        callback.onDataLoaded(null);
//                    }
//                });

    }
}
