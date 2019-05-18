package com.example.schoolguidance.stu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.schoolguidance.R;
import com.example.schoolguidance.data.RegistrationItem;

import java.util.ArrayList;
import java.util.List;

public class PunchCardsActivity extends AppCompatActivity {

    private List<RegistrationItem> registrationItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_punch_cards);
        initData();
        StuTaskAdapter adapter=new StuTaskAdapter(PunchCardsActivity.this,R.layout.stu_task_item,registrationItems);
        ListView listView=(ListView)findViewById(R.id.task_listview);
        listView.setAdapter(adapter);

    }
    void initData(){
        RegistrationItem registrationItem=new RegistrationItem();
        registrationItem.setRegistItemContent("领教材");
        registrationItem.setRegistItemTime("9:00");
        registrationItem.setRegistItemPlace("西看台");
        registrationItem.setRegistItemps("带上校园卡");
        for (int i=0;i<10;i++)
            registrationItems.add(registrationItem);

    }



}