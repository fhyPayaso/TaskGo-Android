package cn.abtion.taskgo.network.response;

import java.io.IOException;

/**
 * 异常响应体
 *
 * @author FanHongyu.
 * @since 18/1/17 23:54.
 * email fanhongyu@hrsoft.net.
 */

public class ApiException extends IOException {

    private int code;
    private String message;

    public ApiException(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }
}