package cn.abtion.taskgo.mvp.model.task.request;

/**
 * @author FanHongyu.
 * @since 18/3/7 10:48.
 * email fanhongyu@hrsoft.net.
 */

public class AcceptLostFoundTaskRequest {

    private int task_id;
    private int task_type;
    private String task_name;


    public AcceptLostFoundTaskRequest(int task_id, int task_type, String task_name) {
        this.task_id = task_id;
        this.task_type = task_type;
        this.task_name = task_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getTask_type() {
        return task_type;
    }

    public void setTask_type(int task_type) {
        this.task_type = task_type;
    }
}
