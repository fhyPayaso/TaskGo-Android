package cn.abtion.taskgo.base.contract;

/**
 * @author FanHongyu.
 * @since 18/1/16 15:48.
 * email fanhongyu@hrsoft.net.
 */

public interface BaseContract {


    interface Presenter {

        /**
         * 销毁
         */
        void destroy();

    }

    interface View<T extends Presenter> {

        /**
         * V层和P层的基础绑定
         * @param presenter
         */
        void setPresenter(T presenter);
    }

}
