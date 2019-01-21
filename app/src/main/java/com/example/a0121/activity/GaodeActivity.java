package com.example.a0121.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.example.a0121.R;

public class GaodeActivity extends AppCompatActivity {

    private MapView mapview;
    AMap aMap;
    Bundle savedInstanceState = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaode);

        initView();
        initData();
    }

    private void initView() {
        mapview = findViewById(R.id.mapview);
        mapview.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        if (aMap == null) {
            aMap = mapview.getMap();
        }

    }

    private void initData() {

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapview.onSaveInstanceState(outState);
    }
}
