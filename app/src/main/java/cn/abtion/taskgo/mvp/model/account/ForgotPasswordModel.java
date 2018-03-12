package cn.abtion.taskgo.mvp.model.account;

/**
 * @author heaijia
 * @since 2018/3/9 上午12:54
 * email 549044363@qq.com
 */

public class ForgotPasswordModel {

    private String mobile;
    private String captcha;
    private String new_password;
    private String new_passwordagain;



    public ForgotPasswordModel(String mobile, String captcha, String new_password,String new_passwordagain) {
        this.mobile = mobile;
        this.captcha = captcha;
        this.new_password=new_password;
        this.new_passwordagain=new_passwordagain;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile= mobile;
    }

    public String getCaptcha() {
        return captcha;
    }
    public void setPassword(String captcha) {
        this.captcha= captcha;
    }

    public String getNew_password(){return new_password;}
    public void setNew_password(String new_password){this.new_password=new_password;}

    public String getNew_passwordagain(){return new_passwordagain;}
    public void setNew_passwordagain(String new_password){this.new_passwordagain=new_passwordagain;}
}
