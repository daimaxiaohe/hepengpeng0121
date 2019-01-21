package com.example.a0121.contract;

import com.example.a0121.bean.ShowCarBean;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2019-01-21.
 */
public interface IContract {
    //抽象类
    abstract class IPresenter{
        public abstract void showcar(String uri,HashMap<String,String> map);
    }
    interface  IModel{
        void showcar(String uri, HashMap<String,String> map,ResponceCallBack callBack);
    }
    interface IView{
        void success(ShowCarBean ShowCarBean);
        void fail(String error);
    }
}
