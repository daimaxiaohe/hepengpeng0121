package com.example.a0121.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * はすてすゃの
 * 2019-01-21.
 */
public class OkHttpUtil {
    //单例模式
    private static OkHttpUtil instance;
    private final OkHttpClient okhttp;

    private OkHttpUtil(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttp = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(interceptor)
                //超时
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public static OkHttpUtil getInstance(){
        if (instance==null){
            synchronized (OkHttpUtil.class){
                if (instance==null){
                    instance = new OkHttpUtil();
                }
            }
        }
        return instance;
    }

    //dopost
    public void  dopost(String uri, HashMap<String,String> map, final OkHttpCallBack callBack){
        //原生表单
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> p : map.entrySet()) {
            formBody.add(p.getKey(),p.getValue());
        }
        //请求对象
        Request request = new Request.Builder().url(uri).post(formBody.build()).build();
        okhttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.fail("网络错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.success(response.body().string());
            }
        });
    }
}
