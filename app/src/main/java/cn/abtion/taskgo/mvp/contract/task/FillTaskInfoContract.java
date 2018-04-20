package cn.abtion.taskgo.mvp.contract.task;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.task.model.LostFoundTaskInfoModel;
import cn.abtion.taskgo.mvp.model.task.model.WaterTaskInfoModel;

/**
 * @author FanHongyu.
 * @since 18/4/20 12:22.
 * email fanhongyu@hrsoft.net.
 */

public interface FillTaskInfoContract {


    interface Presenter extends BaseContract.Presenter {

        void checkWaterTaskInfo(WaterTaskInfoModel model);

        void checkLostFoundTaskInfo(LostFoundTaskInfoModel model);

    }

    interface View extends BaseContract.View<Presenter> {

        void onDataTrue();

        void onDataFalse(String error);
    }
}
