package com.example.a0121.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a0121.R;
import com.example.a0121.activity.GaodeActivity;
import com.example.a0121.activity.QQActivity;
import com.example.a0121.activity.ShareActivity;

/**
 * はすてすゃの
 * 2019-01-21.
 */
public class MyFragment extends Fragment{
    private Button share,gaode;
    private ImageView qqlogin;
    private String icon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.myfragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }
    //操作视图的方法a
    private void initView(View view) {
        qqlogin = view.findViewById(R.id.qqlogin);
        share = view.findViewById(R.id.share);
        gaode = view.findViewById(R.id.gaode);
        //qq登录
        qqlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QQActivity.class);
                startActivityForResult(intent,101);
            }
        });
        //分享
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShareActivity.class);
                intent.putExtra("icon",icon);
                startActivityForResult(intent,102);
            }
        });
        //高德地图
        gaode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GaodeActivity.class);
                startActivity(intent);
            }
        });
    }
    //操作数据的方法
    private void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101&&resultCode==103){
            icon = data.getStringExtra("icon");
            Glide.with(getActivity()).load(icon).into(qqlogin);
        }
    }
}
