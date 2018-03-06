package cn.abtion.taskgo.base.data;

import android.support.annotation.StringRes;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 上午2:46
 * fhyPayaso@qq.com
 */
public interface DataCallBack {

    /**
     * 数据加载失败和成功都关注
     *
     * @param <T> 返回数据类型
     */
    interface Callback<T> extends SuccessCallback<T>, FailedCallback {

    }

    /**
     * 只关注数据加载成功的接口
     *
     * @param <T>
     */
    interface SuccessCallback<T> {
        void onDataLoaded(T t);
    }

    /**
     * 只关注数据加载失败的接口
     */
    interface FailedCallback {
        void onFailedLoaded(@StringRes int error);
    }
}
