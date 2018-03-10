package cn.abtion.taskgo.mvp.view.message.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.RecyclerScrollListener;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.model.MessageModel;
import cn.abtion.taskgo.mvp.view.task.activity.SearchTaskActivity;
import cn.abtion.taskgo.mvp.view.message.activity.ChatWindowActivity;
import cn.abtion.taskgo.mvp.view.message.adapter.MessageItemListener;
import cn.abtion.taskgo.mvp.view.message.adapter.MessageListRecAdapter;
import cn.abtion.taskgo.utils.ToastUtil;
import cn.abtion.taskgo.widget.SwipeItemLayout;

import static cn.abtion.taskgo.utils.Utility.runOnUiThread;

/**
 * @author fhyPayaso
 * @since 2018/1/21 on 下午1:31
 * fhyPayaso@qq.com
 */
public class MessageFragment extends BasePresenterFragment implements MessageItemListener, SwipeRefreshLayout
        .OnRefreshListener {


    @BindView(R.id.rec_message_list)
    RecyclerView recMessageList;
    @BindView(R.id.fresh_message_list)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;


    private List<MessageModel> mMessageList;
    private MessageListRecAdapter mAdapter;


    @Override
    protected BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initVariable() {
        mMessageList = new ArrayList<>();
    }

    @Override
    protected void initView() {

        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void loadData() {

    }


    /**
     * 初始化SwipeRefreshLayout
     */
    private void initRefreshLayout() {

        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeRefresh.setRefreshing(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        testAddItem(10);
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }

    private void initRecyclerView() {

        mAdapter = new MessageListRecAdapter(getContext(), mMessageList);
        mAdapter.setMessageListener(this);
        recMessageList.setAdapter(mAdapter);
        recMessageList.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        recMessageList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        recMessageList.addOnScrollListener(new RecyclerScrollListener() {
            @Override
            public void scrolledToLast() {
                ToastUtil.showToast("到底了");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        testAddItem(3);
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }, Config.REFRESH_TIME);
    }

    @Override
    public void onClickAvatar(int position) {
        ToastUtil.showToast("点击了头像" + position);
    }

    @Override
    public void onClickDelete(int position) {
        mAdapter.removeItem(position);
    }

    @Override
    public void onClickChat(int position) {
        ChatWindowActivity.startActivity(getContext());
    }

    @OnClick(R.id.ly_search_friend)
    public void onViewClicked() {
        SearchTaskActivity.startActivity(getContext());
    }

    /**
     * 测试增加数据
     *
     * @param number
     */
    private void testAddItem(int number) {

        for (int i = 0; i < number; i++) {

            MessageModel model = new MessageModel("url", "lszr-x", "我觉得不行", "04:21");
            if (i % 2 == 0) {
                model.setUnReadNumber(i);
            }
            mMessageList.add(model);
        }
    }
}
