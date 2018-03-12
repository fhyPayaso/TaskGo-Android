package cn.abtion.taskgo.mvp.contract.mine;

import cn.abtion.taskgo.base.contract.BaseContract;

/**
 * @author：lszr on 2018/3/6 21:10
 * @email：1085963811@qq.com
 */
public interface ChangePasswordContract {
    interface Presenter extends BaseContract.Presenter {

        /**
         * P层进行修改密码
         */
        void requestChangePassword(String oldPassword,String newPassword,String repeatPassword);
    }


    interface View extends BaseContract.View<ChangePasswordContract.Presenter> {

        /**
         * 通知V层修改密码成功
         */
        void onChangePasswordSuccess();

        void onFormatError(String errorMessage);
    }


}
