package cn.abtion.taskgo.mvp.model.request.home;

/**
 * @author FanHongyu.
 * @since 18/3/7 10:48.
 * email fanhongyu@hrsoft.net.
 */

public class AcceptLostFoundTaskRequest {

    private int task_id;
    private int task_type;

    public AcceptLostFoundTaskRequest(int task_id, int task_type) {
        this.task_id = task_id;
        this.task_type = task_type;
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
