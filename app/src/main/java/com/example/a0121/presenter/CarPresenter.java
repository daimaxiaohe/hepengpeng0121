package com.example.a0121.presenter;

import com.example.a0121.bean.ShowCarBean;
import com.example.a0121.contract.IContract;
import com.example.a0121.contract.ResponceCallBack;
import com.example.a0121.model.CarModel;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2019-01-21.
 */
public class CarPresenter extends IContract.IPresenter {
    private CarModel model;
    private IContract.IView view;

    public CarPresenter(IContract.IView view) {
        model = new CarModel();
        this.view = view;
    }

    @Override
    public void showcar(String uri, HashMap<String,String> map) {
        model.showcar(uri, map, new ResponceCallBack() {
            @Override
            public void success(String result) {
                if (view!=null){
                    ShowCarBean showCarBean = new Gson().fromJson(result, ShowCarBean.class);
                    view.success(showCarBean);
                }
            }

            @Override
            public void fail(String error) {
                    if (view!=null){
                        view.fail(error);
                    }
            }
        });
    }
}
