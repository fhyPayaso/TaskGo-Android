package cn.abtion.taskgo.mvp.view.account;

import android.os.CountDownTimer;

/**
 * Created by heaijia on 2018/1/28.
 */

public class VerificationCountDownTimer extends CountDownTimer {


    public static long curMillis =0;
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
            VerificationCountDownTimer.curMillis= System.currentTimeMillis();
        }
        VerificationCountDownTimer.FLAG_FIRST_IN=false;
        this.start();
    }
}
