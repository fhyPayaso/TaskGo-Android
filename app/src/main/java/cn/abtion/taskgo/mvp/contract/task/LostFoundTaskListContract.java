package cn.abtion.taskgo.mvp.contract.task;

import java.util.List;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.request.AcceptLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.response.LostFoundTaskResponse;


/**
 * @author FanHongyu.
 * @since 18/3/7 10:15.
 * email fanhongyu@hrsoft.net.
 */

public interface LostFoundTaskListContract {


    interface Presenter extends BaseContract.Presenter {

        /**
         * 加载物品任务列表
         */
        void loadTaskList();

        /**
         * 接受物品任务
         * @param request
         * @param position
         */
        void acceptTask(AcceptLostFoundTaskRequest request,int position);


        /**
         * 查看物品任务详细信息
         * @param taskId
         */
        void loadTaskInfo(int taskId);
    }

    interface View extends BaseContract.View<Presenter> {

        /**
         * 列表加载成功
         * @param lostTaskList
         * @param foundTaskList
         */
        void onLoadDataSuccess(List<BaseTaskModel> lostTaskList, List<BaseTaskModel> foundTaskList);

        /**
         * 任务接受成功
         * @param position
         */
        void onAcceptSuccess(int position);


        /**
         * 查询详细物品任务信息成功
         * @param response
         */
        void onLoadTaskInfoSuccess(LostFoundTaskResponse response);
    }
}
