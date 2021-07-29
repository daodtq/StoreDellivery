package com.example.storedellivery.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.storedellivery.Activity.SendOTPActivity;
import com.example.storedellivery.DbHelper.DbHelper;
import com.example.storedellivery.R;

import java.text.DecimalFormat;


public class MoreFragment extends Fragment {
    RelativeLayout btnDonHang, btnThongKe;
    TextView name, address, phone;
    ImageView logout;
    DbHelper dbHelper;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public MoreFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        name = view.findViewById(R.id.tvStoreName);
        address = view.findViewById(R.id.tvStoreAddress);
        phone = view.findViewById(R.id.tvStorePhone);
        logout = view.findViewById(R.id.ivLogout);
        btnDonHang = view.findViewById(R.id.donHang);
        btnThongKe = view.findViewById(R.id.thongKe);

        dbHelper = new DbHelper(getActivity());
        name.setText(dbHelper.getStore().getStoreName());
        address.setText(dbHelper.getStore().getStoreAddress());
        phone.setText("0" + formatter.format(dbHelper.getStore().getStorePhone()));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.alert_status, null);
                TextView ok = view1.findViewById(R.id.tvOK);
                TextView note = view1.findViewById(R.id.tvQuestion);
                TextView cancel = view1.findViewById(R.id.tvCancel);
                ok.setText("Đăng xuất");
                cancel.setText("Cancel");
                note.setText("Bạn muốn đăng xuất khỏi cửa hàng?");
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbHelper.delete();
                        Intent i = new Intent(getActivity(), SendOTPActivity.class);
                        startActivity(i);
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder.dismiss();
                    }
                });
                builder.setView(view1);
                builder.show();

            }
        });
        btnDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllOrderFragment fragment = new AllOrderFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                );
                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThongKeFragment fragment = new ThongKeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                );
                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}