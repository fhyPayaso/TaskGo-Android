package cn.abtion.taskgo.mvp.presenter.task;

import java.util.ArrayList;
import java.util.List;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.task.FinishUserListContract;
import cn.abtion.taskgo.mvp.model.task.model.SimpleUserInfoModel;
import cn.abtion.taskgo.mvp.model.task.request.FinishLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.FinishUserListRequest;
import cn.abtion.taskgo.mvp.model.task.response.UserInfoReponse;
import cn.abtion.taskgo.network.BaseObserver;
import cn.abtion.taskgo.network.response.ApiResponse;
import cn.abtion.taskgo.network.retrofit.RetrofitFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author fhyPayaso
 * @since 2018/3/10 on 下午11:57
 * fhyPayaso@qq.com
 */
public class FinishUserListPresenter extends BasePresenter<FinishUserListContract.View> implements FinishUserListContract.Prsenter {


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
                .subscribe(new BaseObserver<List<Integer>>() {
                    @Override
                    public void onDataSuccess(ApiResponse<List<Integer>> response) {
                        dealUerIdList(response.getData());
                    }
                });
    }


    @Override
    @SuppressWarnings("unchecked")
    public void finishLostFoundTask(FinishLostFoundTaskRequest request, final int position) {
        RetrofitFactory
                .getRetrofitService()
                .finishLostFoundTask(request)
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
     * 将UserId转化为用户信息列表
     *
     * @param userIdList
     */
    private void dealUerIdList(List<Integer> userIdList) {

        List<SimpleUserInfoModel> modelList = new ArrayList<>();
        for (int i = 0; i < userIdList.size(); i++) {
            loadUserInfo(userIdList.get(i), modelList);
        }
        mView.onLoadUserListSuccess(modelList);
    }

    private void loadUserInfo(final int userId, final List<SimpleUserInfoModel> modelList) {

        RetrofitFactory
                .getRetrofitService()
                .loadSimpleUserInfo(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UserInfoReponse>() {
                    @Override
                    public void onDataSuccess(ApiResponse<UserInfoReponse> response) {
                        modelList.add(new SimpleUserInfoModel(response.getData().getAvatar()
                                , response.getData().getName()
                                , String.valueOf(userId)));
                    }
                });
    }
}
