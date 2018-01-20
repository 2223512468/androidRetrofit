package com.jaja.home.retrofit;

import com.jaja.home.retrofit.net.BaseResq;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by ${Terry} on 2017/12/28.
 */
public interface ApiService {

    /**
     * BaseUri: http://192.168.93.71:8080/OkhttpResponse/
     *
     * @param username
     * @param password
     * @return retrofit 组装成 : http://192.168.93.71:8080/OkhttpResponse/terry?username=?&password=?
     */

    @GET("terry")
    Call<ResponseBody> doGet(@Query("username") String username, @Query("password") String password);

    /**
     * Map 封装 Get 参数
     *
     * @param map
     * @return
     */
    @GET("terry")
    Call<ResponseBody> doGetMap(@QueryMap Map<String, String> map);

    /**
     * @param path 多个uri相同的部分
     * @param map
     * @return
     */
    @GET("{path}")
    Call<ResponseBody> doGetPath(@Path("path") String path, @QueryMap Map<String, String> map);

    /**
     * 覆盖baseUrl
     *
     * @param url
     * @return
     */
    @GET
    Call<ResponseBody> doGetUrl(@Url String url);


    /**
     * 带请求头
     *
     * @param
     * @return
     */
    @Headers({"head1:value1,head2:value2"})
    @GET("tom")
    Call<ResponseBody> doGetHeader(@QueryMap Map<String, String> map);

    /**
     * POST请求
     *
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(@Field("username") String username, @Field("password") String password);


    @GET("model")
    Call<ProductModel> doGetModelUrl();

    @GET("model")
    Call<String> doStringUrl();

    @GET("model")
    Observable<String> getModel();


    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("author") RequestBody author, @Part("timer") RequestBody timer, @Part MultipartBody.Part file);


    @Multipart
    @POST("json")
    Observable<BaseResq> json(@Part("json") RequestBody json, @Part("session") RequestBody session);


}
