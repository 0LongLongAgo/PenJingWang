package sysshare.lq.com.penjingwang.net.callback;

import android.content.Context;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import sysshare.lq.com.penjingwang.R;
import sysshare.lq.com.penjingwang.application.AppContext;
import sysshare.lq.com.penjingwang.bean.BaseBean;
import sysshare.lq.com.penjingwang.net.NetConfig;
import sysshare.lq.com.penjingwang.ui.mvp.presenter.BasePresenter;
import sysshare.lq.com.penjingwang.ui.mvp.view.IView;
import sysshare.lq.com.penjingwang.utils.ToastUtils;

public class RxBaseObserver<T> extends DisposableObserver<BaseBean<T>> {

    protected IView view;

    RxBaseObserver(BasePresenter mPresenter) {
        this.view = mPresenter.getView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoading();
    }

    @Override
    public void onNext(BaseBean<T> tBaseBean) {

    }

    @Override
    public void onError(Throwable e) {
        hideLoading();
        dealException(AppContext.getContext(), e);
    }

    private void dealException(Context context, Throwable t) {
        if (t instanceof ConnectException || t instanceof UnknownHostException) {
            //连接错误
            onException(NetConfig.CONNECT_ERROR, context);
        } else if (t instanceof InterruptedException) {
            //连接超时
            onException(NetConfig.CONNECT_TIMEOUT, context);
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException) {
            //解析错误
            onException(NetConfig.PARSE_ERROR, context);
        } else if (t instanceof SocketTimeoutException) {
            //请求超时
            onException(NetConfig.REQUEST_TIMEOUT, context);
        } else if (t instanceof UnknownError) {
            //未知错误
            onException(NetConfig.UNKNOWN_ERROR, context);
        } else {
            //未知错误
            onException(NetConfig.UNKNOWN_ERROR, context);
        }
    }

    void onException(int errorCode, Context context) {
        switch (errorCode) {
            case NetConfig.CONNECT_ERROR:
                ToastUtils.showToast(context, R.string.connect_error);
                break;
            case NetConfig.CONNECT_TIMEOUT:
                ToastUtils.showToast(context, R.string.connect_timeout);
                break;
            case NetConfig.PARSE_ERROR:
                ToastUtils.showToast(context, R.string.parse_error);
                break;
            case NetConfig.REQUEST_TIMEOUT:
                ToastUtils.showToast(context, R.string.request_timeout);
                break;
            case NetConfig.UNKNOWN_ERROR:
                ToastUtils.showToast(context, R.string.unknown_error);
                break;
        }
    }
    @Override
    public void onComplete() {
        hideLoading();
    }

    public void showLoading() {
        view.showLoading("");
    }

    private void hideLoading() {
        if (null != view)
            this.view.hideLoading();
    }
}
