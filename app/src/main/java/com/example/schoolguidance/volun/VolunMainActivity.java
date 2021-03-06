package com.example.schoolguidance.volun;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolguidance.R;
import com.example.schoolguidance.admin.IssueStep;
import com.example.schoolguidance.data.VolunteerService;
import com.example.schoolguidance.stu.MessagesActivity;
import com.example.schoolguidance.tool.HttpTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.leancloud.chatkit.LCChatKitUser;

public class VolunMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final static int MESS_SELECTALLVOLUNTEERSERVICE = 301;
    private final static int MESS_SELECTVOLUNTEERSERVICEBYVNO = 303;
    private final static int MESS_ACCEPTSERVICE = 305;


    private ViewPager viewPager;
    private int freshNo;
    private int activityNo;
    private MAdapter adapter;
    private TextView emptyText;
    private ListView task_list_main;
    static public List<Map<String, Object>> taskList = new ArrayList<Map<String, Object>>();
    private List<VolunteerService> volunteerServices = new ArrayList<>();

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg);

            switch (msg.what) {
                case MESS_SELECTALLVOLUNTEERSERVICE:
                    String obj = (String) msg.obj;
                    try {
                        JSONArray jsonObj = new JSONArray(obj);
                        for (int i = 0; i < jsonObj.length(); i++) {
                            JSONObject item = jsonObj.getJSONObject(i);
                            VolunteerService volunteerService = new VolunteerService();
                            volunteerService.setServiceContent(item.getString("serviceContent"));
                            volunteerService.setStartTime(item.getString("startTime"));
                            volunteerService.setActivityNo(item.getInt("activityNo"));
                            volunteerService.setEndTime(item.getString("endTime"));
                            volunteerService.setFreshNo(item.getInt("freshNo"));
                            volunteerService.setServiceStatus(item.getString("serviceStatus"));
                            volunteerService.setServiceType(item.getString("serviceType"));
                            volunteerService.setVolunNo(item.getInt("volunNo"));

                            volunteerServices.add(volunteerService);
                        }
                    } catch (Exception e) {

                    }
                    adapter.notifyDataSetChanged();
                    break;
                case MESS_SELECTVOLUNTEERSERVICEBYVNO:
                    String obj1 = (String) msg.obj;
                    try {
                        JSONArray jsonObj1 = new JSONArray(obj1);
                        boolean flag = false;
                        if (jsonObj1.length() > 0) {
                            for (int i = 0; i < jsonObj1.length(); i++) {
                                JSONObject item = jsonObj1.getJSONObject(i);
                                if (item.getString("serviceStatus").equals("已结束")) {
                                    continue;
                                }
                                flag = true;
                                Intent intent = new Intent();
                                intent.putExtra("activityNo", item.getInt("activityNo"));
                                intent.putExtra("freshNo", item.getInt("freshNo"));
                                intent.putExtra("volunNo", item.getInt("volunNo"));
                                intent.setClass(VolunMainActivity.this, CurrentTask.class);
                                startActivity(intent);
                                break;
                            }
                            if(!flag){
                                Toast.makeText(VolunMainActivity.this, "无当前任务，快去接新的任务吧!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(VolunMainActivity.this, "无当前任务，快去接新的任务吧!", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {

                    }
                    break;
                case MESS_ACCEPTSERVICE:
                    String obj2 = (String) msg.obj;
                    Intent intent = new Intent(VolunMainActivity.this, CurrentTask.class);
                    intent.putExtra("activityNo", activityNo);
                    intent.putExtra("freshNo", freshNo);
                    intent.putExtra("volunNo", 1);
                    startActivity(intent);
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_main);
        task_list_main = (ListView) findViewById(R.id.service_listview);
        emptyText = (TextView) findViewById(R.id.textView_empty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        initData();
        adapter = new MAdapter(VolunMainActivity.this, R.layout.volun_main_tasklist_item, volunteerServices);
        task_list_main.setAdapter(adapter);
        task_list_main.setEmptyView(emptyText);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    void initData() {

        HttpTool selectAllVolunteerService = new HttpTool(HttpTool.MODE_POST, "/volunteer/selectAllVolunteerService", MESS_SELECTALLVOLUNTEERSERVICE, handler);
        selectAllVolunteerService.addData("tags","迎新");
        selectAllVolunteerService.start();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.messages) {
            Intent intent = new Intent();
            intent.setClass(VolunMainActivity.this, MessagesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.volunteer_account) {
            // Handle the camera action
            Intent intent = new Intent();
            intent.setClass(VolunMainActivity.this, VolunteerAccount.class);
            startActivity(intent);

        } else if (id == R.id.curr_task) {

            HttpTool selectVolunteerServiceByVno = new HttpTool(HttpTool.MODE_POST, "/volunteer/selectVolunteerServiceByVno", MESS_SELECTVOLUNTEERSERVICEBYVNO, handler);
            selectVolunteerServiceByVno.addData("Vno", "1");
            selectVolunteerServiceByVno.start();


        } else if (id == R.id.hist_task) {
            Intent intent = new Intent();
            intent.setClass(VolunMainActivity.this, HistoryTask.class);
            startActivity(intent);

        } else if (id == R.id.volunteer_feedback) {
            Intent intent = new Intent();
            intent.setClass(VolunMainActivity.this, VolunteerFeedback.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class MAdapter extends ArrayAdapter {
        private final int resourceId;

        public MAdapter(Context context, int textViewResourceId, List<VolunteerService> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final VolunteerService volunteerService = (VolunteerService) getItem(position); // 获取当前项的Fruit实例
            View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
            TextView service_content = (TextView) view.findViewById(R.id.service_name);//获取该布局内的图片视图
            TextView service_time = (TextView) view.findViewById(R.id.service_time);//获取该布局内的文本视图
            Button service_recieve = (Button) view.findViewById(R.id.btn_receive);//接受任务
            service_recieve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //这里还需要修改任务状态和志愿者状态
                    VolunteerService volunteerService1 = volunteerServices.get(position);

                    HttpTool acceptservice = new HttpTool(HttpTool.MODE_POST, "/volunteer/acceptService", MESS_ACCEPTSERVICE, handler);
                    freshNo = volunteerService1.getFreshNo();
                    activityNo = volunteerService1.getActivityNo();
                    acceptservice.addData("activityNo", String.valueOf(volunteerService1.getActivityNo()));
                    acceptservice.addData("volunNo", "1");
                    acceptservice.start();
                }
            });
            service_content.setText(volunteerService.getServiceContent());//为图片视图设置图片资源
            service_time.setText(volunteerService.getStartTime());//为文本视图设置文本内容
            return view;
        }
    }
}
