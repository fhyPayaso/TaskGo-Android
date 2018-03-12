package cn.abtion.taskgo.common;

/**
 * @author FanHongyu.
 * @since 18/1/17 17:51.
 * email fanhongyu@hrsoft.net.
 */

public class Config {

    /**
     * 网络请求BaseURL
     */
    public static final String APP_SERVER_BASE_URL = "http://taskgo.andyhui.xin/";

    /**
     * 网络请求连接超时时间，单位：s
     */
    public static final int APP_SERVER_CONNECT_TIME_OUT = 15;


    /**
     * SwipeRefreshLayout下拉刷新时间
     */
    public final static int REFRESH_TIME = 1000;

    /**
     * 空字段
     */
    public static final String EMPTY_FIELD="";

    /**
     * 对密码的位数进行设置
     */
    public static final int PASSWORD_MIN = 6;
    public static final int PASSWORD_MAX =20;


}
