package cn.abtion.taskgo.mvp.model.task.response;

/**
 * @author FanHongyu.
 * @since 18/3/8 20:26.
 * email fanhongyu@hrsoft.net.
 */

public class BaseTaskResponse {


    //公共部分
    private int id;
    private int user_id;
    private String user_name;
    private String avatar;
    private int type;
    private int status;
    private String created_at;
    private String updated_at;
    //水任务特有
    private String address;
    //物品任务特有
    private String name;
    private String picture_url;
    private String place;
    private String remarks;
    private String finished_by;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFinished_by() {
        return finished_by;
    }

    public void setFinished_by(String finished_by) {
        this.finished_by = finished_by;
    }
}
