package cn.abtion.taskgo.mvp.model.mine;

/**
 * @author：lszr on 2018/3/11 21:09
 * @email：1085963811@qq.com
 */
public class PersonalPageModel {
    private String name;
    private String avatar;
    private String sex;

    public boolean isIs_following() {
        return is_following;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }

    private String mobile;
    private int followers_count;

    public PersonalPageModel(String name, String avatar, String sex, String mobile, int followers_count, boolean is_following, int followings_count) {
        this.name = name;
        this.avatar = avatar;
        this.sex = sex;
        this.mobile = mobile;
        this.followers_count = followers_count;
        this.is_following = is_following;
        this.followings_count = followings_count;
    }

    private boolean is_following;

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



    public int getFollowers_count() {

        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFollowings_count() {
        return followings_count;
    }

    public void setFollowings_count(int followings_count) {
        this.followings_count = followings_count;
    }

    private int followings_count;

}
