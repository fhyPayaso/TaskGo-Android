package cn.abtion.taskgo.mvp.model.mine;

/**
 * @author：lszr on 2018/3/12 18:06
 * @email：1085963811@qq.com
 */
public class MyFansListModel {

    public String getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(String follower_id) {
        this.follower_id = follower_id;
    }

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

    public MyFansListModel(String follower_id, String name, String avatar) {

        this.follower_id = follower_id;
        this.name = name;
        this.avatar = avatar;
    }

    private String follower_id;
    private String name;
    private String avatar;

}
