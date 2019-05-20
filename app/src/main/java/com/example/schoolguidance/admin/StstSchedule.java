package com.example.schoolguidance.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolguidance.R;
import com.example.schoolguidance.tool.HttpTool;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class StstSchedule extends AppCompatActivity {

    private static final int MESS_COUNTFINISHSTU = 104;
    private static final int MESS_GETALLSTU = 105;

    //private YAxis yAxis;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] title = {"条形图", "饼图"};

    private int mStepNum = 0;
    private String[] mCountList;
    private int stuNum = 0;
    private short flag = 0;
    private String[] keys;

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg);
            switch (msg.what) {
                case MESS_COUNTFINISHSTU:
                    String obj = (String) msg.obj;
                    try {
                        JSONArray jsonObj = new JSONArray(obj);
                        for (int i = 0; i < jsonObj.length(); i++) {
                            JSONObject item = jsonObj.getJSONObject(i);
                            mCountList = new String[item.length()];
                            keys = new String[item.length()];
                            Iterator<String> iter = item.keys();
                            while (iter.hasNext()) {
                                String key = iter.next();
                                String value = item.getString(key);
                                keys[mStepNum] = key;
                                mCountList[mStepNum] = value;
                                mStepNum++;
                            }
                        }
                        flag++;
                    } catch (Exception e) {

                    }
                    break;
                case MESS_GETALLSTU:
                    String obj1 = (String)msg.obj;
                    stuNum = Integer.valueOf(obj1);
                    flag++;
                    break;
                default:
                    break;
            }
            if(flag==2){
                flag = 0;
                initPager();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_stst_sche);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.fragment_pager);
        initNet();

    }

    private void initNet() {
        HttpTool countFinishStu = new HttpTool(HttpTool.MODE_POST, "/admin/countfinishstu", MESS_COUNTFINISHSTU, handler);
        countFinishStu.addData("tags", "迎新");
        countFinishStu.start();

        HttpTool getAllStu = new HttpTool(HttpTool.MODE_POST, "/admin/getallstu", MESS_GETALLSTU, handler);
        getAllStu.start();
    }

    private void initPager() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = new Fragment();
                if (fragment != null) {
                    switch (position) {
                        case 0:
                            fragment = new StstScheduleFragmentOne();
                            Bundle bundle = new Bundle();
                            for (int i = 0; i < mStepNum; i++) {
                                bundle.putString(String.valueOf(i), mCountList[i]);//这里的values就是我们要传的值
                                bundle.putString("key"+String.valueOf(i),keys[i]);
                            }
                            bundle.putString("stepNum",String.valueOf(mStepNum));
                            fragment.setArguments(bundle);
                            break;
                        case 1:
                            fragment = new StstScheduleFragmentTwo();
                            Bundle bundle1 = new Bundle();
                            for (int i = 0; i < mStepNum; i++) {
                                bundle1.putString(String.valueOf(i), mCountList[i]);//这里的values就是我们要传的值
                                bundle1.putString("key"+String.valueOf(i),keys[i]);
                            }
                            bundle1.putString("stepNum",String.valueOf(mStepNum));
                            bundle1.putString("stuNum",String.valueOf(stuNum));
                            fragment.setArguments(bundle1);
                            break;

                    }
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
        tabLayout.getTabAt(0).setCustomView(getTabView(0));
        tabLayout.getTabAt(1).setCustomView(getTabView(1));
//        tabLayout.getTabAt(2).setCustomView(getTabView(2));

        initTab();
    }

    private void initTab() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                TextView textView = view.findViewById(R.id.txt_tab_task);
//                ImageView imageView = view.findViewById(R.id.img_tab_task);
                textView.setTextColor(Color.parseColor("#ed8200"));
                if (textView.getText().toString().equals(title[0])) {
//                    imageView.setImageResource(selectImg[0]);
                    viewPager.setCurrentItem(0);
                } else if (textView.getText().toString().equals(title[1])) {
//                    imageView.setImageResource(selectImg[1]);
                    viewPager.setCurrentItem(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                TextView textView = view.findViewById(R.id.txt_tab_task);
//                ImageView imageView = view.findViewById(R.id.img_tab_task);
                textView.setTextColor(Color.parseColor("#999999"));
                if (textView.getText().toString().equals(title[0])) {
//                    imageView.setImageResource(unSelectImg[0]);
                    viewPager.setCurrentItem(0);
                } else if (textView.getText().toString().equals(title[1])) {
//                    imageView.setImageResource(unSelectImg[1]);
                    viewPager.setCurrentItem(1);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.admin_stst_sche_tab_item, null);
        TextView textView = view.findViewById(R.id.txt_tab_task);
//        ImageView imageView = view.findViewById(R.id.img_tab_task);
        textView.setText(title[position]);
//        imageView.setImageResource(unSelectImg[position]);

        if (position == 0) {
            textView.setTextColor(Color.parseColor("#ed8200"));
//            imageView.setImageResource(selectImg[position]);
        }
        return view;
    }

}
