package cn.abtion.taskgo.mvp.model.mine;

/**
 * @author：lszr on 2018/1/30 23:57
 * @email：1085963811@qq.com
 */
public class MyFollowModel {
    private String user_id;
    private String name;
    private String avatar;

    public MyFollowModel(String user_id, String name, String avatar) {
        this.user_id = user_id;
        this.name = name;
        this.avatar = avatar;
    }

    public String getUser_id() {

        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
}
