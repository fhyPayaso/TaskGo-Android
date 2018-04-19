package cn.abtion.taskgo.mvp.model.task.model;

import java.io.Serializable;

/**
 * 有关水任务的信息
 *
 * @author FanHongyu.
 * @since 18/4/19 21:00.
 * email fanhongyu@hrsoft.net.
 */

public class WaterTaskInfoModel implements Serializable {


    private int money;
    private String addressNumber;
    private int waterType;


    public WaterTaskInfoModel() {

    }

    public WaterTaskInfoModel(int money, String addressNumber, int waterType) {
        this.money = money;
        this.addressNumber = addressNumber;
        this.waterType = waterType;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public int getWaterType() {
        return waterType;
    }

    public void setWaterType(int waterType) {
        this.waterType = waterType;
    }
}
