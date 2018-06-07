package sysshare.lq.com.penjingwang.net.callback;


import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import sysshare.lq.com.penjingwang.bean.BaseBean;
import sysshare.lq.com.penjingwang.net.NetConfig;


public abstract class RxConsumer<T> implements Consumer<BaseBean<T>> {

    @Override
    public void accept(@NonNull BaseBean<T> tBaseBean) throws Exception {
        if (tBaseBean.errorCode == NetConfig.REQUEST_SUCCESS){
            onSuccess(tBaseBean.data);
        }else {
            onFail(tBaseBean.errorMsg);
        }
    }

    protected abstract void onFail(String errorMsg);

    protected abstract void onSuccess(T data);
}