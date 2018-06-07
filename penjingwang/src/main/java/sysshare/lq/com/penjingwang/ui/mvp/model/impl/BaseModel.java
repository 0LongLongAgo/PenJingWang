package sysshare.lq.com.penjingwang.ui.mvp.model.impl;

import sysshare.lq.com.penjingwang.api.ApiServer;
import sysshare.lq.com.penjingwang.net.RxRetrofit;
import sysshare.lq.com.penjingwang.ui.mvp.model.IModel;

public class BaseModel implements IModel {
    @Override
    public ApiServer doRxRequest() {
        return RxRetrofit.Api();
    }
}
