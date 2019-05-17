package com.example.schoolguidance.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.schoolguidance.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;


public class StstSchedule extends AppCompatActivity {

    private BarChart mBarChart;
    private BarData mBarData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_stst_sche);

        initView();
        initData();
        initBarChart();
    }

    private void initView() {
        mBarChart = findViewById(R.id.barChart);
    }

    private void initData() {

        // y 轴数据
        ArrayList<BarEntry> yValues = new ArrayList<>();
        // 2.0 ----x 轴数据
        // ArrayList<String> xValues = new ArrayList<>();

        for (int x = 0; x < 10; x++) {
            // 2.0 ----xValues.add(String.valueOf(i));
            float y = (float) (Math.random() * 10);
            yValues.add(new BarEntry(x, y));
        }

        // y 轴数据集
        BarDataSet barDataSet = new BarDataSet(yValues, "条形图");

        // 2.0 ---- mBarData = new BarData(xValues, barDataSet);
        mBarData = new BarData(barDataSet);
    }

    private void initBarChart() {
        mBarChart.setData(mBarData);
    }
}
