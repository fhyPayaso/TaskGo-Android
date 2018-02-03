package cn.abtion.taskgo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ViewFlipper;

import cn.abtion.taskgo.utils.ToastUtil;

/**
 * 轮播图封装
 *
 * @author FanHongyu.
 * @since 18/1/30 19:53.
 * email fanhongyu@hrsoft.net.
 */

public class BannerPager extends ViewFlipper implements View.OnTouchListener {

    float down;
    private OnBannerClick mOnBannerClick;

    public BannerPager(Context context) {
        super(context);
        initBanner();
    }

    public BannerPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBanner();
    }


    public void initBanner() {

        this.setOnTouchListener(this);
        this.setInAnimation(inFromRightAnimation());
        this.setOutAnimation(outToLeftAnimation());
        this.setFlipInterval(3000);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        this.getParent().requestDisallowInterceptTouchEvent(true);
        float up = 0;
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                down = event.getRawX();
                this.stopFlipping();
                break;
            case MotionEvent.ACTION_UP:

                up = event.getRawX();

                if (down - up > 100) {

                    this.setInAnimation(inFromRightAnimation());
                    this.setOutAnimation(outToLeftAnimation());
                    this.showNext();
                    this.startFlipping();

                } else if (up - down > 100) {

                    this.setOutAnimation(outToRightAnimation());
                    this.setInAnimation(inFromLeftAnimation());
                    this.showPrevious();

                    //从左向右滑之后，重新设置动画效果
                    this.startFlipping();
                    this.setInAnimation(inFromRightAnimation());
                    this.setOutAnimation(outToLeftAnimation());
                } else {
                    mOnBannerClick.onBannerClick(this.getDisplayedChild());
                    this.showNext();
                    this.startFlipping();
                }
                break;
            default:
                break;
        }


        return true;
    }


    public interface OnBannerClick {

        void onBannerClick(int position);
    }

    public void setOnBannerClick(OnBannerClick onBannerClick) {
        mOnBannerClick = onBannerClick;
    }


    /**
     * 定义从右侧进入的动画效果
     *
     * @return
     */
    protected Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(200);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    /**
     * 定义从左侧退出的动画效果
     *
     * @return
     */
    protected Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(200);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    /**
     * 定义从左侧进入的动画效果
     *
     * @return
     */
    protected Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(200);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    /**
     * 定义从右侧退出时的动画效果
     *
     * @return
     */
    protected Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(200);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }
}
