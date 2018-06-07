package sysshare.lq.com.penjingwang.net.callback;


import io.reactivex.Observable;
import io.reactivex.functions.Function;
import sysshare.lq.com.penjingwang.bean.BaseBean;
import sysshare.lq.com.penjingwang.net.NetConfig;

public abstract class RxFunction<T,R> implements Function<BaseBean<T>,Observable<BaseBean<R>>> {
    @Override
    public Observable<BaseBean<R>> apply(BaseBean<T> tBaseBean) {
        if(tBaseBean.errorCode == NetConfig.REQUEST_SUCCESS){
            return doOnNextRequest();
        }
        return null;
    }

    protected abstract Observable<BaseBean<R>> doOnNextRequest();
}
