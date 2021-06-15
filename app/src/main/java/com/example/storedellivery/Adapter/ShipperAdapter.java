package com.example.storedellivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShipperAdapter extends BaseAdapter {
    Context context;
    int myLayout;
    List<Shipper> list;

    public ShipperAdapter(Context context, int myLayout, List<Shipper> list) {
        this.context = context;
        this.myLayout = myLayout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(myLayout, null);
        TextView txtName = (TextView)view.findViewById(R.id.tvNameShip);
        ImageView txtStatus = (ImageView)view.findViewById(R.id.tvStatus);
        ImageView iv = (ImageView)view.findViewById(R.id.ivShipper);
        if (list.get(i).getStatus()==0){
            txtStatus.setImageResource(R.drawable.ic_status_on);
        }else {
            txtStatus.setImageResource(R.drawable.ic_status_off);
        }
        txtName.setText(list.get(i).getShipName());
        Picasso.with(context).load(list.get(i).getShipImage()).into(iv);
        return view;
    }
}
