package cn.abtion.taskgo.mvp.view.task.activity.release;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.mvp.model.task.model.ChooseCardModel;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/4/20 10:42.
 * email fanhongyu@hrsoft.net.
 */

public class CardPagerAdapter extends PagerAdapter {

    ImageView imgCard;
    TextView txtHaveCardNumber;
    ImageView imgCardSub;
    TextView txtChooseCardNumber;
    ImageView imgCardAdd;

    private List<ChooseCardModel> mCardModelList;
    private Context mContext;
    private LayoutInflater inflater;


    /**
     * 当前卡片选择数量
     */
    public CardPagerAdapter(List<ChooseCardModel> cardModelList, Context context) {
        mCardModelList = cardModelList;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = inflater.inflate(R.layout.item_pager_card, container, false);
        bindView(view, position);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    private void bindView(View rootView, final int position) {


        imgCard = (ImageView) rootView.findViewById(R.id.img_card);
        txtChooseCardNumber = (TextView) rootView.findViewById(R.id.txt_choose_card_number);
        txtHaveCardNumber = (TextView) rootView.findViewById(R.id.txt_have_card_number);

        imgCardAdd = (ImageView) rootView.findViewById(R.id.img_card_add);
        imgCardSub = (ImageView) rootView.findViewById(R.id.img_card_sub);

        Glide.with(mContext).load(mCardModelList.get(position).getCardImgUrl()).into(imgCard);
        setCardNumber(mCardModelList.get(position));


//        imgCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //ToastUtil.showToast("点击了图片" + position);
//
//            }
//        });
//
//
//        imgCardAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChooseCardModel model = mCardModelList.get(position);
//                int chooseNum = model.getChooseNumber();
//                int haveNum = model.getHaveNumber();
//                if (haveNum > 0) {
//                    model.setChooseNumber(chooseNum + 1);
//                    model.setChooseNumber(haveNum - 1);
//                    setCardNumber(model);
//                }
//            }
//        });
//
//
//        imgCardSub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChooseCardModel model = mCardModelList.get(position);
//                int chooseNum = model.getChooseNumber();
//                int haveNum = model.getHaveNumber();
//                if (chooseNum > 0) {
//                    model.setChooseNumber(chooseNum - 1);
//                    model.setChooseNumber(haveNum + 1);
//                    setCardNumber(model);
//                }
//            }
//        });
    }


    private void setCardNumber(ChooseCardModel model) {
        int chooseNum = model.getChooseNumber();
        int haveNum = model.getHaveNumber();
        txtChooseCardNumber.setText(String.valueOf(chooseNum));
        txtHaveCardNumber.setText(String.valueOf(haveNum));
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mCardModelList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
