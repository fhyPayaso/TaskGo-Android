package cn.abtion.taskgo.mvp.presenter.task;

import java.util.ArrayList;
import java.util.List;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.task.ReleaseTaskContract;

import cn.abtion.taskgo.mvp.model.task.model.ChooseCardModel;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseWaterTaskRequest;

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


    @Override
    public void loadCardInformation() {

        List<ChooseCardModel> modelList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            ChooseCardModel chooseCardModel = new ChooseCardModel();
            switch (i) {
                case 0:
                    chooseCardModel.setCardImgUrl("https://timgsa.baidu" +
                            ".com/timg?image&quality=80&size=b9999_10000&sec=1524211166428&di" +
                            "=66aac07e1d134dab3b77d77c76a5632a&imgtype=0&src=http%3A%2F%2Fimg4.duitang" +
                            ".com%2Fuploads%2Fitem%2F201501%2F15%2F20150115234911_S4xLM.jpeg");

                    break;
                case 1:
                    chooseCardModel.setCardImgUrl("https://timgsa.baidu" +
                            ".com/timg?image&quality=80&size=b9999_10000&sec=1524237277235&di" +
                            "=58cea7e9760f3c32c2433707c17e93c5&imgtype=0&src=http%3A%2F%2Fimg3.duitang" +
                            ".com%2Fuploads%2Fitem%2F201511%2F29%2F20151129194142_hZzMP.jpeg");

                    break;


                case 2:

                    chooseCardModel.setCardImgUrl("https://timgsa.baidu" +
                            ".com/timg?image&quality=80&size=b10000_10000&sec=1524227238&di" +
                            "=80fbec11a0a02090ce37c16f2b4217cb&src=http://f6.topitme.com/6/e2/8d/11311082682618de26o" +
                            ".jpg");

                    break;

                default:

                    break;
            }

            chooseCardModel.setHaveNumber(i + 10);
            chooseCardModel.setChooseNumber(0);
            modelList.add(chooseCardModel);
        }
        mView.loadCardInfoSuccess(modelList);
    }

    /**
     * 发布水任务网络请求
     *
     * @param request
     */
    @Override
    @SuppressWarnings("unchecked")
    public void releaseWaterTask(ReleaseWaterTaskRequest request) {

//        if (isDataTrue(request)) {
//            RetrofitFactory
//                    .getRetrofitService()
//                    .releaseWaterTask(request)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new BaseObserver() {
//                        @Override
//                        public void onDataSuccess(ApiResponse response) {
//                            mView.onReleaseSuccess();
//                        }
//                    });
//        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void releaseLostFoundTask(ReleaseLostFoundTaskRequest request) {

//        if (isDataTrue(request)) {
//            RetrofitFactory
//                    .getRetrofitService()
//                    .releaseLostFoundTask(request)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new BaseObserver() {
//                        @Override
//                        public void onDataSuccess(ApiResponse response) {
//                            mView.onReleaseSuccess();
//                        }
//                    });
//        }
    }
}
