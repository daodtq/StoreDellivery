package com.example.storedellivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.DAO.StoreDAO;
import com.example.storedellivery.Model.ListProduct;
import com.example.storedellivery.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    DecimalFormat formatter = new DecimalFormat("###,###,###");
    ArrayList<ListProduct> list;
    Context context;
    StoreDAO dao;

    public StoreAdapter(Context context, ArrayList<ListProduct> list) {
        this.context = context;
        this.list = list;
        dao = new StoreDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_one_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.ViewHolder holder, int position) {
        holder.nameProduct.setText(list.get(position).getProductName());
        if (list.get(position).getStatus()==0){
            holder.swProduct.setChecked(true);
        }
        holder.swProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    dao.changeStatus(list.get(position).getStoreID(), list.get(position).getProductID(),0);
                } else {
                    dao.changeStatus(list.get(position).getStoreID(), list.get(position).getProductID(),1);
                }
            }
        });
        Picasso.with(context).load(list.get(position).getProductImage()).into(holder.ivProduct);
        holder.tvPrice.setText(formatter.format(list.get(position).getProductPrice())+ " VNĐ");
        holder.tvNote.setText(list.get(position).getProductNote());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameProduct, tvPrice, tvNote;
        ImageView ivProduct;
        SwitchCompat swProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNote = itemView.findViewById(R.id.tvNote);
            nameProduct = itemView.findViewById(R.id.tvNameProduct);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            swProduct = itemView.findViewById(R.id.swStatus);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
