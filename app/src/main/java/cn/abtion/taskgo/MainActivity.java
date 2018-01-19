package cn.abtion.taskgo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.LoginModel;
import cn.abtion.taskgo.network.DataCallBack;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.test.LoginService;
import cn.abtion.taskgo.utils.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.test)
    Button test;

    String phone = "123456";
    String password = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.test)
    public void onViewClicked() {





//        RetrofitFactory.getRetrofitService().login(loginModel)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe()
        //login();

        rxLogin();
    }

    public void login1() {


        LoginModel loginModel = new LoginModel();
        loginModel.setPhone("123456");
        loginModel.setPassword("123456");

        RetrofitFactory
                .getRetrofitService()
                .login(loginModel)
                .enqueue(new DataCallBack<ApiResponse>() {

            @Override
            public void onDataResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                ToastUtil.showToast("登录成功");
            }

            @Override
            public void onDataFailure(Call<ApiResponse> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });


    }


    public void login() {

//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(Config.APP_SERVER_BASE_URL)
//                .build();
//
//        retrofit.create(LoginService.class)
//                .login(phone,password)
//                .enqueue(new Callback<ApiResponse>() {
//                    @Override
//                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//
//                        //登录成功后的操作
//                    }
//
//                    @Override
//                    public void onFailure(Call<ApiResponse> call, Throwable t) {
//
//                        //登录失败后的操作
//                    }
//                });
    }


    public void rxLogin() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //添加对RxJava的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Config.APP_SERVER_BASE_URL)
                .build();

        retrofit.create(LoginService.class)
                .rxLogin(phone,password)
                //io线程获取网络请求
                .subscribeOn(Schedulers.io())
                //主线程进行数据更新
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ApiResponse apiResponse) {

                        //登录成功后的操作
                    }

                    @Override
                    public void onError(Throwable e) {

                        //登录失败后的操作
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
