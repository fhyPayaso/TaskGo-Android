package cn.abtion.taskgo.mvp.contract.task;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.task.request.FinishUserListRequest;

/**
 * @author FanHongyu.
 * @since 18/3/10 18:38.
 * email fanhongyu@hrsoft.net.
 */

public interface FinishUserListContract {


    interface Prsenter extends BaseContract.Presenter {


        void loadFinishUserList(FinishUserListRequest request);

        void loadUserInfo();



    }


    interface View extends BaseContract.View<Prsenter> {

    }


}
