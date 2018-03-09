package cn.abtion.taskgo.mvp.contract.task;

import java.util.List;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.request.AcceptLostFoundTaskRequest;


/**
 * @author FanHongyu.
 * @since 18/3/7 10:15.
 * email fanhongyu@hrsoft.net.
 */

public interface LostFoundTaskListContract {


    interface Presenter extends BaseContract.Presenter {

        void loadTaskList();

        void acceptTask(AcceptLostFoundTaskRequest request,int position);
    }

    interface View extends BaseContract.View<Presenter> {

        void onLoadDataSuccess(List<BaseTaskModel> lostTaskList, List<BaseTaskModel> foundTaskList);

        void onAcceptSuccess(int position);

    }
}
