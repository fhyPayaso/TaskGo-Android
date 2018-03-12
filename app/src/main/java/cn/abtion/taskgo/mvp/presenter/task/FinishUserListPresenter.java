package cn.abtion.taskgo.mvp.presenter.task;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.task.FinishUserListContract;
import cn.abtion.taskgo.mvp.model.task.model.SimpleUserInfoModel;
import cn.abtion.taskgo.mvp.model.task.request.FinishLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.FinishUserListRequest;
import cn.abtion.taskgo.mvp.model.task.response.SimpleUserInfoResponse;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * @author fhyPayaso
 * @since 2018/3/10 on 下午11:57
 * fhyPayaso@qq.com
 */
public class FinishUserListPresenter extends BasePresenter<FinishUserListContract.View> implements
        FinishUserListContract.Presenter {


    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public FinishUserListPresenter(FinishUserListContract.View mView) {
        super(mView);
    }

    /**
     * 加载用户userid列表
     *
     * @param request
     */
    @Override
    public void loadFinishUserList(final FinishUserListRequest request) {

        RetrofitFactory
                .getRetrofitService()
                .loadFinishUserList(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<SimpleUserInfoResponse>>() {
                    @Override
                    public void onDataSuccess(ApiResponse<List<SimpleUserInfoResponse>> response) {
                        onLoadListSuccess(response.getData());
                    }
                });
    }


    @Override
    @SuppressWarnings("unchecked")
    public void finishLostFoundTask(FinishLostFoundTaskRequest request, final int position) {


        Log.i(TAG, "finishLostFoundTask: 开始任务完成网络请求");
        
        RetrofitFactory
                .getRetrofitService()
                .finishLostFoundTask(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onDataSuccess(ApiResponse response) {
                        mView.onFinishSuccess(position);
                        Log.i(TAG, "onDataSuccess: 任务完成");
                    }
                });
    }


    private void onLoadListSuccess(List<SimpleUserInfoResponse> responseList) {
        List<SimpleUserInfoModel> modelList = new ArrayList<>();
        for (int i = 0; i < responseList.size(); i++) {
            SimpleUserInfoResponse response = responseList.get(i);
            modelList.add(new SimpleUserInfoModel(response.getAvatar(),response.getUser_name(),response.getUser_id()));
        }
        mView.onLoadUserListSuccess(modelList);
    }
}
