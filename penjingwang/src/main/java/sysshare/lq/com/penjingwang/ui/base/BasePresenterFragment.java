package sysshare.lq.com.penjingwang.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import sysshare.lq.com.penjingwang.ui.mvp.presenter.BasePresenter;
import sysshare.lq.com.penjingwang.ui.mvp.view.IView;

public abstract class BasePresenterFragment<P extends BasePresenter<V>,V extends IView> extends BaseFragment implements IView {
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
        attachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachView();
    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

   private void detachView() {
       if (mPresenter != null) {
           mPresenter.detachView();
           mPresenter.removeAllDisPosable();
           mPresenter = null;
       }
    }
    protected abstract P createPresenter();
}
