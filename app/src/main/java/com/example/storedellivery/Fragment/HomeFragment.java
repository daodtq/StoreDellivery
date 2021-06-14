package com.example.storedellivery.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedellivery.Adapter.StoreAdapter;
import com.example.storedellivery.DAO.StoreDAO;
import com.example.storedellivery.DbHelper.DbHelper;
import com.example.storedellivery.Model.ListProduct;
import com.example.storedellivery.Model.StatusModel;
import com.example.storedellivery.Model.TypeProduct;
import com.example.storedellivery.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    LinearLayout ln1;
    List<TypeProduct> typeProductList;
    List<ListProduct> listProductList;
    StoreDAO dao;
    StoreAdapter adapter;
    DbHelper dbHelper ;
    TextView nameStore;
    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ln1 = view.findViewById(R.id.ln1);
        nameStore = view.findViewById(R.id.nameStore);
        dao = new StoreDAO(getActivity());
        dbHelper= new DbHelper(getActivity());
        nameStore.setText(dbHelper.getStore().getStoreName());
        StatusModel statusModel = dao.getListProduct(dbHelper.getStore().getStoreID());
        typeProductList = statusModel.getTypeProduct();
        listProductList = statusModel.getListProduct();
        for (int i =0; i<typeProductList.size(); i++){
            ArrayList<ListProduct> list= new ArrayList<>();
            LayoutInflater inflater1 = LayoutInflater.from(getActivity());
            LinearLayout line2 = (LinearLayout) inflater1.inflate(R.layout.typeproduct_layout, null, false);
            LinearLayout lineType = line2.findViewById(R.id.lineType);
            TextView textView = line2.findViewById(R.id.tvTypeProduct);
            textView.setText(String.valueOf(typeProductList.get(i).getTypeName()));
            for (int j =0; j<listProductList.size();j++){
                if (typeProductList.get(i).getTypeID()==listProductList.get(j).getTypeID()){
                    list.add(listProductList.get(j));
                }
            }
            adapter = new StoreAdapter(getActivity(),list);
            RecyclerView recyclerView = new RecyclerView(getActivity());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            lineType.addView(recyclerView);
            ln1.addView(line2);
        }
        return view;
    }
}