package cn.abtion.taskgo.mvp.model.account;

/**
 * @author heaijia
 * @since 2018/3/12 下午5:08
 * email 549044363@qq.com
 */

public class TokenResponse {
    private String user_id;
    private String token;

    public String getUser_id(){
        return user_id;
    }
    public void setUser_id(String user_id){
        this.user_id=user_id;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token=token;
    }

}
