package cn.abtion.taskgo.mvp.view.initiate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarActivity;
import cn.abtion.taskgo.mvp.view.account.LoginActivity;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * 引导页
 *
 * @author FanHongyu.
 * @since 18/3/5 15:27.
 * email fanhongyu@hrsoft.net.
 */

public class GuideActivity extends BaseNoBarActivity {

    @BindView(R.id.vp_guide_pager)
    ViewPager vpGuidePager;

    private Button btnToLogin;
    private int imageId[];
    private List<View> pagerList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide_pager;
    }

    @Override
    protected void initVariable() {

        imageId = new int[]{R.layout.fragment_initiate_first, R.layout.fragment_initiate_second};
        pagerList = new ArrayList<>();
    }

    @Override
    protected void initView() {

        initViewPager();
    }

    @Override
    protected void loadData() {

    }

    private void initViewPager() {


        for (int i = 0; i < imageId.length; i++) {

            View view = LayoutInflater.from(this).inflate(imageId[i], null);


            if (i == imageId.length - 1) {

                btnToLogin = (Button) view.findViewById(R.id.btn_initiate);
                btnToLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginActivity.startActivity(GuideActivity.this);
                        finish();
                    }
                });
            }
            pagerList.add(view);
        }

        vpGuidePager.setAdapter(new GuidePagerAdapter(pagerList));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,GuideActivity.class));
    }

}
