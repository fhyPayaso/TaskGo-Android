package cn.abtion.taskgo.mvp.presenter.mine;

import java.util.List;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.mine.MyFollowContract;
import cn.abtion.taskgo.mvp.model.mine.MyFollowModel;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author：lszr on 2018/3/11 16:39
 * @email：1085963811@qq.com
 */
public class MyFollowPresenter extends BasePresenter<MyFollowContract.View> implements MyFollowContract.Presenter {
    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public MyFollowPresenter(MyFollowContract.View mView) {
        super(mView);
    }


    @Override
    public void requestFollowList() {
        RetrofitFactory.getRetrofitService().followList().enqueue(new Callback<ApiResponse<List<MyFollowModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<MyFollowModel>>> call, Response<ApiResponse<List<MyFollowModel>>> response) {
                mView.getListSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<MyFollowModel>>> call, Throwable t) {

            }
        });
    }
}
