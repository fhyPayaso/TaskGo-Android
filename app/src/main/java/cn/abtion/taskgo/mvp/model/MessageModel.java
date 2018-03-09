package cn.abtion.taskgo.mvp.model;

/**
 * @author FanHongyu.
 * @since 18/2/3 17:51.
 * email fanhongyu@hrsoft.net.
 */

public class MessageModel {


    private String avatarUrl;
    private String userName;
    private String lastMessage;
    private String lastTime;
    private int unReadNumber = 0;

    public MessageModel(String avatarUrl, String userName, String lastMessage, String lastTime) {
        this.avatarUrl = avatarUrl;
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.lastTime = lastTime;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }


    public int getUnReadNumber() {
        return unReadNumber;
    }

    public void setUnReadNumber(int unReadNumber) {
        this.unReadNumber = unReadNumber;
    }
}
