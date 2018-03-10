package cn.abtion.taskgo.mvp.model.account;

/**
 * @author heaijia
 * @since 2018/2/2 上午11:33
 * email 549044363@qq.com
 */


public class RegisterRequestModel{

    /**
     * 请求部分的接口 value--phone
     * 微信/QQ注册:"type": "wechat_id"   注册类型，wechat_id 或 qq_id注册类型
     * 手机号注册  "type": "mobile"      本接口为mobile
     */

    private String phone;
    private String password;
    private String type;

    public RegisterRequestModel() {
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(){
        this.phone=phone;
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
