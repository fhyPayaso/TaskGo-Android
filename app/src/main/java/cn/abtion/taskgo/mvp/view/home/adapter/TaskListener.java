package cn.abtion.taskgo.mvp.view.home.adapter;

/**
 * 任务item点击事件接口
 *
 * @author FanHongyu.
 * @since 18/1/27 16:20.
 * email fanhongyu@hrsoft.net.
 */

public interface TaskListener {

    /**
     * 点击头像回调事件
     * @param position item位置
     */
    void onClickAvatar(int position);

    /**
     * 点击接受按钮回调事件
     * @param position item位置
     */
    void onClickAccept(int position);

}
