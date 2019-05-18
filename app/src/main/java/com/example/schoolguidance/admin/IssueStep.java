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
import com.example.schoolguidance.data.RegistrationItem;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static com.example.schoolguidance.volun.VolunMainActivity.adapter;

public class IssueStep extends AppCompatActivity{
    private ListViewAdapter mAdapter;
    private List<ItemBean> mDataName;
    private List<ItemBean> mDataTime;
    private List<ItemBean> mDataPlace;
    private List<ItemBean> mDataPs;
    private ListView steplist;
    private Button mBuadd;
    private Button mBuSubmit;
    private TextView context;
    private ArrayList<RegistrationItem> stepList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.admin_issue);
        steplist=findViewById(R.id.list_issue);

        mBuadd=(Button)findViewById(R.id.btn_add_step);
        mBuSubmit=(Button)findViewById(R.id.btn_submit_step);
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
                mAdapter.notifyDataSetChanged();
                System.out.println(mDataName.get(0).getText());
            }
        });

        mBuSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<mDataName.size();i++)
                {
                    if(mDataName.get(i).getText()==null||mDataTime.get(i).getText()==null||mDataPlace.get(i).getText()==null||mDataPs.get(i).getText()==null)
                    {
                        final QMUITipDialog tipDialog = new QMUITipDialog.Builder(IssueStep.this)
                                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                                .setTipWord("输入不可以为空")
                                .create();
                        tipDialog.show();
                        mBuadd.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tipDialog.dismiss();
                            }
                        }, 1000);
                    }
                    else {
                        RegistrationItem registrationItem=new RegistrationItem();
                        registrationItem.setRegistItemContent(mDataName.get(i).getText());
                        registrationItem.setRegistItemTime(mDataTime.get(i).getText());
                        registrationItem.setRegistItemPlace(mDataPlace.get(i).getText());
                        registrationItem.setRegistItemps(mDataPs.get(i).getText());
                        stepList.add(registrationItem);
                    }
                }
            }
        });
    }
}
