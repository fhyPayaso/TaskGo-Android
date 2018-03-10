package cn.abtion.taskgo.mvp.model.task.response;

/**
 * @author fhyPayaso
 * @since 2018/3/11 on 上午12:13
 * fhyPayaso@qq.com
 */
public class UserInfoReponse {



    private String name;
    private String avatar;
    private String sex;
    private String mobile;
    private String followers_count;
    private String followings_count;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(String followers_count) {
        this.followers_count = followers_count;
    }

    public String getFollowings_count() {
        return followings_count;
    }

    public void setFollowings_count(String followings_count) {
        this.followings_count = followings_count;
    }
}
