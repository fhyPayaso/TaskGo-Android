package cn.abtion.taskgo.mvp.model.task.response;

/**
 * @author fhyPayaso
 * @since 2018/3/11 on 上午12:13
 * fhyPayaso@qq.com
 */
public class SimpleUserInfoResponse {



    private String user_id;
    private String user_name;
    private String avatar;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
