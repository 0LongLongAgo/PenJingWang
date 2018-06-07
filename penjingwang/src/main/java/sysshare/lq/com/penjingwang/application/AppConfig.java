package sysshare.lq.com.penjingwang.application;

import android.content.Context;

import sysshare.lq.com.penjingwang.common.UrlConstainer;
import sysshare.lq.com.penjingwang.net.RxRetrofit;
import sysshare.lq.com.penjingwang.utils.PreUtils;




public class AppConfig {

    static void init(Context context){
        //初始化网络框架
        RxRetrofit.getInstance().initRxRetrofit(context, UrlConstainer.baseUrl);
        //初始化缓存
        PreUtils.init(context);
    }

}
