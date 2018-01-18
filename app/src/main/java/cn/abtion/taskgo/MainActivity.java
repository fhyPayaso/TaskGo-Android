package cn.abtion.taskgo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.mvp.LoginModle;
import cn.abtion.taskgo.network.DataCallBack;
import cn.abtion.taskgo.network.RetrofitClient;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    @BindView(R.id.test)
    Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.test)
    public void onViewClicked() {

        LoginModle loginModle = new LoginModle();

        loginModle.setPhone("123456");
        loginModle.setPassword("123456");


//        RetrofitClient.getApiService().login(loginModle)
//                .subscribeOn(rx.schedulers.Schedulers.newThread())
//                .observeOn(rx.schedulers.Schedulers.io())
//                .doOnNext(new Action1<ApiResponse>() {
//                    @Override
//                    public void call(ApiResponse apiResponse) {
//
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ApiResponse>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ApiResponse apiResponse) {
//
//                        ToastUtil.showToast("登录成功");
//
//                    }
//                });



        RetrofitClient.getApiService().login(loginModle).enqueue(new DataCallBack<ApiResponse>() {
            @Override
            public void onDataResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                ToastUtil.showToast("登陆成功");
            }

            @Override
            public void onDataFailure(Call<ApiResponse> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });

    }
}
