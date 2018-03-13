package cn.abtion.taskgo.mvp.model.task.model;

/**
 * @author fhyPayaso
 * @since 2018/2/3 on 上午10:58
 * fhyPayaso@qq.com
 */
public class BaseTaskModel {




    /**
     * 0代表水任务,1代表物品任务
     */
    private int taskType;
    private int taskId;
    private String avatarUrl;
    private String username;
    private String releaseTime;
    private String mainValue;
    private String subTitle;
    private String userId;


    public BaseTaskModel(int taskType, String avatarUrl, String username, String releaseTime, String mainValue,
                         String subTitle,String userId) {
        this.taskType = taskType;
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.releaseTime = releaseTime;
        this.mainValue = mainValue;
        this.subTitle = subTitle;
        this.userId = userId;
    }

    public BaseTaskModel(int taskType,String avatarUrl, String username, String releaseTime, String mainValue,String userId) {

        this.taskType = taskType;
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.releaseTime = releaseTime;
        this.mainValue = mainValue;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
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

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getMainValue() {
        return mainValue;
    }

    public void setMainValue(String mainValue) {
        this.mainValue = mainValue;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
