package cn.abtion.taskgo.mvp.presenter;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.ReleaseWaterTaskContract;
import cn.abtion.taskgo.mvp.model.request.account.LoginRequestModel;
import cn.abtion.taskgo.mvp.model.request.home.ReleaseWaterTaskRequest;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author FanHongyu.
 * @since 18/3/6 19:06.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseWaterTaskPresenter extends BasePresenter<ReleaseWaterTaskContract.View> implements ReleaseWaterTaskContract.Presenter {

    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public ReleaseWaterTaskPresenter(ReleaseWaterTaskContract.View mView) {
        super(mView);
    }




    @Override
    @SuppressWarnings("unchecked")
    public void releaseWaterTask(ReleaseWaterTaskRequest request) {

        if (isDataTrue(request)) {

            RetrofitFactory
                    .getRetrofitService()
                    .releaseWaterTask(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver() {
                        @Override
                        public void onDataSuccess(ApiResponse response) {
                            mView.onReleaseSuccess();
                        }
                    });

        } else {
            mView.onReleaseFailed("请填写完整信息");
        }
    }


    private boolean isDataTrue(ReleaseWaterTaskRequest request) {

        boolean flag = true;

        if (request.getAddress().equals("")) {
            flag = false;
        }

        return flag;
    }

}
