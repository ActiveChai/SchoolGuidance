package com.example.schoolguidance.volun;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.schoolguidance.R;
import com.example.schoolguidance.data.RegistrationItem;
import com.example.schoolguidance.data.VolunteerService;
import com.example.schoolguidance.tool.HttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryTask extends AppCompatActivity {

    private static final int MESS_SELECTVOLUNTEERSERVICEBYVNO = 302;
    private MyAdapter adapter;
    private HttpTool selectVolunteerServiceByVno;

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg.obj);

            switch (msg.what) {
                case MESS_SELECTVOLUNTEERSERVICEBYVNO:
                    try {
                        JSONArray jsonArray = new JSONArray((String) msg.obj);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (jsonObject != null && jsonObject.optString("serviceStatus").equals("已结束")) {
                                VolunteerService volunteerService = new VolunteerService();
                                volunteerService.setActivityNo(jsonObject.optInt("activityNo"));
                                volunteerService.setServiceType(jsonObject.optString("serviceType"));
                                volunteerService.setStartTime(jsonObject.optString("startTime"));
                                volunteerService.setEndTime(jsonObject.optString("endTime"));
                                volunteerServices.add(volunteerService);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private List<VolunteerService> volunteerServices = new ArrayList<>();

//    VolunteerService volunteerService = new VolunteerService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volun_history_task);

        selectVolunteerServiceByVno = new HttpTool(HttpTool.MODE_POST, "/volunteer/selectVolunteerServiceByVno", MESS_SELECTVOLUNTEERSERVICEBYVNO, handler);

        selectVolunteerServiceByVno.addData("Vno", String.valueOf("1"));

        selectVolunteerServiceByVno.addData("tags", "迎新");

        selectVolunteerServiceByVno.start();

        adapter = new MyAdapter(HistoryTask.this, R.layout.volun_service_history_item, volunteerServices);
        ListView listView = (ListView) findViewById(R.id.history_listview);
        listView.setAdapter(adapter);
    }
}

class MyAdapter extends ArrayAdapter {
    private final int resourceId;

    public MyAdapter(Context context, int textViewResourceId, List<VolunteerService> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VolunteerService volunteerService = (VolunteerService) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        TextView taskno = (TextView) view.findViewById(R.id.task_no);//获取该布局内的图片视图
        TextView taskname = (TextView) view.findViewById(R.id.task_name);//获取该布局内的文本视图
        TextView taskBeginTime = (TextView) view.findViewById(R.id.task_begin_time);
        TextView taskEndTime = (TextView) view.findViewById(R.id.task_end_time);
        taskno.setText(String.valueOf(volunteerService.getActivityNo()));//为图片视图设置图片资源
        taskname.setText(volunteerService.getServiceType());//为文本视图设置文本内容
        taskBeginTime.setText(volunteerService.getStartTime());
        taskEndTime.setText(volunteerService.getEndTime());
        return view;
    }
}
