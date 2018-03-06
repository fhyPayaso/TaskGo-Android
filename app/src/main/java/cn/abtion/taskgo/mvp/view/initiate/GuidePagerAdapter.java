package cn.abtion.taskgo.mvp.view.initiate;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/3/5 15:34.
 * email fanhongyu@hrsoft.net.
 */

public class GuidePagerAdapter extends PagerAdapter{

    private List<View> pagerList;

    public GuidePagerAdapter(List<View> pagerList) {
        this.pagerList = pagerList;
    }

    /**
     * 返回页面个数
     * @return
     */
    @Override
    public int getCount() {

        if(pagerList!=null) {
            return pagerList.size();
        }
        return 0;
    }

    /**
     * 判断对象是否生成界面
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    /**
     * 初始化界面
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pagerList.get(position));
        return pagerList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pagerList.get(position));
    }
}
