package cn.abtion.taskgo.mvp.contract.mine;

import cn.abtion.taskgo.base.contract.BaseContract;

/**
 * @author：lszr on 2018/3/7 10:17
 * @email：1085963811@qq.com
 */
public interface SettingContract {

    interface Presenter extends BaseContract.Presenter {

        /**
         *
         */
        void requestRevealSetting();
    }

    interface View extends BaseContract.View<SettingContract.Presenter> {

        /**
         *
         */
        void onRevealSettingSuccess();

        void onFormatError(String errorMessage);
    }



}
