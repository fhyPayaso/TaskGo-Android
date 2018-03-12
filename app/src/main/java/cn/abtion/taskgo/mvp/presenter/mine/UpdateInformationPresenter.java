package cn.abtion.taskgo.mvp.presenter.mine;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.mine.UpdateInformationContract;
import cn.abtion.taskgo.mvp.model.mine.UpdateInformationModel;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author：lszr on 2018/3/9 19:07
 * @email：1085963811@qq.com
 */
public class UpdateInformationPresenter extends BasePresenter<UpdateInformationContract.View>implements UpdateInformationContract.Presenter{
    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public UpdateInformationPresenter(UpdateInformationContract.View mView) {
        super(mView);
    }

    @Override
    public void requestUpdateInformation(String name, String avater, String sex, String birth) {
        RetrofitFactory.getRetrofitService().updateInformation(new UpdateInformationModel(name,avater,sex,birth)).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                mView.updateInformationSuccess();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }
}
