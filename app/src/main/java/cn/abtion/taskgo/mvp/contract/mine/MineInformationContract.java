package cn.abtion.taskgo.mvp.contract.mine;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.mine.MineInformationModel;

/**
 * @author：lszr on 2018/3/7 10:17
 * @email：1085963811@qq.com
 */
public interface MineInformationContract {

    interface Presenter extends BaseContract.Presenter {

        /**
         *
         */
        void requestMineInformation();


    }

    interface View extends BaseContract.View<MineInformationContract.Presenter> {

        /**
         *
         */
        void onMineInformationRequestSuccess(MineInformationModel mineInformationModel);


    }



}
