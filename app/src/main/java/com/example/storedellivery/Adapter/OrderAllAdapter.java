package com.example.storedellivery.Adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
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

public class OrderAllAdapter extends RecyclerView.Adapter<OrderAllAdapter.ViewHolder> {
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    DateFormat df = new DateFormat();
    Context context;
    ArrayList<Order> list;
    private OrderAllAdapter.OnOrderClickListener mListener;
    public void setOnOrderItemClickListener (OrderAllAdapter.OnOrderClickListener onOrderItemClickListener){
        mListener = onOrderItemClickListener;
    }
    public OrderAllAdapter(Context context, ArrayList<Order> list){
        this.context = context;
        this.list = list;
        df.format("kk:mm:ss", new java.util.Date());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_all_one_item, parent, false);
        return new OrderAllAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAllAdapter.ViewHolder holder, int position) {
        holder.id.setText(list.get(position).getOrderID());
        holder.address.setText(list.get(position).getAddress());
        holder.money.setText(formatter.format(list.get(position).getTotalMoney())+ " VNĐ");
        Log.d("abc123", list.get(position).getStatus());
        String s  =list.get(position).getStatus();
        if (s.equals("Giao hàng thành công")){
            holder.ivStatus.setImageResource(R.drawable.ic_status);
        }else if (s.equals("Đã hủy đơn hàng")||s.equals("Giao hàng thất bại")){
            holder.ivStatus.setImageResource(R.drawable.ic_status_off);
        }else {
            holder.ivStatus.setImageResource(R.drawable.ic_status_on);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,address,money;
        ImageView ivStatus;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvOrderID);
            address = itemView.findViewById(R.id.tvAddress);
            money = itemView.findViewById(R.id.tvTotalMoney);
            ivStatus = itemView.findViewById(R.id.ivStatus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onOrderItemClick(getAdapterPosition());
                }
            });
        }
    }
    public interface OnOrderClickListener {
        void onOrderItemClick(int position);
    }
}
