package cn.abtion.taskgo.data;

import android.app.ActivityManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import java.util.Iterator;
import java.util.List;

import cn.abtion.taskgo.mvp.view.message.adapter.ChatWindowRecAdapter;
import cn.abtion.taskgo.utils.Utility;

import static android.content.Context.ACTIVITY_SERVICE;
import static cn.abtion.taskgo.BuildConfig.DEBUG;
import static cn.abtion.taskgo.TaskGoApplication.appContext;

/**
 * @author FanHongyu.
 * @since 18/2/3 20:34.
 * email fanhongyu@hrsoft.net.
 */

public class ChatHelper {


    /**
     * 初始化环信
     */
    public static void initEM() {
        int pid = android.os.Process.myPid();
        String processName = getProcessAppName(pid);
        if (processName == null || !processName.equalsIgnoreCase(appContext.getPackageName())) {
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        EMOptions options = new EMOptions();
        options.setAutoLogin(false);
        EMClient.getInstance().init(appContext, options);
        EMClient.getInstance().setDebugMode(DEBUG);
    }

    /**
     * processAppName
     *
     * @param pID pid
     * @return name
     */
    private static String getProcessAppName(int pID) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) appContext.getSystemService(ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) iterator.next();
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }




    /**
     * 刷新消息列表
     */
    public static void refresh(ChatWindowRecAdapter recAdapter,RecyclerView recyclerView) {
        if (recAdapter != null) {
            recAdapter.refresh();
        }
        scrollToBottom(recAdapter,recyclerView);
    }

    /**
     * 让最新消息位于最底部
     */
    private static void scrollToBottom(final ChatWindowRecAdapter recAdapter ,final RecyclerView recyclerView) {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.scrollToPosition(recAdapter.getItemCount() - 1);
            }
        }, 200);
    }


    /**
     * 登录环信账号
     */
    public static void loginEM() {

        EMClient.getInstance().login("111111", "111111", new EMCallBack() {
            @Override
            public void onSuccess() {

                Log.i("login", "onSuccess: 登录成功");

            }

            @Override
            public void onError(int code, String error) {

                Log.i("login", "onError: 登录失败，" + error);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }



}
