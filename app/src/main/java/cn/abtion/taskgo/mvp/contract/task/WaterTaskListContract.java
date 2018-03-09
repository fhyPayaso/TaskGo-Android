package cn.abtion.taskgo.mvp.contract.task;

import java.util.List;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.mvp.model.task.response.WaterTaskResponse;

/**
 * @author FanHongyu.
 * @since 18/3/6 13:38.
 * email fanhongyu@hrsoft.net.
 */

public interface WaterTaskListContract {


    interface Presenter extends BaseContract.Presenter {


        /**
         * 加载水任务列表
         */
        void loadWaterTaskList();


        /**
         * 接受一个水任务
         */
        void acceptWaterTask(int taskId ,int position);

        /**
         * 接受全部水任务
         */
        void acceptAllWaterTask();

    }

    interface View extends BaseContract.View<Presenter> {


        /**
         * 列表加载成功
         */
        void onLoadDataSuccess(List<BaseTaskModel> modelList);

        /**
         * 接受单个任务成功
         */
        void onAcceptSuccess(int position);


        /**
         * 接受全部任务成功
         */
        void onAcceptAllSuccess();

    }
}
