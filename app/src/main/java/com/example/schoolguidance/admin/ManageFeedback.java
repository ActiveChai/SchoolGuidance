package com.example.schoolguidance.admin;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.schoolguidance.R;
import com.example.schoolguidance.data.Feedback;
import com.example.schoolguidance.data.VolunteerService;
import com.example.schoolguidance.tool.HttpTool;
import com.example.schoolguidance.volun.HistoryTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManageFeedback extends AppCompatActivity {
    private static final int MESS_GETALLFEEDBACK = 102;

    private List<Feedback> feedbacks = new ArrayList<>();
    private MyAdapter adapter;
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg);

            switch (msg.what) {
                case MESS_GETALLFEEDBACK:
                    String obj = (String) msg.obj;
                    Log.d(obj, "0");
                    try {
                        JSONArray jsonObj = new JSONArray(obj);
                        for (int i = 0; i < jsonObj.length();i++) {
                            JSONObject item = jsonObj.getJSONObject(i);
                            if(item==null) continue;
                            Feedback feedback = new Feedback();
                            feedback.setFeedno(item.optInt("feedno"));
                            feedback.setFeedcontent(item.optString("feedcontent"));
                            feedbacks.add(feedback);
                        }
                    } catch (Exception e) {

                    }
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_manage_feedback);
        initData();
        adapter = new MyAdapter(ManageFeedback.this, R.layout.admin_feedback_item, feedbacks);
        ListView listView = (ListView) findViewById(R.id.feedback_listview);
        listView.setAdapter(adapter);
    }


    void initData() {

        HttpTool getAllFeedBack = new HttpTool(HttpTool.MODE_POST, "/admin/getallfeedback", MESS_GETALLFEEDBACK, handler);
        getAllFeedBack.addData("tags", "迎新");
        getAllFeedBack.start();
    }
}

class MyAdapter extends ArrayAdapter {
    private final int resourceId;

    public MyAdapter(Context context, int textViewResourceId, List<Feedback> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Feedback feedback = (Feedback) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        TextView feedNo = (TextView) view.findViewById(R.id.feedback_number);//获取该布局内的图片视图
        TextView feedbackContent = (TextView) view.findViewById(R.id.feekback_content);//获取该布局内的文本视图
        feedNo.setText(String.valueOf(feedback.getFeedno()));//为图片视图设置图片资源
        feedbackContent.setText(feedback.getFeedcontent());//为文本视图设置文本内容
        return view;
    }
}
