package cn.abtion.taskgo.mvp.model.request.home;

/**
 * @author FanHongyu.
 * @since 18/1/26 16:18.
 * email fanhongyu@hrsoft.net.
 */

public class WaterTaskModel {

    private String avatarUrl;
    private String username;
    private String address;
    private String type;
    private String releaseTime;

    public WaterTaskModel(String avatarUrl, String username, String address, String type, String releaseTime) {
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.address = address;
        this.type = type;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
}
