package sysshare.lq.com.penjingwang.ui.mvp.model;

import sysshare.lq.com.penjingwang.api.ApiServer;

public interface IModel {

    /**
     * 使用RxRetrofit请求数据
     *
     * @return
     */
    ApiServer doRxRequest();
}
