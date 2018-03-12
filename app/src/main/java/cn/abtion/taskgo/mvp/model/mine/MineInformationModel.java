package cn.abtion.taskgo.mvp.model.mine;

/**
 * @author：lszr on 2018/3/7 10:42
 * @email：1085963811@qq.com
 */
public class MineInformationModel {
    private String name;
    private String mobile;
    private String avatar;
    private String sex;
    private String wechat_id;
    private String qq_id;
    private String birth;
    private int status;
    private String stuwithcard_pic;
    private String id_pic;
    private String stucard_pic;
    private int level;
    private int exp;
    private int followers_count;
    private int followings_count;

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

    public MineInformationModel(String name, String mobile, String avatar, String sex, String wechat_id, String qq_id, String birth, int status, String stuwithcard_pic, String id_pic, String stucard_pic, int level, int exp, int followers_count, int followings_count) {
        this.name = name;
        this.mobile = mobile;
        this.avatar = avatar;
        this.sex = sex;
        this.wechat_id = wechat_id;
        this.qq_id = qq_id;
        this.birth = birth;
        this.status = status;
        this.stuwithcard_pic = stuwithcard_pic;
        this.id_pic = id_pic;
        this.stucard_pic = stucard_pic;
        this.level = level;
        this.exp = exp;
        this.followers_count = followers_count;
        this.followings_count = followings_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getWechat_id() {
        return wechat_id;
    }

    public void setWechat_id(String wechat_id) {
        this.wechat_id = wechat_id;
    }

    public String getQq_id() {
        return qq_id;
    }

    public void setQq_id(String qq_id) {
        this.qq_id = qq_id;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStuwithcard_pic() {
        return stuwithcard_pic;
    }

    public void setStuwithcard_pic(String stuwithcard_pic) {
        this.stuwithcard_pic = stuwithcard_pic;
    }

    public String getId_pic() {
        return id_pic;
    }

    public void setId_pic(String id_pic) {
        this.id_pic = id_pic;
    }

    public String getStucard_pic() {
        return stucard_pic;
    }

    public void setStucard_pic(String stucard_pic) {
        this.stucard_pic = stucard_pic;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
