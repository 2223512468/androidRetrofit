package com.jajahome.retrofit2;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by ${Terry} on 2018/1/2.
 */
public interface ApiService {

    @GET
    Observable<String> getJson(@Url String url);

}


