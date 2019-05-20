package com.example.schoolguidance.stu;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolguidance.R;
import com.example.schoolguidance.admin.IssueStep;
import com.example.schoolguidance.data.RegistrationItem;
import com.example.schoolguidance.tool.HttpTool;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PunchCardsActivity extends AppCompatActivity {

    private static final int MESS_GETREGISTRATIONITEMS = 204;
    private static final int MESS_COMPLETEMISSION = 205;
    StuTaskAdapter adapter;
    private HttpTool getRegistrationItems;
    private HttpTool completeMission;

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg.obj);

            switch (msg.what) {
                case MESS_GETREGISTRATIONITEMS:
                    try {
                        JSONArray jsonArray = new JSONArray((String) msg.obj);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (jsonObject != null) {
                                RegistrationItem registrationItem = new RegistrationItem();

                                JSONObject jsonObj = new JSONObject(jsonObject.optString("registration"));//解析两次

                                registrationItem.setRegistItemContent(jsonObj.optString("registrationcontent"));
                                registrationItem.setRegistItemTime(jsonObj.optString("registrationtime"));
                                registrationItem.setRegistItemPlace(jsonObj.optString("registrationplace"));
                                registrationItem.setRegistItemps(jsonObj.optString("registrationps"));
                                registrationItem.setRegistItemNo(jsonObject.optInt("registrationno"));
                                registrationItems.add(registrationItem);

                            }
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case MESS_COMPLETEMISSION:
                    Toast.makeText(PunchCardsActivity.this, "打卡完成", Toast.LENGTH_LONG).show();
                default:
                    break;
            }
        }
    };

    private List<RegistrationItem> registrationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_punch_cards);

        getRegistrationItems = new HttpTool(HttpTool.MODE_POST, "/freshman/getRegistrationItems", MESS_GETREGISTRATIONITEMS, handler);

        getRegistrationItems.addData("stuNo", String.valueOf("1"));

        getRegistrationItems.addData("tags","迎新");

        getRegistrationItems.start();

        adapter = new StuTaskAdapter(PunchCardsActivity.this, R.layout.stu_task_item, registrationItems);
        ListView listView = (ListView) findViewById(R.id.task_listview);
        listView.setAdapter(adapter);

    }

    class StuTaskAdapter extends ArrayAdapter {

        private final int resourceId;

        public StuTaskAdapter(Context context, int textViewResourceId, List<RegistrationItem> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final RegistrationItem registrationItem = (RegistrationItem) getItem(position); // 获取当前项的Fruit实例
            View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
            TextView regist_content = (TextView) view.findViewById(R.id.task_name);//获取该布局内的图片视图
            TextView regist_time = (TextView) view.findViewById(R.id.task_time);//获取该布局内的文本视图
            TextView regist_place = (TextView) view.findViewById(R.id.task_place);
            TextView regist_ps = (TextView) view.findViewById(R.id.task_ps);
            regist_content.setText(registrationItem.getRegistItemContent());//为图片视图设置图片资源
            regist_time.setText(registrationItem.getRegistItemTime());//为文本视图设置文本内容
            regist_place.setText(registrationItem.getRegistItemPlace());
            regist_ps.setText(registrationItem.getRegistItemps());
            Button task_complete = (Button) view.findViewById(R.id.task_complete);
            task_complete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RegistrationItem registrationItem1 = registrationItems.get(position);

                    completeMission = new HttpTool(HttpTool.MODE_POST, "/freshman/completeMission", MESS_COMPLETEMISSION, handler);
                    completeMission.addData("registrationno", String.valueOf(registrationItem1.getRegistItemNo()));
                    completeMission.addData("stuNo", "1");
                    completeMission.start();
                }
            });
            return view;
        }
    }
}