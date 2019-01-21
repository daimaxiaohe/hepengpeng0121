package com.example.a0121.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.a0121.R;
import com.example.a0121.adapter.CarAdapter;
import com.example.a0121.api.CarApi;
import com.example.a0121.bean.ShowCarBean;
import com.example.a0121.contract.IContract;
import com.example.a0121.presenter.CarPresenter;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2019-01-21.
 */
public class HomeFragment extends Fragment implements IContract.IView,CarAdapter.CaritemClick {
    private RecyclerView home_rv;
    private TextView textprice;
    private CheckBox check_btn;
    private CarPresenter carPresenter;
    private CarAdapter carAdapter1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homefragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }
    //操作视图的方法a
    private void initView(View view) {
        //获取ID
        home_rv = view.findViewById(R.id.home_rv);
       check_btn= view.findViewById(R.id.check_btn);
        textprice = view.findViewById(R.id.text_price);
        //获取p层的实力
        carPresenter = new CarPresenter(this);
        home_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    //操作数据的方法
    private void initData() {
        HashMap<String,String> map = new HashMap<>();
        map.put("uid","71");
        carPresenter.showcar(CarApi.CAR_API,map);
        check_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                double setcount = carAdapter1.setcount(isChecked);
                textprice.setText("合计 ￥："+setcount+"");
            }
        });
    }
    //成功的方法
    @Override
    public void success(ShowCarBean showCarBean) {
        if (showCarBean!=null){
            carAdapter1 = new CarAdapter(getActivity(), showCarBean);
            carAdapter1.setCaritemClick(this);
            home_rv.setAdapter(carAdapter1);
        }
    }
    //失败的方法
    @Override
    public void fail(String error) {

    }
    //接口监听
    @Override
    public void Onclick() {
        double setprice = carAdapter1.setprice();
        textprice.setText("合计 ￥："+setprice);
    }
}
