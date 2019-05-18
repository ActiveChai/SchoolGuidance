package com.example.schoolguidance.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.schoolguidance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.schoolguidance.volun.VolunMainActivity.adapter;

public class IssueStep extends AppCompatActivity{
    private ListViewAdapter mAdapter;
    private List<ItemBean> mDataName;
    private List<ItemBean> mDataTime;
    private List<ItemBean> mDataPlace;
    private List<ItemBean> mDataPs;
    private ListView steplist;
    private Button mBuadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.admin_issue);
        steplist=findViewById(R.id.list_issue);
        mBuadd=(Button)findViewById(R.id.btn_add_step);
        mDataName = new ArrayList<ItemBean>();
        mDataTime = new ArrayList<ItemBean>();
        mDataPlace = new ArrayList<ItemBean>();
        mDataPs = new ArrayList<ItemBean>();
        mAdapter = new ListViewAdapter(this, mDataName,mDataTime,mDataPlace,mDataPs);
        steplist.setAdapter(mAdapter);

        mBuadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataName.add(new ItemBean());
                mDataTime.add(new ItemBean());
                mDataPlace.add(new ItemBean());
                mDataPs.add(new ItemBean());
                for (int i=0;i<mDataName.size();i++)
                {
                    System.out.println(mDataName.get(i).getText()+"name"+i);
                    System.out.println(mDataTime.get(i).getText()+"time"+i);
                    System.out.println(mDataPlace.get(i).getText()+"place"+i);
                    System.out.println(mDataPs.get(i).getText()+"ps"+i);
                }

                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
