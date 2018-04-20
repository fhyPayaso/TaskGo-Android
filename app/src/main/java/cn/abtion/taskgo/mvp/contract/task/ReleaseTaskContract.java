package cn.abtion.taskgo.mvp.contract.task;

import java.util.List;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.task.model.ChooseCardModel;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseWaterTaskRequest;

/**
 * @author FanHongyu.
 * @since 18/3/6 19:04.
 * email fanhongyu@hrsoft.net.
 */

public interface ReleaseTaskContract {

    interface Presenter extends BaseContract.Presenter {

        void loadCardInformation();

        void releaseWaterTask(ReleaseWaterTaskRequest request);

        void releaseLostFoundTask(ReleaseLostFoundTaskRequest request);
    }

    interface View extends BaseContract.View<Presenter> {


        /**
         * 加载卡片信息成功回调
         *
         * @param models
         */
        void loadCardInfoSuccess(List<ChooseCardModel> models);

        void onReleaseSuccess();

        void onReleaseFailed(String errorMessage);
    }
}
