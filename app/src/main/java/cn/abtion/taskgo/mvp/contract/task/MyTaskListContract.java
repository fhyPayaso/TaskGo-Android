package cn.abtion.taskgo.mvp.contract.task;

import java.util.List;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.request.FinishLostFoundTaskRequest;

/**
 * @author FanHongyu.
 * @since 18/3/8 19:18.
 * email fanhongyu@hrsoft.net.
 */

public interface MyTaskListContract {


    interface Presenter extends BaseContract.Presenter {


        void loadMyAcceptTaskList(int status);

        void loadMyReleaseTaskList(int status);

        void finishWaterTask(int taskId,int position);

        void finishLostFoundTask(FinishLostFoundTaskRequest request, int position);
    }

    interface View extends BaseContract.View<Presenter> {

        void onLoadSuccess(List<BaseTaskModel> taskModelList);

        void onFinishSuccess(int position);
    }
}
