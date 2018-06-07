package sysshare.lq.com.penjingwang.net;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sysshare.lq.com.penjingwang.api.ApiServer;
import sysshare.lq.com.penjingwang.net.interceptor.LoadCookieInterceptor;
import sysshare.lq.com.penjingwang.net.interceptor.SaveCookieInterceptor;

public class RxRetrofit {
    private Retrofit retrofit;
    private static ApiServer apiServer;
    private static final class Holder{
        private static final RxRetrofit INSTANCE =new RxRetrofit();
    }

    public static RxRetrofit getInstance() {
        return Holder.INSTANCE;
    }

    public void initRxRetrofit(final Context context, String baseUrl) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .cache(new Cache(context.getExternalFilesDir("http_cache"),10<<20))
                .addInterceptor(new SaveCookieInterceptor())
                .addInterceptor(new LoadCookieInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServer = retrofit.create(ApiServer.class);

    }

    public static ApiServer Api() {
        if (apiServer == null)
            throw new IllegalStateException("You must invoke init method first in Application");
        return apiServer;
    }
}
