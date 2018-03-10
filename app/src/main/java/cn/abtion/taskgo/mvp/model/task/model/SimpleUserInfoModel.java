package cn.abtion.taskgo.mvp.model.task.model;

/**
 * @author FanHongyu.
 * @since 18/3/10 17:58.
 * email fanhongyu@hrsoft.net.
 */

public class SimpleUserInfoModel {

    private String avatarUrl;
    private String username;
    private String taskId;

    public SimpleUserInfoModel(String avatarUrl, String username) {
        this.avatarUrl = avatarUrl;
        this.username = username;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
