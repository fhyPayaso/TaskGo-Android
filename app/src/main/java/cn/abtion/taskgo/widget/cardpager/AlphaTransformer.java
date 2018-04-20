package cn.abtion.taskgo.widget.cardpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author FanHongyu.
 * @since 18/4/20 10:56.
 * email fanhongyu@hrsoft.net.
 */

public class AlphaTransformer implements ViewPager.PageTransformer {


    private static final float MIN_SCALE = 0.70f;
    private static final float MINALPHA = 0.5f;

    @Override
    public void transformPage(View page, float position) {


        if (position < -1 || position > 1) {
            page.setAlpha(MINALPHA);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);

        } else if (position <= 1) {
            //【-1,1】
            float scaleFactor = Math.max(MIN_SCALE, (1 - Math.abs(position)));

            //pos由0->-1  scale由1->0.70f
            //pos由1->0  scale由0.70f->1
            float scale = position < 0 ? (1 + 0.3f * position) : (1 - 0.3f * position);
            page.setScaleX(scale);
            page.setScaleY(scale);
            page.setAlpha(MINALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MINALPHA));
        }
    }
}
