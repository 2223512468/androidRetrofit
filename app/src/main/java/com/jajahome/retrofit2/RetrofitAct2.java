package com.jajahome.retrofit2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.jaja.home.retrofit.R;

import rx.functions.Action1;

/**
 * Created by ${Terry} on 2018/1/2.
 */
public class RetrofitAct2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        RetrofitClient.getInstance(this)
                .create()
                .getDatas("model", new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i("print", s);
                    }
                });
    }
}
