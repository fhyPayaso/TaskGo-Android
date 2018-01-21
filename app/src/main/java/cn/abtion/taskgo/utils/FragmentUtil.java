package cn.abtion.taskgo.utils;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import cn.abtion.taskgo.base.activity.BaseActivity;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 下午2:59
 * fhyPayaso@qq.com
 */
public class FragmentUtil {

    /**
     * 添加Fragment
     */
    public static void addFragment(BaseActivity context, int viewId, Fragment fragment, @Nullable String tag) {
        context.getSupportFragmentManager()
                .beginTransaction()
                .add(viewId, fragment, tag)
                .commit();

    }

    /**
     * 替换Fragment
     */
    public static void replaceFragment(BaseActivity context, int viewId, Fragment fragment, @Nullable String tag) {
        context.getSupportFragmentManager()
                .beginTransaction()
                .replace(viewId, fragment, tag)
                .commit();
    }

    /**
     * 隐藏Fragment
     */
    public static void hideFragment(BaseActivity context, Fragment fragment) {
        context.getSupportFragmentManager()
                .beginTransaction()
                .hide(fragment)
                .commit();
    }

    /**
     * 展示Fragment
     */
    public static void showFragment(BaseActivity context, Fragment fragment) {
        context.getSupportFragmentManager()
                .beginTransaction()
                .show(fragment)
                .commit();
    }

}
