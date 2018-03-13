package cn.abtion.taskgo.mvp.presenter.task;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.task.LostFoundTaskListContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.request.AcceptLostFoundTaskRequest;

import cn.abtion.taskgo.mvp.model.task.response.LostFoundTaskResponse;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * @author FanHongyu.
 * @since 18/3/7 10:25.
 * email fanhongyu@hrsoft.net.
 */

public class LostFoundTaskListPresenter extends BasePresenter<LostFoundTaskListContract.View> implements
        LostFoundTaskListContract.Presenter {

    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public LostFoundTaskListPresenter(LostFoundTaskListContract.View mView) {
        super(mView);
    }

    @Override
    public void loadTaskList() {

        RetrofitFactory
                .getRetrofitService()
                .loadLostFoundTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<LostFoundTaskResponse>>() {
                    @Override
                    public void onDataSuccess(ApiResponse<List<LostFoundTaskResponse>> response) {
                        onLoadSuccess(response.getData());
                    }
                });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void acceptTask(AcceptLostFoundTaskRequest request, final int position) {

        RetrofitFactory
                .getRetrofitService()
                .acceptLostFoundTask(request)
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


    private void onLoadSuccess(List<LostFoundTaskResponse> responseList) {

        List<BaseTaskModel> lostTaskList = new ArrayList<>();
        List<BaseTaskModel> foundTaskList = new ArrayList<>();

        for (int i=0 ;i<responseList.size();i++) {

            LostFoundTaskResponse response = responseList.get(i);
            BaseTaskModel model = new BaseTaskModel(1
                    ,response.getPicture_url()
                    ,response.getUser_name()
                    ,response.getCreated_at()
                    ,response.getName()
                    ,String.valueOf(response.getUser_id()));




            model.setTaskId(response.getId());
            //0代表寻物启事，1代表失物招领
            if(response.getType() == 0) {
                lostTaskList.add(model);
            } else if (response.getType() == 1) {
                foundTaskList.add(model);
            }
        }
        mView.onLoadDataSuccess(lostTaskList,foundTaskList);
    }
}

