package com.example.schoolguidance.stu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.schoolguidance.R;
import com.example.schoolguidance.data.RegistrationItem;
import com.example.schoolguidance.data.VolunteerService;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

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
        registrationItem.setRegistItemStartTime("9:00");
        registrationItem.setRegistItemPlace("西看台");
        registrationItem.setRegistItemMaterials("带上校园卡");
        for (int i=0;i<10;i++)
            registrationItems.add(registrationItem);

    }



}
