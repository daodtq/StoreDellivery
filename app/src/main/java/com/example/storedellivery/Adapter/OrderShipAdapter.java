package com.example.storedellivery.Adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.Model.Order;
import com.example.storedellivery.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderShipAdapter extends RecyclerView.Adapter<OrderShipAdapter.ViewHolder> {
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    DateFormat df = new DateFormat();
    Context context;
    ArrayList<Order> list;

    public OrderShipAdapter(Context context, ArrayList<Order> list){
        this.context = context;
        this.list = list;
        df.format("kk:mm:ss", new java.util.Date());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_one_item_ship, parent, false);
        return new OrderShipAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderShipAdapter.ViewHolder holder, int position) {
        holder.stt.setText(String.valueOf(position+1));
        holder.address.setText(list.get(position).getAddress());
        holder.money.setText(formatter.format(list.get(position).getTotalMoney())+ " VNĐ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stt,address,money;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.stt);
            address = itemView.findViewById(R.id.tvAddress);
            money = itemView.findViewById(R.id.tvTotalMoney);
        }
    }
}
