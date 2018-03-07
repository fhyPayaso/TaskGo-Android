package cn.abtion.taskgo.mvp.contract.task;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.task.ReleaseWaterTaskRequest;

/**
 * @author FanHongyu.
 * @since 18/3/6 19:04.
 * email fanhongyu@hrsoft.net.
 */

public interface ReleaseWaterTaskContract {

    interface Presenter extends BaseContract.Presenter {

        void releaseWaterTask(ReleaseWaterTaskRequest request);
    }

    interface View extends BaseContract.View<Presenter> {

        void onReleaseSuccess();

        void onReleaseFailed(String errorMessage);
    }
}
