package cn.abtion.taskgo.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import cn.abtion.taskgo.TaskGoApplication;

/**
 * @author FanHongyu.
 * @since 18/1/16 14:39.
 * email fanhongyu@hrsoft.net.
 */

public class ToastUtil {


    /** toast显示时间 */
    private static final int DURATION = Toast.LENGTH_SHORT;
    private static final boolean IS_SHOW_ERROR_CODE = true;

    public static void showToast(final String msg) {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TaskGoApplication.getInstance(), msg, DURATION).show();
            }
        });
    }

    public static void showToast(@StringRes final int resId) {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TaskGoApplication.getInstance(), resId, DURATION).show();
            }
        });
    }

    public static void showToast(String msg, int... errorCode) {
        if (IS_SHOW_ERROR_CODE) {
            for (int anErrorCode : errorCode) {
                msg = msg + "-" + anErrorCode;
            }
        }
        showToast(msg);
    }

    public static void showToast(@StringRes int resId, int... errorCode) {
        String msg = TaskGoApplication.getInstance().getResources().getString(resId);
        if (IS_SHOW_ERROR_CODE) {
            for (int anErrorCode : errorCode) {
                msg = msg + "-" + anErrorCode;
            }
        }
        showToast(msg);
    }

}
