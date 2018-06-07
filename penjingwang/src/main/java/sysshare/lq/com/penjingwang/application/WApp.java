package sysshare.lq.com.penjingwang.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;



public class WApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化内存泄漏检测工具
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
        //初始化App配置
        AppContext.initialize(this);
    }

}
