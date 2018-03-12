package cn.abtion.taskgo.mvp.model.task.request;

/**
 * @author FanHongyu.
 * @since 18/3/10 18:41.
 * email fanhongyu@hrsoft.net.
 */

public class FinishUserListRequest {


    private String task_id;
    private String task_type;

    public FinishUserListRequest(String task_id, String task_type) {
        this.task_id = task_id;
        this.task_type = task_type;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }
}
