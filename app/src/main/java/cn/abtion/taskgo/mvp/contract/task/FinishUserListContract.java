package cn.abtion.taskgo.mvp.contract.task;

import java.util.List;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.task.model.SimpleUserInfoModel;
import cn.abtion.taskgo.mvp.model.task.request.FinishLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.model.task.request.FinishUserListRequest;

/**
 * @author FanHongyu.
 * @since 18/3/10 18:38.
 * email fanhongyu@hrsoft.net.
 */

public interface FinishUserListContract {


    interface Presenter extends BaseContract.Presenter {

        /**
         * 加载待选完成者列表
         * @param request
         */
        void loadFinishUserList(FinishUserListRequest request);

        /**
         * 完成物品任务
         * @param request
         * @param position
         */
        void finishLostFoundTask(FinishLostFoundTaskRequest request, int position);
    }


    interface View extends BaseContract.View<Presenter> {


        void onLoadUserListSuccess(List<SimpleUserInfoModel> modelList);

        void onFinishSuccess(int position);

    }
}
