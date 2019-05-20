package com.example.schoolguidance.stu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.schoolguidance.R;
import com.example.schoolguidance.data.RegistrationItem;

import java.util.List;

public class StuTaskAdapter extends ArrayAdapter {

    private final int resourceId;

    public StuTaskAdapter(Context context, int textViewResourceId, List<RegistrationItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RegistrationItem registrationItem = (RegistrationItem) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        TextView regist_content = (TextView) view.findViewById(R.id.task_name);//获取该布局内的图片视图
        TextView regist_time = (TextView) view.findViewById(R.id.task_time);//获取该布局内的文本视图
        TextView regist_place = (TextView) view.findViewById(R.id.task_place);
        TextView regist_ps = (TextView) view.findViewById(R.id.task_ps);
        regist_content.setText(registrationItem.getRegistItemContent());//为图片视图设置图片资源
        regist_time.setText(registrationItem.getRegistItemTime());//为文本视图设置文本内容
        regist_place.setText(registrationItem.getRegistItemPlace());
        regist_ps.setText(registrationItem.getRegistItemps());
        return view;
    }
}