package cn.abtion.taskgo.mvp.view.message.adapter;

/**
 * @author FanHongyu.
 * @since 18/2/3 17:59.
 * email fanhongyu@hrsoft.net.
 */

public interface MessageItemListener {


    /**
     * 头像点击事件接口
     *
     * @param position
     */
    void onClickAvatar(int position);


    /**
     * 删除点击事件接口
     *
     * @param position
     */
    void onClickDelete(int position);


    /**
     * 进入聊天窗口点击事件接口
     *
     * @param position
     */
    void onClickChat(int position);

}
