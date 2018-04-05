package cn.abtion.taskgo.widget.VerticalPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.mvp.model.task.response.LostFoundTaskResponse;
import cn.abtion.taskgo.utils.DialogUtil;

/**
 * @author FanHongyu.
 * @since 18/4/5 13:43.
 * email fanhongyu@hrsoft.net.
 */

public class CommonFragment extends Fragment implements DragLayout.GotoDetailListener {


    private ImageView imageView;
    private View address1, address2, address3, address4, address5;
    private RatingBar ratingBar;
    private View head1, head2, head3, head4;
    private String imageUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common, null);
        DragLayout dragLayout = (DragLayout) rootView.findViewById(R.id.drag_layout);
        imageView = (ImageView) dragLayout.findViewById(R.id.image);

        Glide.with(getContext()).load(imageUrl).into(imageView);

//        address1 = dragLayout.findViewById(R.id.address1);
//        address2 = dragLayout.findViewById(R.id.address2);
//        address3 = dragLayout.findViewById(R.id.address3);
//        address4 = dragLayout.findViewById(R.id.address4);
//        address5 = dragLayout.findViewById(R.id.address5);
//        ratingBar = (RatingBar) dragLayout.findViewById(R.id.rating);
//
//        head1 = dragLayout.findViewById(R.id.head1);
//        head2 = dragLayout.findViewById(R.id.head2);
//        head3 = dragLayout.findViewById(R.id.head3);
//        head4 = dragLayout.findViewById(R.id.head4);

        dragLayout.setGotoDetailListener(this);
        return rootView;
    }

    @Override
    public void gotoDetail() {

        DialogUtil.CustomAlertDialog dialog = new DialogUtil().new CustomAlertDialog();
        dialog.initDialog(getContext(), R.layout.dialog_card_information);
        dialog.setCanceledOntouchOutside(true);
        dialog.showDialog();
        View view = dialog.getView();

    }

    public void bindData(String imageUrl) {
        this.imageUrl = imageUrl;
    }





    /**
     * 展示物品任务详细信息
     * @param context
     * @param dialog
     * @param response
     */
    public static void showLostFoundTaskInfo(Context context, final DialogUtil.CustomAlertDialog dialog, LostFoundTaskResponse response) {


    }
}