package cn.abtion.taskgo.network;

import cn.abtion.taskgo.network.response.ApiException;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.utils.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 上午3:51
 * fhyPayaso@qq.com
 */
public abstract class BaseObserver<T> implements Observer<ApiResponse<T>> {


    public abstract void onDataSuccess(ApiResponse response);


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ApiResponse tApiResponse) {

        if (tApiResponse.getCode() != 1000) {

            GlobalAPIErrorHandler.handler(tApiResponse.getCode());
        } else {

            onDataSuccess(tApiResponse);
        }
    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof ApiException) {
            GlobalAPIErrorHandler.handler((ApiException) e);
        } else {
            ToastUtil.showToast("网络连接失败，请稍后再试");
        }
    }

    @Override
    public void onComplete() {

    }
}
