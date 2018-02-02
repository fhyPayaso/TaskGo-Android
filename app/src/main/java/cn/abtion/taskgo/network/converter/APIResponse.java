package cn.abtion.taskgo.network.converter;


/**
 * @author heaijia
 * @since 2018/2/2 上午11:33
 * email 549044363@qq.com
 */


public class APIResponse<T>  {
    private int code = -2;
    private T data;
    private String msg="";

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
