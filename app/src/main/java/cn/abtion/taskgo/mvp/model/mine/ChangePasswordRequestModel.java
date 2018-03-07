package cn.abtion.taskgo.mvp.model.mine;

/**
 * @author：lszr on 2018/3/7 09:07
 * @email：1085963811@qq.com
 */
public class ChangePasswordRequestModel {
    public String oldPassword;
    public String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public ChangePasswordRequestModel(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
