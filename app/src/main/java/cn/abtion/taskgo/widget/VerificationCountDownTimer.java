package cn.abtion.taskgo.widget;

import android.os.CountDownTimer;

/**
 * @author heaijia
 * @since 2018/2/2 上午11:33
 * email 549044363@qq.com
 */


public class VerificationCountDownTimer extends CountDownTimer {


    public static long mcurMillis =0;
    public static boolean FLAG_FIRST_IN =true;

    /**
     * 类中的构造函数
     * @param millisInFuture
     * @param countDownInterval
     */
    public VerificationCountDownTimer(long millisInFuture,long countDownInterval){
        super(millisInFuture, countDownInterval);
    }


    /**
     * 当前任务每完成一次倒计时间隔时间时回调
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {

    }


    /**
     * 当前任务完成的时候回调
     */
    @Override
    public void onFinish() {

    }


    public void timerStart(boolean onClick){

        if(onClick) {
            VerificationCountDownTimer.mcurMillis= System.currentTimeMillis();
        }
        VerificationCountDownTimer.FLAG_FIRST_IN=false;
        this.start();
    }
}
