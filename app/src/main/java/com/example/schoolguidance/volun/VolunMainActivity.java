package com.example.schoolguidance.volun;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.schoolguidance.R;
import com.example.schoolguidance.data.VolunteerService;
import com.example.schoolguidance.stu.MessagesActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.leancloud.chatkit.LCChatKitUser;

public class VolunMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager viewPager;
    private TextView emptyText;
    private ListView task_lisk_main;
    static public List<Map<String, Object>> taskList = new ArrayList<Map<String, Object>>();
    static public SimpleAdapter adapter = null;
    static public List<LCChatKitUser> userList;
    private List<VolunteerService> volunteerServices=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_main);
        task_lisk_main = (ListView) findViewById(R.id.listView_volun_main);
        emptyText = (TextView) findViewById(R.id.textView_empty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        userList = CustomUserProvider.getInstance().getAllUsers();
        adapter = new SimpleAdapter(this, taskList, R.layout.volun_main_tasklist_item,
                new String[] {  },
                new int[] { });
        task_lisk_main.setAdapter(adapter);
        task_lisk_main.setEmptyView(emptyText);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
            Intent intent = new Intent();
            intent.setClass(VolunMainActivity.this, CurrentTask.class);
            startActivity(intent);

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
}
