package cn.abtion.taskgo.mvp.model.task.request;

/**
 * 发布送水任务请求体
 *
 * @author FanHongyu.
 * @since 18/3/6 19:17.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseWaterTaskRequest {

    private String address;
    private String type;

    public ReleaseWaterTaskRequest(String address, String type) {
        this.address = address;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
