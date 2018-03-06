package cn.abtion.taskgo.mvp.presenter;

import android.util.Log;

import java.util.List;

import cn.abtion.taskgo.base.data.DataCallBack;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.WaterTaskListContract;
import cn.abtion.taskgo.mvp.model.request.home.WaterTaskResponse;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import cn.abtion.taskgo.utils.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

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
