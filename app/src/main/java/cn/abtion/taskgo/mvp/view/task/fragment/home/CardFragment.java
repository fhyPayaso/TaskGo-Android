package cn.abtion.taskgo.mvp.view.task.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.frgment.BaseFragment;
import cn.abtion.taskgo.mvp.model.task.model.ChooseCardModel;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/4/20 20:10.
 * email fanhongyu@hrsoft.net.
 */

public class CardFragment extends BaseFragment {


    @BindView(R.id.img_card)
    ImageView imgCard;
    @BindView(R.id.txt_have_card_number)
    TextView txtHaveCardNumber;
    @BindView(R.id.img_card_sub)
    ImageView imgCardSub;
    @BindView(R.id.txt_choose_card_number)
    TextView txtChooseCardNumber;
    @BindView(R.id.img_card_add)
    ImageView imgCardAdd;
    Unbinder unbinder;


    private ChooseCardModel mChooseCardModel;


    private int haveCardNumber;
    private int chooseCardNumber;


    @Override
    protected int getLayoutId() {
        return R.layout.item_pager_card;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

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

    @OnClick({R.id.img_card_sub, R.id.img_card_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_card_sub:

                if (chooseCardNumber > 0) {
                    haveCardNumber++;
                    chooseCardNumber--;
                }
                setTextNumber();
                break;
            case R.id.img_card_add:
                if (haveCardNumber > 0) {
                    haveCardNumber--;
                    chooseCardNumber++;
                }
                setTextNumber();
                break;
            default:
                break;
        }
    }


    private void setTextNumber() {
        txtChooseCardNumber.setText(String.valueOf(chooseCardNumber));
        txtHaveCardNumber.setText(String.valueOf(haveCardNumber));
        mChooseCardModel.setChooseNumber(chooseCardNumber);
        mChooseCardModel.setHaveNumber(haveCardNumber);
    }

    public void setChooseCardModel(ChooseCardModel chooseCardModel) {
        mChooseCardModel = chooseCardModel;


        haveCardNumber = mChooseCardModel.getHaveNumber();
        chooseCardNumber = mChooseCardModel.getChooseNumber();

        Glide.with(getContext()).load(mChooseCardModel.getCardImgUrl()).into(imgCard);
        txtHaveCardNumber.setText(String.valueOf(haveCardNumber));
        txtChooseCardNumber.setText(String.valueOf(chooseCardNumber));
    }
}
