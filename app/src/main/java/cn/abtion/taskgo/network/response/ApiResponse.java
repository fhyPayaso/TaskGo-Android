package cn.abtion.taskgo.network.response;

import cn.abtion.taskgo.R;

/**
 * 正确响应体
 *
 * @author FanHongyu.
 * @since 18/1/17 23:21.
 * email fanhongyu@hrsoft.net.
 */

public class ApiResponse<T> {


    /** 返回码 */
    private int code = -2;

    /** 返回信息 */
    private String message = "";
    /** 返回数据 */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
