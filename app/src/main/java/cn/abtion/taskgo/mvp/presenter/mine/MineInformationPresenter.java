package cn.abtion.taskgo.mvp.presenter.mine;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.mine.MineInformationContract;
import cn.abtion.taskgo.mvp.model.mine.MineInformationModel;
import cn.abtion.taskgo.network.ResponseCallBack;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author：lszr on 2018/3/7 11:31
 * @email：1085963811@qq.com
 */
public class MineInformationPresenter extends BasePresenter<MineInformationContract.View>implements MineInformationContract.Presenter {
    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public MineInformationPresenter(MineInformationContract.View mView) {
        super(mView);
    }



    @Override
    public void requestMineInformation() {
        RetrofitFactory.getRetrofitService().mineInformation().enqueue(new ResponseCallBack<ApiResponse<MineInformationModel>>() {
            @Override
            public void onDataResponse(Call<ApiResponse<MineInformationModel>> call, Response<ApiResponse<MineInformationModel>> response) {
                mView.onMineInformationRequestSuccess(response.body().getData());
            }

            @Override
            public void onDataFailure(Call<ApiResponse<MineInformationModel>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });


//        RetrofitFactory
//                .getRetrofitService()
//                .mineInformation()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<MineInformationModel>() {
//                    @Override
//                    public void onDataSuccess(ApiResponse<MineInformationModel> response) {
//                        Log.i(TAG, "onDataSuccess: "+response.getMessage());
//                    }
//                });

    }
}
