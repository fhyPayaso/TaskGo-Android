package cn.abtion.taskgo.mvp.contract.mine;

import java.util.List;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.mine.MyFollowModel;

/**
 * @author：lszr on 2018/3/11 15:43
 * @email：1085963811@qq.com
 */
public interface MyFollowContract {
    interface Presenter extends BaseContract.Presenter{

        void requestFollowList();


    }
    interface View extends BaseContract.View<MyFollowContract.Presenter>{

        void getListSuccess(List<MyFollowModel> list);

    }
}
