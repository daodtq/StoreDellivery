package com.example.storedellivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.Model.DetailOrder;
import com.example.storedellivery.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailOrderAdapter extends RecyclerView.Adapter<DetailOrderAdapter.ViewHolder> {
    Context context;
    ArrayList<DetailOrder> list;
    public DetailOrderAdapter(Context context, ArrayList<DetailOrder> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DetailOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_one_item, parent, false);
        return new DetailOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailOrderAdapter.ViewHolder holder, int position) {
        Picasso.with(context).load(list.get(position).getProductImage()).into(holder.iv);
        holder.name.setText(list.get(position).getProductName());
        holder.type.setText(list.get(position).getTypeName());
        holder.amount.setText(String.valueOf(list.get(position).getQuantity()));
        holder.tvSize.setText(String.valueOf(list.get(position).getSizeName()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, amount, tvSize;
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSize = itemView.findViewById(R.id.tvSize);
            iv = itemView.findViewById(R.id.ivProduct);
            name = itemView.findViewById(R.id.tvNameProduct);
            type = itemView.findViewById(R.id.tvTypeProduct);
            amount = itemView.findViewById(R.id.tvAmount);
        }
    }
}
