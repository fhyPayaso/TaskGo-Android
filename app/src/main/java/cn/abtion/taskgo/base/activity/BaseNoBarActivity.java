package cn.abtion.taskgo.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author FanHongyu.
 * @since 18/1/16 15:30.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BaseNoBarActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initActivity();
    }
}
