package cn.abtion.taskgo.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.abtion.taskgo.utils.ToastUtil;

/**
 * 自定义轮播图
 *
 * @author FanHongyu.
 * @since 18/1/25 18:48.
 * email fanhongyu@hrsoft.net.
 */


public class BannerFlipper extends ViewFlipper implements View.OnTouchListener {


    private static final String TAG = "BannerFlipper";

    /**
     * 轮播速度
     */
    public static final int FLIPPER_ACTION_SPEED = 200;

    /**
     * 间隔时间
     */
    public static final int FLIPPER_TIME_INTERVAL = 5000;

    /**
     * 最小滑动距离
     */
    public static final int FLIPPER_MIN_DISTANCE = 100;


    private Context mContext;
    private float downX = 0;


    public BannerFlipper(Context context) {
        super(context);
        this.mContext = context;
        initFlipper();
        Log.i(TAG, "BannerFlipper: 绑定成功");
    }

    public BannerFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initFlipper();
    }

    /**
     * 初始化轮播图
     */
    private void initFlipper() {

        //设置右进左出的动画
        this.setInAnimation(inFromRightAnimation());
        this.setOutAnimation(outToLeftAnimation());
        //设置轮播间隔时间
        this.setFlipInterval(FLIPPER_TIME_INTERVAL);
        this.startFlipping();
    }


    /**
     * 加载url类型图片
     *
     * @param imageList 图片url数组
     */
    public void setImage(List<String> imageList) {
        for (int i = 0; i < imageList.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            Glide.with(mContext).load((String)imageList.get(i)).into(imageView);
            this.addView(imageView);
        }
    }

    /**
     * 加载本地图片
     * @param imageList 本地图片id数组
     */
    public void setDrawableImage(List<Integer> imageList) {

        for (int i = 0; i < imageList.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource((Integer) imageList.get(i));
            this.addView(imageView);
        }
    }


    /**
     * 实现触摸事件接口
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            //触摸事件(记录触摸坐标，停止轮播)
            case MotionEvent.ACTION_DOWN:
                downX = event.getRawX();
                this.stopFlipping();
                break;
            //离开事件
            case MotionEvent.ACTION_UP:
                //记录离开时坐标
                float upX = event.getRawX();
                if (downX - upX > FLIPPER_MIN_DISTANCE) {
                    //从右向左滑动
                    this.setInAnimation(inFromRightAnimation());
                    this.setOutAnimation(outToLeftAnimation());
                    this.showNext();
                    this.startFlipping();

                } else if (upX - downX > FLIPPER_MIN_DISTANCE) {
                    //从左向右滑动
                    this.setOutAnimation(outToRightAnimation());
                    this.setInAnimation(inFromLeftAnimation());
                    this.showPrevious();
                    //从左向右滑之后，重新设置动画效果
                    this.startFlipping();
                    this.setInAnimation(inFromRightAnimation());
                    this.setOutAnimation(outToLeftAnimation());
                } else if (upX == downX) {

                    ToastUtil.showToast("点击了轮播图");
                }
                break;
            default:
                break;
        }
        return true;
    }


    /**
     * 定义从右侧进入的动画效果
     */
    protected Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(FLIPPER_ACTION_SPEED);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }


    /**
     * 定义从右侧退出时的动画效果
     */
    protected Animation outToRightAnimation() {
        Animation outToRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outToRight.setDuration(FLIPPER_ACTION_SPEED);
        outToRight.setInterpolator(new AccelerateInterpolator());
        return outToRight;
    }


    /**
     * 定义从左侧进入的动画效果
     */
    protected Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(FLIPPER_ACTION_SPEED);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    /**
     * 定义从左侧退出的动画效果
     */
    protected Animation outToLeftAnimation() {
        Animation outToLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outToLeft.setDuration(FLIPPER_ACTION_SPEED);
        outToLeft.setInterpolator(new AccelerateInterpolator());
        return outToLeft;
    }

}
