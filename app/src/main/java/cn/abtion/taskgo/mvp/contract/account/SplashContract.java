package cn.abtion.taskgo.mvp.contract.account;

import cn.abtion.taskgo.base.contract.BaseContract;

/**
 * @author FanHongyu.
 * @since 18/3/11 21:49.
 * email fanhongyu@hrsoft.net.
 */

public interface SplashContract {


    interface Presenter extends BaseContract.Presenter {

        void checkToken(String token);
    }

    interface View extends BaseContract.View<Presenter> {

        /**
         * 有效token
         */
        void effectiveToken();

        /**
         * 无效token
         */
        void invalidToken();
    }
}
