package com.example.schoolguidance.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schoolguidance.R;
import com.example.schoolguidance.tool.HttpTool;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static de.greenrobot.event.EventBus.TAG;

public class StstScheduleFragmentTwo extends Fragment {
    private View view;
    private PieChart mPieChart;

    private static final int MESS_TOTAL_STU = 500;
    private static final int MESS_TOTAL_TASK = 501;
    private static final int MESS_TASK_LIST = 502;
    private static final int MESS_NUM = 503;
    private int total_stu;
    private int total_task;
    List<String> dataList = new ArrayList<>();
    List<Float> datal = new ArrayList<>();
    List<PieEntry> mPie = new ArrayList<>();

    private int mSize;
    private String[] mCountList;
    private int stuNum = 0;
    private String[] keys;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.admin_stst_sche_piechart, null);
        mPieChart = view.findViewById(R.id.pie_chart);


        Bundle bundle = getArguments();
        mSize = Integer.valueOf(bundle.getString("stepNum"));
        mCountList = new String[mSize];
        keys = new String[mSize];
        stuNum = Integer.valueOf(bundle.getString("stuNum"));
        for (int i = 0; i < mSize; i++) {
            mCountList[i] = bundle.getString(String.valueOf(i));
            keys[i] = bundle.getString("key" + String.valueOf(i));
        }
        showPieChart(mPieChart, getPieChartData());
        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                // e.getX()方法得到x数据
                PieEntry pieEntry = (PieEntry) e;
                Log.d(TAG, "-->value" + pieEntry.getValue() + "->x" + pieEntry.getX() + "->y" + pieEntry.getY());
            }

            @Override
            public void onNothingSelected() {
            }
        });

        return view;
    }

    private List<PieEntry> getPieChartData() {



        for (int i = 0; i < mSize; i++) {
            float value = Float.valueOf(mCountList[i]);
//            for (int l = i + 1; l < mSize; l++) {
//                value -=Float.valueOf(mCountList[l]);
//            }
            value = value/ Float.valueOf(stuNum);
            value *= 100;
            datal.add(value);
            dataList.add(keys[i]);
        }
        for (int i = 0; i < dataList.size(); i++) {
            // 参数1为 value，参数2为 data。
            // 如 PieEntry(0.15F, "90分以上");  表示90分以上的人占比15%。
            PieEntry pieEntry = new PieEntry(datal.get(i), dataList.get(i));
            pieEntry.setX(1f);
            mPie.add(pieEntry);
        }
        return mPie;
    }

    private void showPieChart(PieChart pieChart, List<PieEntry> pieList) {
        PieDataSet dataSet = new PieDataSet(pieList, "Label");

        // 设置颜色list，让不同的块显示不同颜色，下面是我觉得不错的颜色集合，比较亮
        ArrayList<Integer> colors = new ArrayList<Integer>();
        int[] MATERIAL_COLORS = {
                Color.rgb(200, 172, 255)
        };
        for (int c : MATERIAL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);

        // 设置描述，我设置了不显示，因为不好看，你也可以试试让它显示，真的不好看
        Description description = new Description();
        description.setEnabled(false);
        pieChart.setDescription(description);
        //设置半透明圆环的半径, 0为透明
        pieChart.setTransparentCircleRadius(0f);

        //设置初始旋转角度
        pieChart.setRotationAngle(-15);

        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(80f);

        //设置连接线的颜色
        dataSet.setValueLineColor(Color.LTGRAY);
        // 连接线在饼状图外面
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        // 设置饼块之间的间隔
        dataSet.setSliceSpace(1f);
        dataSet.setHighlightEnabled(true);
        // 不显示图例
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        // 和四周相隔一段距离,显示数据
        pieChart.setExtraOffsets(26, 5, 26, 5);

        // 设置pieChart图表是否可以手动旋转
        pieChart.setRotationEnabled(false);
        // 设置piecahrt图表点击Item高亮是否可用
        pieChart.setHighlightPerTapEnabled(true);
        // 设置pieChart图表展示动画效果，动画运行1.4秒结束
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        //设置pieChart是否只显示饼图上百分比不显示文字
        pieChart.setDrawEntryLabels(true);
        //是否绘制PieChart内部中心文本
        pieChart.setDrawCenterText(false);
        // 绘制内容value，设置字体颜色大小
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setData(pieData);
        // 更新 piechart 视图
        pieChart.postInvalidate();
    }

}

