package cn.abtion.taskgo.mvp.presenter.task;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.task.WaterTaskListContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.response.WaterTaskResponse;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
                        onLoadWaterTaskSuccess(response.getData());

                        Log.i(TAG, "onDataSuccess: " + response.getData().toString());
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
    @SuppressWarnings("unchecked")
    public void acceptAllWaterTask() {
        RetrofitFactory
                .getRetrofitService()
                .acceptAllWaterTask()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onDataSuccess(ApiResponse response) {
                        mView.onAcceptAllSuccess();
                    }
                });
    }


    /**
     * 水任务列表加载成功回调
     *
     * @param responseList
     */
    private void onLoadWaterTaskSuccess(List<WaterTaskResponse> responseList) {

        List<BaseTaskModel> modelList = new ArrayList<>();
        if (responseList != null) {
            for (int i = 0; i < responseList.size(); i++) {
                WaterTaskResponse response = responseList.get(i);
                String waterTaskType = response.getType() == 0 ? "自取" : "送水上门";
                BaseTaskModel taskModel = new BaseTaskModel(0
                        , response.getAvatar()
                        , response.getUser_name()
                        , response.getCreated_at()
                        , response.getAddress()
                        , waterTaskType
                        , String.valueOf(response.getUser_id()));
                taskModel.setTaskId(response.getId());
                modelList.add(taskModel);
            }
        }

        mView.onLoadDataSuccess(modelList);
    }
}
