package com.example.a0121.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.a0121.R;
import com.example.a0121.bean.ShowCarBean;

/**
 * はすてすゃの
 * 2019-01-21.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyHolder> {
    private Context context;
    private ShowCarBean showCarBean;

    public CarAdapter(Context context, ShowCarBean showCarBean) {
        this.context = context;
        this.showCarBean = showCarBean;
    }

    public double setcount(boolean ischecked){
        double totalprice=0.0;
        for (ShowCarBean.Datas datum : showCarBean.data) {
            datum.ischecked=ischecked;
            for (ShowCarBean.Datas.ListBean listBean : datum.list) {
                listBean.ischildchecked=ischecked;
                if (listBean.ischildchecked==true){
                    totalprice+=listBean.price;
                }
            }
        }
        notifyDataSetChanged();
        return totalprice;
    }

    public double setprice(){
        double totalprice=0.0;
        for (ShowCarBean.Datas datum : showCarBean.data) {
            for (ShowCarBean.Datas.ListBean listBean : datum.list) {
                if (listBean.ischildchecked==true){
                    totalprice+=listBean.price;
                }
            }
        }
        notifyDataSetChanged();
        return totalprice;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.carshow,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        if (showCarBean.data.size()>0){
            final ShowCarBean.Datas datas = showCarBean.data.get(i);
            myHolder.car_check.setChecked(datas.ischecked);
            myHolder.car_name.setText(datas.sellerName);
            Car_item_Adapter car_item_adapter = new Car_item_Adapter(context, datas.list);
            myHolder.car_item_rv.setAdapter(car_item_adapter);
            myHolder.car_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCarBean.data.get(i).ischecked=myHolder.car_check.isChecked();
                    for (ShowCarBean.Datas.ListBean listBean : showCarBean.data.get(i).list) {
                        listBean.ischildchecked=myHolder.car_check.isChecked();
                    }
                    caritemClick.Onclick();
                }
            });
            car_item_adapter.setSetitemClick(new Car_item_Adapter.SetitemClick() {
                @Override
                public void itemclick() {
                    boolean temp=true;
                    for (ShowCarBean.Datas datum : showCarBean.data) {
                        for (ShowCarBean.Datas.ListBean listBean : datas.list) {
                            if (listBean.ischildchecked==false){
                                temp=false;
                                datas.ischecked=false;
                            }
                        }
                    }
                    if (temp){
                        showCarBean.data.get(i).ischecked=true;
                    }
                    notifyDataSetChanged();
                    caritemClick.Onclick();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return showCarBean==null?0:showCarBean.data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private RecyclerView car_item_rv;
        private TextView car_name;
        private CheckBox car_check;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            car_item_rv = itemView.findViewById(R.id.car_item_rv);
            car_name = itemView.findViewById(R.id.car_name);
            car_check = itemView.findViewById(R.id.car_check);
            car_item_rv.setLayoutManager(new LinearLayoutManager(context));
        }
    }
    public CaritemClick caritemClick;

    public void setCaritemClick(CaritemClick caritemClick) {
        this.caritemClick = caritemClick;
    }

    public interface CaritemClick{
        void Onclick();
    }
}
