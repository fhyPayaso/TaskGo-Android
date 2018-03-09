package cn.abtion.taskgo.mvp.model.task.request;

/**
 * @author FanHongyu.
 * @since 18/3/9 20:53.
 * email fanhongyu@hrsoft.net.
 */

public class FinishLostFoundTaskRequest {

    private String user_id;
    private String task_id;
    private String task_type;

    public FinishLostFoundTaskRequest(String user_id, String task_id, String task_type) {
        this.user_id = user_id;
        this.task_id = task_id;
        this.task_type = task_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
