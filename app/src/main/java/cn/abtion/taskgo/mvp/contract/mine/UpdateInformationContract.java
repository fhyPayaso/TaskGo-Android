package cn.abtion.taskgo.mvp.contract.mine;

import cn.abtion.taskgo.base.contract.BaseContract;

/**
 * @author：lszr on 2018/3/9 19:05
 * @email：1085963811@qq.com
 */
public interface UpdateInformationContract {

    interface Presenter extends BaseContract.Presenter{

        void requestUpdateInformation(String name,String avater,String sex,String birth);

    }
    interface View extends BaseContract.View<UpdateInformationContract.Presenter>{

        void updateInformationSuccess();


    }

}
