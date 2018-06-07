package sysshare.lq.com.penjingwang.net.callback;

import java.util.List;

import sysshare.lq.com.penjingwang.bean.BaseBean;
import sysshare.lq.com.penjingwang.bean.PageListDataBean;
import sysshare.lq.com.penjingwang.net.NetConfig;
import sysshare.lq.com.penjingwang.ui.mvp.presenter.BasePresenter;
import sysshare.lq.com.penjingwang.ui.mvp.view.IListDataView;

public abstract class RxPageListObserver<T> extends RxBaseObserver<PageListDataBean<T>>{

    public IListDataView<T> mListDataView;
    public RxPageListObserver(BasePresenter mPresenter) {
        super(mPresenter);
        this.mListDataView = (IListDataView<T>)mPresenter.getView();
    }

    @Override
    public void onNext(BaseBean<PageListDataBean<T>> baseBean) {
        if (baseBean.errorCode == NetConfig.REQUEST_SUCCESS) {
            PageListDataBean<T> mListData = baseBean.data;

            if (mListDataView.getPage() == 0) {
                mListDataView.clearListData();
            }

            if (mListData.isOver()) {
                mListDataView.showNoMore();
            }else{
                mListDataView.autoLoadMore();
            }

            onSuccess(mListData.getDatas());
        }else{
            onFail(baseBean.errorCode,baseBean.errorMsg);
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        mListDataView.showError();
    }

    public abstract void onSuccess(List<T> mData);

    public abstract void onFail(int errorCode, String errorMsg);
}
