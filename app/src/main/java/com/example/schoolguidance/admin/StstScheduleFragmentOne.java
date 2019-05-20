package com.example.schoolguidance.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schoolguidance.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class StstScheduleFragmentOne extends Fragment {
    private BarChart mBarChart;
    private BarData mBarData;
    private XAxis xAxis;
    private YAxis leftxAxis;
    private YAxis rightxAris;

    private int mSize;
    private String[] mCountList;
    private String[] keys; //  流程名！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！


    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.admin_stst_sche_barchart, null);
        Bundle bundle = getArguments();
        mSize = Integer.valueOf(bundle.getString("stepNum"));
        mCountList = new String[mSize];
        keys = new String[mSize];
        for(int i = 0;i < mSize;i++){
            mCountList[i] = bundle.getString(String.valueOf(i));
            keys[i] = bundle.getString("key"+String.valueOf(i));
        }
        initView();
        initData();
        initBarChart();
        return view;
    }


    private void initView() {
        mBarChart = view.findViewById(R.id.barChart);
    }

    private void initData() {

        // y 轴数据
        ArrayList<BarEntry> yValues = new ArrayList<>();
        // 2.0 ----x 轴数据
        // ArrayList<String> xValues = new ArrayList<>();

        yValues.add(new BarEntry(0,0));
        for (int x = 1; x < mSize; x++) {
            // 2.0 ----xValues.add(String.valueOf(i));
            int y = Integer.valueOf(mCountList[x]);
            yValues.add(new BarEntry(x, y));
        }

        // y 轴数据集
        BarDataSet barDataSet = new BarDataSet(yValues, "人数");
        barDataSet.setColor(Color.rgb(33,150,243));
        barDataSet.setValueTextSize(11f);

        // 2.0 ---- mBarData = new BarData(xValues, barDataSet);
        mBarData = new BarData(barDataSet);
    }

    private void initBarChart() {
        mBarChart.setData(mBarData);
        mBarChart.setDrawGridBackground(false);
        mBarChart.animateX(1000);
        mBarChart.animateY(1000);
        xAxis=mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(12f);
        leftxAxis=mBarChart.getAxisLeft();
        rightxAris=mBarChart.getAxisRight();
        leftxAxis.setAxisMinimum(0f);
        leftxAxis.setTextSize(12f);
        rightxAris.setDrawAxisLine(false);
        rightxAris.setEnabled(false);
        xAxis.setDrawGridLines(false);
        Legend legend=mBarChart.getLegend();
        legend.setTextSize(11f);
        Description xDescription = new Description();
        xDescription.setText("任务号");
        xDescription.setTextSize(12f);
        xDescription.setPosition(950,1480);
        mBarChart.setDescription(xDescription);
        mBarChart.setTouchEnabled(false);
    }
}

