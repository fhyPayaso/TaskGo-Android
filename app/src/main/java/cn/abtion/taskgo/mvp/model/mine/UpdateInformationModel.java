package cn.abtion.taskgo.mvp.model.mine;

/**
 * @author：lszr on 2018/3/11 14:41
 * @email：1085963811@qq.com
 */
public class UpdateInformationModel {
    private String name;
    private String avatar;
    private String sex;

    public UpdateInformationModel(String name, String avatar, String sex, String birth) {
        this.name = name;
        this.avatar = avatar;
        this.sex = sex;
        this.birth = birth;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    private String birth;

}
