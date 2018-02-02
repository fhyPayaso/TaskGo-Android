package cn.abtion.taskgo.base.adapter;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

/**
 * @author FanHongyu.
 * @since 18/1/30 17:04.
 * email fanhongyu@hrsoft.net.
 */

public abstract class RecyclerScrollListener extends RecyclerView.OnScrollListener{



    private FloatingActionButton mFloatingButton;


    public RecyclerScrollListener() {

    }

    public RecyclerScrollListener(FloatingActionButton floatingButton) {
        mFloatingButton = floatingButton;
    }



    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        // 滑动停止
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            //获取最后一个完全显示的ItemPosition ,角标值
            int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
            int totalItemCount = layoutManager.getItemCount();
            // 判断是否滚动到底部
            if (lastVisibleItem == (totalItemCount - 1)) {
                scrolledToLast();
            }
        }
    }


    /**
     * 动态显示悬浮按钮
     * @param recyclerView
     * @param dx
     * @param dy
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mFloatingButton != null) {
            if (dy < 0) {

                //向下滑动显示按钮
                mFloatingButton.show();
            } else if (dy > 0) {
                //向上滑动隐藏按钮
                mFloatingButton.hide();
            }
        }
    }


    public abstract void scrolledToLast();
}
