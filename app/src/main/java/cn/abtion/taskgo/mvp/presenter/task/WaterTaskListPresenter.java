package cn.abtion.taskgo.mvp.presenter.task;

import java.util.List;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.task.WaterTaskListContract;
import cn.abtion.taskgo.mvp.model.task.WaterTaskResponse;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author FanHongyu.
 * @since 18/3/6 13:46.
 * email fanhongyu@hrsoft.net.
 */

public class WaterTaskListPresenter extends BasePresenter<WaterTaskListContract.View> implements
        WaterTaskListContract.Presenter {

    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public WaterTaskListPresenter(WaterTaskListContract.View mView) {
        super(mView);
    }

    @Override
    public void loadWaterTaskList() {

        RetrofitFactory
                .getRetrofitService()
                .loadWaterTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<WaterTaskResponse>>() {
                    @Override
                    public void onDataSuccess(ApiResponse<List<WaterTaskResponse>> response) {
                        if (mView != null) {
                            mView.onLoadDataSuccess(response.getData());
                        }
                    }
                });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void acceptWaterTask(int taskId, final int position) {

        RetrofitFactory
                .getRetrofitService()
                .acceptWaterTask(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onDataSuccess(ApiResponse response) {
                        mView.onAcceptSuccess(position);
                    }
                });
    }

    @Override
    public void acceptAllWaterTask() {

    }
}
