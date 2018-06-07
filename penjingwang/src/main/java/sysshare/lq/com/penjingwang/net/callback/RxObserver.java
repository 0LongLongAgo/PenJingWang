package sysshare.lq.com.penjingwang.net.callback;

import sysshare.lq.com.penjingwang.bean.BaseBean;
import sysshare.lq.com.penjingwang.net.NetConfig;
import sysshare.lq.com.penjingwang.ui.mvp.presenter.BasePresenter;

public abstract class RxObserver<T> extends RxBaseObserver<T> {
    public RxObserver(BasePresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public void onNext(BaseBean<T> mBaseBean) {
        if (mBaseBean.errorCode == NetConfig.REQUEST_SUCCESS) {
            onSuccess(mBaseBean.data);
        }else{
            onFail(mBaseBean.errorCode,mBaseBean.errorMsg);
        }
    }

    protected abstract void onSuccess(T data);

    protected abstract void onFail(int errorCode,String errorMsg);
}
