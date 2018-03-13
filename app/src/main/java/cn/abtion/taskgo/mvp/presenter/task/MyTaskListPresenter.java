package cn.abtion.taskgo.mvp.presenter.task;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.task.MyTaskListContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.request.FinishLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.response.BaseTaskResponse;
import cn.abtion.taskgo.mvp.model.task.response.LostFoundTaskResponse;
import cn.abtion.taskgo.mvp.model.task.response.WaterTaskResponse;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * @author FanHongyu.
 * @since 18/3/8 19:27.
 * email fanhongyu@hrsoft.net.
 */

public class MyTaskListPresenter extends BasePresenter<MyTaskListContract.View> implements MyTaskListContract.Presenter {


    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public MyTaskListPresenter(MyTaskListContract.View mView) {
        super(mView);
    }

    @Override
    public void loadMyAcceptTaskList(int status) {


        RetrofitFactory
                .getRetrofitService()
                .loadMyAcceptTask(status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<BaseTaskResponse>>() {
                    @Override
                    public void onDataSuccess(ApiResponse<List<BaseTaskResponse>> response) {
                        dealSuccessData(response.getData());
                    }
                });
    }

    @Override
    public void loadMyReleaseTaskList(int status) {

        RetrofitFactory
                .getRetrofitService()
                .loadMyReleaseTask(status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<BaseTaskResponse>>() {
                    @Override
                    public void onDataSuccess(ApiResponse<List<BaseTaskResponse>> response) {
                        dealSuccessData(response.getData());
                    }
                });
    }



    @Override
    public void loadTaskInfo(int taskId) {

        RetrofitFactory
                .getRetrofitService()
                .loadTaskInfo(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LostFoundTaskResponse>() {
                    @Override
                    public void onDataSuccess(ApiResponse<LostFoundTaskResponse> response) {
                        mView.onLoadTaskInfoSuccess(response.getData());
                    }
                });
    }

    /**
     * 完成水任务
     * @param taskId
     * @param position
     */
    @Override
    @SuppressWarnings("unchecked")
    public void finishWaterTask(int taskId, final int position) {

        RetrofitFactory
                .getRetrofitService()
                .finishWaterTask(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onDataSuccess(ApiResponse response) {
                        mView.onFinishSuccess(position);
                    }
                });
    }


    /**
     * 对返回成功的数据进行处理
     * @param responseList
     */
    private void dealSuccessData(List<BaseTaskResponse> responseList) {

        List<BaseTaskModel> modelList = new ArrayList<>();

        for (int i=0;i<responseList.size();i++) {

            BaseTaskResponse response = responseList.get(i);
            BaseTaskModel model;

            //地址为null说明是物品任务，否则为水任务
            if(response.getAddress() == null) {

                model = new BaseTaskModel(1
                        ,response.getAvatar()
                        ,response.getUser_name()
                        ,response.getCreated_at()
                        ,response.getName()
                        ,String.valueOf(response.getUser_id()));
            } else {

                String type = response.getType() == 0 ? "自取":"送水上门";

                model = new BaseTaskModel(0
                        ,response.getAvatar()
                        ,response.getUser_name()
                        ,response.getCreated_at()
                        ,response.getAddress()
                        ,type
                        ,String.valueOf(response.getUser_id()));
            }
            model.setTaskId(response.getId());
            modelList.add(model);
        }
        mView.onLoadSuccess(modelList);
    }
}
