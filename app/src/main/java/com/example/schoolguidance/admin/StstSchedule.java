package com.example.schoolguidance.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schoolguidance.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;


public class StstSchedule extends AppCompatActivity {


    //private YAxis yAxis;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] title = {"条形图", "饼图"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_stst_sche);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.fragment_pager);
        initPager();



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
                            break;
                        case 1:
                            fragment = new StstScheduleFragmentTwo();
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
