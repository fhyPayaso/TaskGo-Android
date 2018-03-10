package cn.abtion.taskgo.mvp.contract.account;

import cn.abtion.taskgo.base.contract.BaseContract;

/**
 * @author：lszr on 2018/3/6 20:04
 * @email：1085963811@qq.com
 */
public interface UpdatePasswordContract {

    interface Presenter extends BaseContract.Presenter {

        /**
         * P层进行修改密码
         */
        void requestUpdatePassword(String phone,String password);
    }

    interface View extends BaseContract.View<UpdatePasswordContract.Presenter> {

        /**
         * 通知V层修改密码成功
         */
        void onUpdatePasswordSuccess();
    }
}
