package com.example.storedellivery.Fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.Adapter.OrderShipAdapter;
import com.example.storedellivery.Model.Order;
import com.example.storedellivery.Model.Shipper;
import com.example.storedellivery.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

public class ShipGPSFragment extends Fragment {

    private static final int REQUEST_CALL = 1;
    Shipper shipper;
    ArrayList<Order> orders;
    ImageView back;
    Button donHang, call;
    TextView nameShip, phoneShip;
    public static GoogleMap mMap;
    SupportMapFragment supportMapFragment;
    Marker marker;
    RecyclerView rcv;
    OrderShipAdapter adapter;

    public ShipGPSFragment(Shipper shipper, ArrayList<Order> orders) {
        this.shipper = shipper;
        this.orders = orders;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shipgps, container, false);
        back = view.findViewById(R.id.ivBack);
        nameShip = view.findViewById(R.id.nameShip);
        phoneShip = view.findViewById(R.id.phoneShip);
        donHang = view.findViewById(R.id.btnDonHang);
        call = view.findViewById(R.id.btnCall);
        rcv = view.findViewById(R.id.rcvBottomSheet);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(layoutManager);
        adapter = new OrderShipAdapter(getActivity(),orders);
        rcv.setAdapter(adapter);


        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        showMap();

        LinearLayout layoutBottomSheet = view.findViewById(R.id.bottom_sheet);
        BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        nameShip.setText(shipper.getShipName());
        phoneShip.setText("0"+shipper.getShipPhone());


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                Fragment myFragment = new ShipperFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, myFragment).addToBackStack(null).commit();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }
                AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.alert_call, null);
                TextView ok = view1.findViewById(R.id.tvOK);
                TextView cancel = view1.findViewById(R.id.tvCancel);
                cancel.setText("Hủy");
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String dial = "tel:" + "0" + shipper.getShipPhone();
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                        builder.dismiss();
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

        donHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        return view;
    }

    private void showMap() {
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.shipper);
                BitmapDescriptor icon1 = BitmapDescriptorFactory.fromResource(R.drawable.point);
                mMap = googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                LatLng latLng = new LatLng(shipper.getShipLat(), shipper.getShipLong());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Shipper");
                markerOptions.icon(icon);
                for (int i = 0; i < orders.size(); i++){
                    LatLng position = new LatLng(orders.get(i).getOrderLat(),orders.get(i).getOrderLong());
                    MarkerOptions order = new MarkerOptions();
                    order.position(position);
                    order.title("ĐƠn hàng " + (i + 1));
                    order.icon(icon1);
                    Marker marker1 = mMap.addMarker(order);
                }
                marker = mMap.addMarker(markerOptions);
                UiSettings uisetting = mMap.getUiSettings();
                uisetting.setCompassEnabled(true);
                uisetting.setScrollGesturesEnabled(true);
                uisetting.setTiltGesturesEnabled(true);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

            }
        });
    }

}