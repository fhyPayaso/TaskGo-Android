package cn.abtion.taskgo.mvp.presenter.task;

import java.util.List;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.task.LostFoundTaskListContract;
import cn.abtion.taskgo.mvp.model.request.home.AcceptLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.request.home.LostFindTaskModel;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
                .subscribe(new BaseObserver<List<LostFindTaskModel>>() {
                    @Override
                    public void onDataSuccess(ApiResponse<List<LostFindTaskModel>> response) {
                        mView.onLoadDataSuccess(response.getData());
                    }
                });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void acceptTask(AcceptLostFoundTaskRequest request) {

        RetrofitFactory
                .getRetrofitService()
                .acceptLostFoundTask(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onDataSuccess(ApiResponse response) {
                        mView.onAcceptSuccess();
                    }
                });
    }
}

