package cn.abtion.taskgo.mvp.presenter.mine;

import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.mine.ChangePhotoContract;

/**
 * @author：lszr on 2018/3/12 21:28
 * @email：1085963811@qq.com
 */
public class ChangePhotoPresenter extends BasePresenter<ChangePhotoContract.View> implements ChangePhotoContract.Presenter {
    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     * @param mView V层的引用
     */
    public ChangePhotoPresenter(ChangePhotoContract.View mView) {
        super(mView);
    }
}
