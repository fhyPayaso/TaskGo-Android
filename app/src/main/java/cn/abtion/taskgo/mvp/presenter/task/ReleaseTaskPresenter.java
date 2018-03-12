package cn.abtion.taskgo.mvp.presenter.task;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.task.ReleaseTaskContract;

import cn.abtion.taskgo.mvp.model.task.request.ReleaseLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseWaterTaskRequest;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import cn.abtion.taskgo.utils.RegexpUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author FanHongyu.
 * @since 18/3/6 19:06.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseTaskPresenter extends BasePresenter<ReleaseTaskContract.View> implements
        ReleaseTaskContract.Presenter {

    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public ReleaseTaskPresenter(ReleaseTaskContract.View mView) {
        super(mView);
    }


    /**
     * 发布水任务网络请求
     * @param request
     */
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
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void releaseLostFoundTask(ReleaseLostFoundTaskRequest request) {

        if (isDataTrue(request)) {
            RetrofitFactory
                    .getRetrofitService()
                    .releaseLostFoundTask(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver() {
                        @Override
                        public void onDataSuccess(ApiResponse response) {
                            mView.onReleaseSuccess();
                        }
                    });
        }
    }

    /**
     * 水任务字段检查
     *
     * @param request
     * @return
     */
    private boolean isDataTrue(ReleaseWaterTaskRequest request) {

        boolean flag = true;
        String address = request.getAddress();

        if (address.equals("")) {
            mView.onReleaseFailed("请填宿舍号");
            flag = false;
        } else if (address.length() !=4 || !RegexpUtils.checkAllNumber(address)) {
            mView.onReleaseFailed("宿舍号格式错误");
            flag = false;
        }
        return flag;
    }

    /**
     * 物品任务字段检查
     *
     * @param request
     * @return
     */
    private boolean isDataTrue(ReleaseLostFoundTaskRequest request) {

        boolean flag = true;
        if (request.getName().equals("")) {
            mView.onReleaseFailed("请填写物品名称");
            flag = false;
        } else if (request.getPlace().equals("")) {
            mView.onReleaseFailed("请填写地点");
            flag = false;
        }
        return flag;
    }
}
