package cn.abtion.taskgo.mvp.contract.task;

import java.util.List;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.request.FinishLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.response.LostFoundTaskResponse;

/**
 * @author FanHongyu.
 * @since 18/3/8 19:18.
 * email fanhongyu@hrsoft.net.
 */

public interface MyTaskListContract {


    interface Presenter extends BaseContract.Presenter {

        /**
         * 加载我接受的任务
         * @param status 状态码 物品任务：0代表未完成，1代表已完成
         */
        void loadMyAcceptTaskList(int status);

        /**
         * 我发布的任务
         * @param status 状态码 水任务：0未接受，1被接受，2已完成，物品任务：0未完成，1已完成
         */
        void loadMyReleaseTaskList(int status);

        /**
         * 完成水任务
         * @param taskId
         * @param position 列表中位置
         */
        void finishWaterTask(int taskId,int position);


        /**
         * 查看物品任务详细信息
         * @param taskId
         */
        void loadTaskInfo(int taskId);
    }

    interface View extends BaseContract.View<Presenter> {

        /**
         * 列表加载成功
         * @param taskModelList
         */
        void onLoadSuccess(List<BaseTaskModel> taskModelList);

        /**
         * 任务完成成功
         * @param position
         */
        void onFinishSuccess(int position);

        /**
         * 查询详细物品任务信息成功
         * @param response
         */
        void onLoadTaskInfoSuccess(LostFoundTaskResponse response);
    }
}
