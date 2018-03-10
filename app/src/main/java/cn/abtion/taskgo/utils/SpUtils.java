package cn.abtion.taskgo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author heaijia
 * @since 2018/3/7 下午7:10
 * email 549044363@qq.com
 */

public class SpUtils {
    /**
     * 将数据保存在缓冲中
     * @param context
     * @param key
     * @param obj
     */
    public static void save(Context context, String key, Object obj){
        //Context.MODE_PRIVATE为默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容，
        //如果想把新写入的内容追加到原文件中。可以使用Context.MODE_APPEND
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        //编辑器
        SharedPreferences.Editor editor = sp.edit();
        if(obj instanceof String){
            editor.putString(key, (String) obj);
        }else if(obj instanceof Boolean){
            editor.putBoolean(key, (Boolean) obj);
        }
        editor.commit();//保存数据到缓冲
    }
    /**
     * 获取SharedPreferences的实例
     * @param context
     * @return
     */
    public static SharedPreferences get(Context context){

        return context.getSharedPreferences("config", Context.MODE_PRIVATE);
    }

}