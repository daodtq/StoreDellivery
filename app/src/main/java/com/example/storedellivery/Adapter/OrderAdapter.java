package com.example.storedellivery.Adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.Fragment.DetailOrderFragment;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    DateFormat df = new DateFormat();
    Context context;
    ArrayList<Order> list;
    private OrderAdapter.OnOrderClickListener mListener;
    public void setOnOrderItemClickListener (OrderAdapter.OnOrderClickListener onOrderItemClickListener){
        mListener = onOrderItemClickListener;
    }
    public OrderAdapter(Context context, ArrayList<Order> list){
        this.context = context;
        this.list = list;
        df.format("kk:mm:ss", new java.util.Date());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_one_item, parent, false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.id.setText(list.get(position).getOrderID());
        holder.address.setText(list.get(position).getAddress());
        holder.money.setText(formatter.format(list.get(position).getTotalMoney())+ " VNĐ");
        String status = list.get(position).getStatus();
        if (status.equals("Đang đợi xác nhận")){
            holder.ivStatus.setImageResource(R.drawable.ic_status_on);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,address,money;
        TextView status;
        ImageView ivStatus;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            ivStatus = itemView.findViewById(R.id.status);
            id = itemView.findViewById(R.id.tvOrderID);
            address = itemView.findViewById(R.id.tvAddress);
            money = itemView.findViewById(R.id.tvTotalMoney);
            status = itemView.findViewById(R.id.btnStatus);
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
