package cn.abtion.taskgo.mvp.model.task.request;

/**
 * 发布物品任务请求体
 *
 * @author FanHongyu.
 * @since 18/3/7 11:42.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseLostFoundTaskRequest {

    private String name;
    private String type;
    private String picture_url;
    private String place;
    private String remarks;

    public ReleaseLostFoundTaskRequest(String name, String type, String picture_url, String place, String remarks) {
        this.name = name;
        this.type = type;
        this.picture_url = picture_url;
        this.place = place;
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
