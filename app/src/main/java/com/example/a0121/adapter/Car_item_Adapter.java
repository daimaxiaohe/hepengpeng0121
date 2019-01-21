package com.example.a0121.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a0121.R;
import com.example.a0121.bean.ShowCarBean;

import java.util.List;

/**
 * はすてすゃの
 * 2019-01-21.
 */
public class Car_item_Adapter extends RecyclerView.Adapter<Car_item_Adapter.MyHolder> {
    private Context context;
    private List<ShowCarBean.Datas.ListBean> list;

    public Car_item_Adapter(Context context,List<ShowCarBean.Datas.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item_show,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        if (list.size()>0){
            myHolder.car_item_price.setText(list.get(i).price+"");
            myHolder.car_item_title.setText(list.get(i).title+"");
            myHolder.car_item_check.setChecked(list.get(i).ischildchecked);
            String[] split = list.get(i).images.split("\\|");
            Glide.with(context).load(split[0]).into(myHolder.car_item_img);
            myHolder.car_item_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(i).ischildchecked=myHolder.car_item_check.isChecked();
                    if (setitemClick!=null){
                        setitemClick.itemclick();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private CheckBox car_item_check;
        private ImageView car_item_img;
        private TextView car_item_price,car_item_title;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            car_item_check = itemView.findViewById(R.id.car_item_check);
            car_item_img = itemView.findViewById(R.id.car_item_img);
            car_item_price = itemView.findViewById(R.id.car_item_price);
            car_item_title = itemView.findViewById(R.id.car_item_title);
        }
    }
    public SetitemClick setitemClick;

    public void setSetitemClick(SetitemClick setitemClick) {
        this.setitemClick = setitemClick;
    }

    interface SetitemClick{
        void itemclick();
    }
}
