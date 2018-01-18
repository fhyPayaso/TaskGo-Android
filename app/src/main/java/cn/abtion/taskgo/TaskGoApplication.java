package cn.abtion.taskgo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.abtion.taskgo.utils.CacheUtil;

/**
 * @author FanHongyu.
 * @since 18/1/17 17:36.
 * email fanhongyu@hrsoft.net.
 */

public class TaskGoApplication extends Application {

    private static TaskGoApplication instance;
    private static List<Activity> activityList = new ArrayList<>();
    private static CacheUtil cacheUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /**
     * 缓存初始化
     */
    public CacheUtil getCacheUtil() {
        if (cacheUtil == null) {
            cacheUtil = CacheUtil.get(instance.getFilesDir());
        }
        return cacheUtil;
    }

    /**
     * 外部获取实例对象
     *
     * @return TaskGoApplication
     */
    public static TaskGoApplication getInstance() {
        return instance;
    }

    private static android.app.Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new android.app
            .Application.ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            activityList.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            activityList.remove(activity);
        }
    };

    /**
     * 移除Activity
     *
     * @param activity act
     */
    public static void removeActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 清除所有Activity
     */
    public void removeAllActivity() {
        for (Activity activity : activityList) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        removeAllActivity();
    }

    /**
     * 退出账户
     */
    public void exitAccount() {

    }


}
