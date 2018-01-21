package cn.abtion.taskgo.mvp.model.request;

/**
 * 登录所需要的model
 *
 * @author fhyPayaso
 * @since 2018/1/21 on 上午1:20
 * fhyPayaso@qq.com
 */
public class LoginRequest {


    private String phone;
    private String password;


    public LoginRequest(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
