package cn.abtion.taskgo.mvp.presenter.mine;

import java.util.List;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.mine.MyFansContract;
import cn.abtion.taskgo.mvp.model.mine.MyFansListModel;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author：lszr on 2018/3/12 17:58
 * @email：1085963811@qq.com
 */
public class MyFansPresneter extends BasePresenter<MyFansContract.View> implements MyFansContract.Presenter {
    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public MyFansPresneter(MyFansContract.View mView) {
        super(mView);
    }

    @Override
    public void requestFollowList() {

        RetrofitFactory.getRetrofitService().fansList().enqueue(new Callback<ApiResponse<List<MyFansListModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<MyFansListModel>>> call, Response<ApiResponse<List<MyFansListModel>>> response) {
                mView.getFansListSuccess(response.body().getData());

            }

            @Override
            public void onFailure(Call<ApiResponse<List<MyFansListModel>>> call, Throwable t) {

            }
        });

    }
}
