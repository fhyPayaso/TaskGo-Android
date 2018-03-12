package cn.abtion.taskgo.mvp.presenter.mine;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.mine.PersonalPageContract;
import cn.abtion.taskgo.mvp.model.mine.MineInformationModel;
import cn.abtion.taskgo.mvp.model.mine.PersonalPageModel;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author：lszr on 2018/3/11 21:06
 * @email：1085963811@qq.com
 */
public class PersonalPagePresenter extends BasePresenter<PersonalPageContract.View> implements PersonalPageContract.Presenter{
    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public PersonalPagePresenter(PersonalPageContract.View mView) {
        super(mView);
    }


    @Override
    public void requestPersonalInformation(String userId) {
        RetrofitFactory.getRetrofitService().getPersonalPageInformation(userId).enqueue(new Callback<ApiResponse<PersonalPageModel>>() {
            @Override
            public void onResponse(Call<ApiResponse<PersonalPageModel>> call, Response<ApiResponse<PersonalPageModel>> response) {
                mView.getPersonalInformationsuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<PersonalPageModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void requestFollowSB(String followerId) {
        RetrofitFactory.getRetrofitService().followSB(followerId).enqueue(new Callback<ApiResponse<MineInformationModel>>() {
            @Override
            public void onResponse(Call<ApiResponse<MineInformationModel>> call, Response<ApiResponse<MineInformationModel>> response) {
                mView.followSBSuccess();
            }

            @Override
            public void onFailure(Call<ApiResponse<MineInformationModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void requestCancelFollow(String userId) {
        RetrofitFactory.getRetrofitService().cancelFollow(userId).enqueue(new Callback<ApiResponse<MineInformationModel>>() {
            @Override
            public void onResponse(Call<ApiResponse<MineInformationModel>> call, Response<ApiResponse<MineInformationModel>> response) {
                mView.cancelFollowSuccess();
            }

            @Override
            public void onFailure(Call<ApiResponse<MineInformationModel>> call, Throwable t) {

            }
        });
    }


}
