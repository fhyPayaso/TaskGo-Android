package cn.abtion.taskgo.mvp.model.task.model;

import java.io.Serializable;

/**
 * @author FanHongyu.
 * @since 18/4/19 21:04.
 * email fanhongyu@hrsoft.net.
 */

public class LostFoundTaskInfoModel implements Serializable{


    private String name;
    private String place;
    private int lostFoundType;
    private String imgUrl;
    private boolean withImg;
    private String remark;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getLostFoundType() {
        return lostFoundType;
    }

    public void setLostFoundType(int lostFoundType) {
        this.lostFoundType = lostFoundType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isWithImg() {
        return withImg;
    }

    public void setWithImg(boolean withImg) {
        this.withImg = withImg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
