package cn.abtion.taskgo.mvp.contract.task;

import java.util.List;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.request.home.AcceptLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.request.home.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.request.home.LostFindTaskModel;

/**
 * @author FanHongyu.
 * @since 18/3/7 10:15.
 * email fanhongyu@hrsoft.net.
 */

public interface LostFoundTaskListContract {


    interface Presenter extends BaseContract.Presenter {

        void loadTaskList();

        void acceptTask(AcceptLostFoundTaskRequest request);
    }

    interface View extends BaseContract.View<Presenter> {

        void onLoadDataSuccess(List<LostFindTaskModel> taskModelList);

        void onAcceptSuccess();

    }
}
