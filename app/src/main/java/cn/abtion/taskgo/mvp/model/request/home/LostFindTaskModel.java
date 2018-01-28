package cn.abtion.taskgo.mvp.model.request.home;

/**
 * @author fhyPayaso
 * @since 2018/1/27 on 上午12:28
 * fhyPayaso@qq.com
 */
public class LostFindTaskModel {

    private String avatarUrl;
    private String username;
    private String thingsName;
    private String releaseTime;


    public LostFindTaskModel(String avatarUrl, String username, String thingsName, String releaseTime) {
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.thingsName = thingsName;
        this.releaseTime = releaseTime;
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

    public String getThingsName() {
        return thingsName;
    }

    public void setThingsName(String thingsName) {
        this.thingsName = thingsName;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
}
