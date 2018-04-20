package cn.abtion.taskgo.mvp.presenter.task;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.task.FillTaskInfoContract;
import cn.abtion.taskgo.mvp.model.task.model.LostFoundTaskInfoModel;
import cn.abtion.taskgo.mvp.model.task.model.WaterTaskInfoModel;
import cn.abtion.taskgo.utils.RegexpUtils;

/**
 * @author FanHongyu.
 * @since 18/4/20 12:21.
 * email fanhongyu@hrsoft.net.
 */

public class FillTaskInfoPresenter extends BasePresenter<FillTaskInfoContract.View> implements FillTaskInfoContract
        .Presenter {

    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public FillTaskInfoPresenter(FillTaskInfoContract.View mView) {
        super(mView);
    }

    @Override
    public void checkWaterTaskInfo(WaterTaskInfoModel model) {
        String addressNumber = model.getAddressNumber();
        boolean flag = true;
        if (addressNumber.equals("")) {
            mView.onDataFalse("请填宿舍号");
        } else if (addressNumber.length() != 4 || !RegexpUtils.checkAllNumber(addressNumber)) {
            mView.onDataFalse("宿舍号格式错误");
        } else {
            mView.onDataTrue();
        }
    }

    @Override
    public void checkLostFoundTaskInfo(LostFoundTaskInfoModel model) {

        if("".equals(model.getName())) {
            mView.onDataFalse("请填写物品名称");
        } else if("".equals(model.getPlace())) {
            mView.onDataFalse("请填写地点");
        } else if(model.isWithImg() && model.getImgUrl() == null) {
            mView.onDataFalse("图片上传有误");
        } else {
            mView.onDataTrue();
        }
    }
}
