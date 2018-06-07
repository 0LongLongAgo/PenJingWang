package sysshare.lq.com.penjingwang.ui.mvp.presenter;

import io.reactivex.disposables.Disposable;
import sysshare.lq.com.penjingwang.ui.mvp.view.IView;

public interface IPresenter<V extends IView> {

    void attachView(V view);

    void detachView();
    //检查View是否存在
    void checkAttachView();

    V getView();

    void addDisposable(Disposable disposable);

    void removeDisposable(Disposable disposable);

    void removeAllDisposable();
}
