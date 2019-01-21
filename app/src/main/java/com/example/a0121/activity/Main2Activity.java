package com.example.a0121.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a0121.R;
import com.example.a0121.adapter.PageShowAdapter;

public class Main2Activity extends AppCompatActivity {

    private ViewPager viewpage;
    private BottomNavigationView navigation;
    private PageShowAdapter pageShowAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        initData();

    }
    //操作视图的方法
    private void initView() {
        //获取资源ID
        viewpage = findViewById(R.id.viewpage);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //创建一个适配器
        pageShowAdapter = new PageShowAdapter(getSupportFragmentManager());
        viewpage.setAdapter(pageShowAdapter);
    }
    //操作数据的方法
    private void initData() {

    }

    //导航栏
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                  viewpage.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewpage.setCurrentItem(1);
                    return true;
            }
            return false;
        }
    };
}
