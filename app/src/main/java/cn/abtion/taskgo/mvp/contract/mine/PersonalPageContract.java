package cn.abtion.taskgo.mvp.contract.mine;

import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.mvp.model.mine.PersonalPageModel;

/**
 * @author：lszr on 2018/3/11 21:04
 * @email：1085963811@qq.com
 */
public interface PersonalPageContract  {
    interface Presenter extends BaseContract.Presenter{
        void requestPersonalInformation(String userId);

        void requestFollowSB(String followerId);

        void requestCancelFollow(String userId);
    }
    interface View extends BaseContract.View<PersonalPageContract.Presenter>{
        void getPersonalInformationsuccess(PersonalPageModel personalPageModel);

        void followSBSuccess();

        void cancelFollowSuccess();
    }
}
