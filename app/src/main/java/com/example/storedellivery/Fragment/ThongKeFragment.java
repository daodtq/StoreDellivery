package com.example.storedellivery.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.storedellivery.DAO.OrderDAO;
import com.example.storedellivery.DbHelper.DbHelper;
import com.example.storedellivery.MainActivity;
import com.example.storedellivery.Model.Total;
import com.example.storedellivery.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class ThongKeFragment extends Fragment {

    OrderDAO orderDAO;
    DbHelper dbHelper;
    TextView tvTotal, tvMonth, tvToday;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public ThongKeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        BarChart chart = view.findViewById(R.id.Barchart);
        orderDAO = new OrderDAO(getActivity());
        dbHelper= new DbHelper(getActivity());
        tvToday = view.findViewById(R.id.totalToday);
        tvTotal = view.findViewById(R.id.totalMonth);
        tvMonth = view.findViewById(R.id.month);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        ArrayList<Total> list = orderDAO.getTotal(dbHelper.getStore().getStoreID());
        tvMonth.setText(month+ ": ");
        tvToday.setText(formatter.format(orderDAO.getTotalDate(dbHelper.getStore().getStoreID())) + " VNĐ");
        for (int i = 0; i< list.size(); i++){
            if (list.get(i).getMonth()==month){
                tvTotal.setText(formatter.format(list.get(i).getTotal()) + " VNĐ");
                break;
            }
        }
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        LimitLine upper_limit = new LimitLine(65f, "Max");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f,10f,0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(15f);

        LimitLine lower_limit = new LimitLine(0f, "Min");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f,10f,0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        upper_limit.setTextSize(15f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);

        leftAxis.enableGridDashedLine(10f,10f,0f);
        leftAxis.setDrawLimitLinesBehindData(true);

        chart.getAxisRight().setEnabled(false);


        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int  i= 0; i< list.size(); i++){
            entries.add(new BarEntry(i,list.get(i).getTotal()));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Đơn vị: nghìn");
        dataSet.setColor(Color.rgb(252,96,17));
        dataSet.setValueTextSize(10f);
        dataSet.setValueTextColor(Color.BLACK);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        BarData data = new BarData(dataSets);
        chart.setData(data);
        chart.invalidate();


        return view;
    }


}