package cn.abtion.taskgo.mvp.presenter.account;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.account.SplashContract;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author FanHongyu.
 * @since 18/3/11 21:53.
 * email fanhongyu@hrsoft.net.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public SplashPresenter(SplashContract.View mView) {
        super(mView);
    }

    @Override
    public void checkToken(String token) {

        RetrofitFactory.getRetrofitService().checkToken(token).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if (response.body().getData().equals("0")) {
                    mView.effectiveToken();
                } else {
                    mView.invalidToken();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                mView.invalidToken();
            }
        });
    }
}
