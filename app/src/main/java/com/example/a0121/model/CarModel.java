package com.example.a0121.model;

import android.os.Handler;

import com.example.a0121.contract.IContract;
import com.example.a0121.contract.ResponceCallBack;
import com.example.a0121.utils.OkHttpCallBack;
import com.example.a0121.utils.OkHttpUtil;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2019-01-21.
 */
public class CarModel implements IContract.IModel {
    Handler handler = new Handler();
    //购物车展示的方法
    @Override
    public void showcar(String uri, HashMap<String, String> map, final ResponceCallBack callBack) {
        OkHttpUtil.getInstance().dopost(uri, map, new OkHttpCallBack() {
            @Override
            public void success(final String result) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success(result);
                        }
                    });
            }

            @Override
            public void fail(final String error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.fail(error);
                    }
                });
            }
        });
    }
}
