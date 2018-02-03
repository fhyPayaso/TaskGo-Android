package cn.abtion.taskgo.mvp.view.home.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.taskgo.base.adapter.RecyclerScrollListener;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.frgment.BasePresenterFragment;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.common.Config;
import cn.abtion.taskgo.mvp.model.request.home.BaseTaskModel;
import cn.abtion.taskgo.mvp.view.home.adapter.BtnTaskRecAdapter;
import cn.abtion.taskgo.mvp.view.home.adapter.TaskListener;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.ToastUtil;

import static cn.abtion.taskgo.utils.Utility.runOnUiThread;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 下午11:31
 * fhyPayaso@qq.com
 */
public class LostTaskListFragment extends BasePresenterFragment implements TaskListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rec_lost_find_task)
    RecyclerView recLostFindTask;
    @BindView(R.id.fresh_task_list)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;

    private BtnTaskRecAdapter mAdapter;
    private List<BaseTaskModel> mLostFindTaskLst;
    private DialogUtil.CustomAlertDialog dialogTaskInformation;


    @Override
    protected BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lost_task_list;
    }

    @Override
    protected void initVariable() {

        mLostFindTaskLst = new ArrayList<>();
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


        mAdapter = new BtnTaskRecAdapter(getContext(), mLostFindTaskLst);
        mAdapter.setTaskListener(this);
        recLostFindTask.setAdapter(mAdapter);
        recLostFindTask.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        mAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<BaseTaskModel>() {
            @Override
            public void onItemClicked(BaseTaskModel baseTaskModel, BaseRecyclerViewAdapter.BaseViewHolder holder) {
                showDialogTaskInformation(baseTaskModel);
            }
        });

        recLostFindTask.addOnScrollListener(new RecyclerScrollListener((FloatingActionButton) getActivity().findViewById(R.id.btn_release_task)) {
            @Override
            public void scrolledToLast() {
                ToastUtil.showToast("到底了");
            }
        });
    }

    /**
     * 显示任务详细信息
     */
    private void showDialogTaskInformation(BaseTaskModel model) {

        dialogTaskInformation = new DialogUtil().new CustomAlertDialog();
        dialogTaskInformation.initDialog(getContext(), R.layout.dialog_lost_found_information);
        dialogTaskInformation.setCanceledOntouchOutside(true);
        dialogTaskInformation.showDialog();

        View view = dialogTaskInformation.getView();
        TextView txtItemName = view.findViewById(R.id.txt_item_name);
        TextView txtLostFoundType = view.findViewById(R.id.txt_item_task_type);
        TextView btnConfirm = view.findViewById(R.id.btn_information_confirm);

        txtItemName.setText(model.getMainValue());
        txtLostFoundType.setText(getString(R.string.txt_lost_found_task_type_lost));
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTaskInformation.hideDialog();
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
    public void onClickAvatar(int position) {
        ToastUtil.showToast("点击了头像" + position);
    }

    @Override
    public void onClickAccept(final int position) {

        DialogUtil.NativeDialog dialogAccept = new DialogUtil().new NativeDialog();
        dialogAccept
                .singleInit(getContext())
                .setTitle(getString(R.string.dialog_title_if_accept))
                .setMessage(getString(R.string.dialog_message_remove_task))
                .setNegativeButton(getString(R.string.txt_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.txt_confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showToast(getString(R.string.toast_accept_successful) + position);
                        mAdapter.removeItem(position);
                        dialog.dismiss();
                    }
                })
                .showNativeDialog();

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

    /**
     * 测试增加数据
     *
     * @param number
     */
    private void testAddItem(int number) {

        for (int i = 0; i < number; i++) {
            mLostFindTaskLst.add(new BaseTaskModel(1
                    , "url"
                    , "xkaxka"
                    , "02-10 04:25"
                    , "机械键盘"));
        }
    }
}
