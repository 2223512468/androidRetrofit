package com.jaja.home.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.jaja.home.retrofit.net.BaseReq;
import com.jaja.home.retrofit.net.BaseResq;
import com.jaja.home.retrofit.net.SessionReq;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2017/12/28.
 */
public class RetfitAct extends Activity {

    private Retrofit retrofit;
    private ApiService api;
    private String baseUrl = "http://192.168.93.21:8080/OkhttpResponse/";
    private String phoneUrl = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=15267185984";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(ApiService.class);
    }

    public void btnClick(View v) {
        switch (v.getId()) {
            case R.id.getBtn:
                try {
                    doGet();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.getBtnMap:
                doGetMap();
                break;
            case R.id.getBtnUrl:
                doGetUrl();
                break;
            case R.id.getBtnHeader:
                doGetHeader();
                break;
            case R.id.postBtnLogin:
                doPost();
                break;
            case R.id.getModelLogin:
                doModelGet();
                break;
            case R.id.getStringLogin:
                doJsonGet();
                break;
            case R.id.getObserLogin:
                getObservable();
                break;
            case R.id.upFile:
                upFile();
                break;
            case R.id.upRequest:
                JsonAndSession();
                break;
        }
    }


    private void doGet() throws UnsupportedEncodingException {
        Call<ResponseBody> call = api.doGet(URLEncoder.encode("燃烧de雪", "utf-8"), "123456");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("print", "get" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    private void doGetMap() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "terry");
        map.put("password", "123456");
        Call<ResponseBody> call = api.doGetMap(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("print", "get" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void doGetUrl() {
        Call<ResponseBody> call = api.doGetUrl(phoneUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("print", "get" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void doGetHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "terry");
        map.put("password", "123456");
        Call<ResponseBody> call = api.doGetHeader(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("print", "get" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public void doPost() {
        Call<ResponseBody> call = api.login("terry", "123456");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("print", "get" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public void doModelGet() {
        Call<ProductModel> call = api.doGetModelUrl();
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                Log.i("print", "success");
                ProductModel model = response.body();
                Log.i("print", model.toString());
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.i("print", "err" + t);
            }
        });
    }


    public void doJsonGet() {
        Call<String> call = api.doStringUrl();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("print", "success" + response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("print", "err" + t);
            }
        });
    }


    public void getObservable() {
        api.getModel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("print", s);
                    }
                });

    }


    public void upFile() {

        RequestBody author = RequestBody.create(MediaType.parse("text/plain"), "terry");

        RequestBody timer = RequestBody.create(MediaType.parse("text/plain"), new Date().toString());

        File upLoadFile = new File(Environment.getExternalStorageDirectory(), "com.jaja.home/homer.txt");
        RequestBody upLoadBody = RequestBody.create(MediaType.parse("multipart/form-data"), upLoadFile);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image", upLoadFile.getName(), upLoadBody);
        Log.i("print", "printFile" + upLoadFile.getName());

        // 执行请求
        Call<ResponseBody> call = api.upload(author, timer, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("print success", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("print error:", t.getMessage());
            }
        });
    }


    private void JsonAndSession() {
        BaseReq req = new BaseReq();
        BaseReq.Content content = new BaseReq.Content();
        content.setId(1);
        content.setName("terry");
        req.setCmd("json");
        req.setContent(content);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(req));

        SessionReq sReq = new SessionReq();
        sReq.setUserId(1);
        sReq.setToken("sssssssssssssssssssssssssss");
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(sReq));

        api.json(requestBody, requestBody1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResq>() {
                    @Override
                    public void onCompleted() {
                        Log.v("print", "session--onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("print", "session--onErr");
                    }

                    @Override
                    public void onNext(BaseResq baseResq) {
                        Log.v("print", "session--onNext");
                    }
                });


    }


}
