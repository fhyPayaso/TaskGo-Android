package cn.abtion.taskgo.mvp.model.account;

/**
 * @author heaijia
 * @since 2018/2/2 上午11:33
 * email 549044363@qq.com
 */

public class UpdatePasswordRequestModel {

    private String phone;
    private String password;

    public UpdatePasswordRequestModel() {
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

}
