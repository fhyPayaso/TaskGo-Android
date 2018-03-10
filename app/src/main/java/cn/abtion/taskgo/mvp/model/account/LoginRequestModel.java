package cn.abtion.taskgo.mvp.model.account;

/**
 * 登录所需要的model
 *
 * @author fhyPayaso
 * @since 2018/1/21 on 上午1:20
 * fhyPayaso@qq.com
 */
public class LoginRequestModel{


    private String value;
    private String password;
    private String type;


    public LoginRequestModel(String phone, String password) {
        this.value = phone;
        this.password = password;
    type="mobile";
//        this.type=type;
    }

    public String getPhone() {
        return value;
    }
    public void setPhone(String phone) {
        this.value = phone;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getType(){return type;}
    public void setType(String type){this.type=type;}
}
