package com.example.storedellivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.DAO.OrderDAO;
import com.example.storedellivery.DAO.ShipDAO;
import com.example.storedellivery.DAO.StoreDAO;
import com.example.storedellivery.Fragment.MoreFragment;
import com.example.storedellivery.Fragment.ShipGPSFragment;
import com.example.storedellivery.Model.ListProduct;
import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShipListAdapter extends RecyclerView.Adapter<ShipListAdapter.ViewHolder> {
    Context context;
    ArrayList<Shipper> list;
    OrderDAO dao;

    public ShipListAdapter(Context context, ArrayList<Shipper> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shipper_one_item, parent, false);
        return new ShipListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShipListAdapter.ViewHolder holder, int position) {
        String s = list.get(position).getStatus();
        if (s.equals("Trực Tuyến")){
            holder.ivStatus.setImageResource(R.drawable.ic_status_on);
            holder.tvStatus.setText("Trực Tuyến");
        }else {
            holder.ivStatus.setImageResource(R.drawable.ic_status_off);
            holder.tvStatus.setText("Ngoại Tuyến");
        }
        Picasso.with(context).load(list.get(position).getShipImage()).into(holder.ivShipper);
        holder.tvName.setText(list.get(position).getShipName());
        holder.tvPhone.setText(String.valueOf(list.get(position).getShipPhone()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivStatus, ivShipper;
        TextView tvStatus, tvName, tvPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivShipper = itemView.findViewById(R.id.ivShipper);
            ivStatus = itemView.findViewById(R.id.ivStatus);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvName = itemView.findViewById(R.id.nameShip);
            tvPhone = itemView.findViewById(R.id.phoneShip);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) context;
                    dao = new OrderDAO(context);
                    Fragment myFragment = new ShipGPSFragment(dao.getShip(list.get(getAdapterPosition()).getShipID()),dao.getOrderFromShip(list.get(getAdapterPosition()).getShipID()));
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, myFragment).addToBackStack(null).commit();
                }
            });
        }
    }
}
