package cn.abtion.taskgo.mvp.contract.mine;

import java.util.List;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.mine.MyFansListModel;

/**
 * @author：lszr on 2018/3/12 17:53
 * @email：1085963811@qq.com
 */
public interface MyFansContract {
    interface Presenter extends BaseContract.Presenter{
        void requestFollowList();

    }
    interface View extends BaseContract.View<MyFansContract.Presenter>{
        void getFansListSuccess(List<MyFansListModel> list);
    }
}
