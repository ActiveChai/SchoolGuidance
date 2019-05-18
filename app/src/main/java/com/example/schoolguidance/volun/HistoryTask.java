package com.example.schoolguidance.volun;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.schoolguidance.R;
import com.example.schoolguidance.data.VolunteerService;

import java.util.ArrayList;
import java.util.List;

public class HistoryTask extends AppCompatActivity {
    private List<VolunteerService> volunteerServices=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volun_history_task);
        initData();
        MyAdapter adapter=new MyAdapter(HistoryTask.this,R.layout.volun_ervice_history_item,volunteerServices);
        ListView listView=(ListView)findViewById(R.id.history_listview);
        listView.setAdapter(adapter);
    }


    void initData(){
        VolunteerService volunteerService=new VolunteerService();
        volunteerService.setActivityNo(1);
        volunteerService.setServiceType("str1");
        volunteerService.setStartTime("9:00");
        volunteerService.setEndTime("12:00");
        for (int i=0;i<20;i++)
            volunteerServices.add(volunteerService);

    }
}
    class MyAdapter extends ArrayAdapter{
    private final int resourceId;

    public MyAdapter(Context context, int textViewResourceId, List<VolunteerService> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VolunteerService volunteerService= (VolunteerService) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        TextView taskno = (TextView) view.findViewById(R.id.task_no);//获取该布局内的图片视图
        TextView taskname = (TextView) view.findViewById(R.id.task_name);//获取该布局内的文本视图
        TextView taskBeginTime=(TextView)view.findViewById(R.id.task_begin_time);
        TextView taskEndTime=(TextView)view.findViewById(R.id.task_end_time);
        taskno.setText(String.valueOf(volunteerService.getActivityNo()));//为图片视图设置图片资源
        taskname.setText(volunteerService.getServiceType());//为文本视图设置文本内容
        taskBeginTime.setText(volunteerService.getStartTime());
        taskEndTime.setText(volunteerService.getEndTime());
        return view;
    }
}
