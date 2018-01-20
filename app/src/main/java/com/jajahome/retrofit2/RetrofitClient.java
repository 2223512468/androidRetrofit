package com.jajahome.retrofit2;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2018/1/2.
 */
public class RetrofitClient {

    private static Context mContext;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private ApiService api;
    private String baseUrl = "http://192.168.93.146:8080/OkhttpResponse/";

    public static RetrofitClient INSTANCE = new RetrofitClient();

    public static RetrofitClient getInstance(Context mContext) {
        if (RetrofitClient.mContext == null) {
            RetrofitClient.mContext = mContext;
        }
        return INSTANCE;
    }

    public RetrofitClient() {

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MILLISECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public RetrofitClient create() {
        if (api == null) {
            api = retrofit.create(ApiService.class);
        }
        return this;
    }

    public void getDatas(String jsonUrl, Action1<String> action1) {
        Observable<String> json = api.getJson(jsonUrl);
        json.
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

    }

}
