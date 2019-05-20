package com.example.schoolguidance.stu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.schoolguidance.R;
import com.example.schoolguidance.data.RegistrationItem;

import java.util.ArrayList;
import java.util.List;

public class StuMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List<RegistrationItem> registrationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        initData();
        StuTaskAdapter adapter = new StuTaskAdapter(StuMainActivity.this, R.layout.stu_task_item, registrationItems);
        ListView listView = (ListView) findViewById(R.id.task_listview);
        listView.setAdapter(adapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.openDrawer(Gravity.LEFT);//默认打开
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

//    void initData() {
//        RegistrationItem registrationItem = new RegistrationItem();
//        registrationItem.setRegistItemContent("领教材");
//        registrationItem.setRegistItemTime("9:00");
//        registrationItem.setRegistItemPlace("西看台");
//        registrationItem.setRegistItemps("带上校园卡");
//        for (int i = 0; i < 10; i++)
//            registrationItems.add(registrationItem);
//
//    }

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
            intent.setClass(StuMainActivity.this, MessagesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.punch_cards) {
            // Handle the camera action
            Intent intent = new Intent();
            intent.setClass(StuMainActivity.this, PunchCardsActivity.class);
            startActivity(intent);

        } else if (id == R.id.map) {
            Intent intent = new Intent();
            intent.setClass(StuMainActivity.this, MapActivity.class);
            startActivity(intent);

        } else if (id == R.id.feedback) {
            Intent intent = new Intent();
            intent.setClass(StuMainActivity.this, FeedbackActivity.class);
            startActivity(intent);

        } else if (id == R.id.ask_help) {
            Intent intent = new Intent();
            intent.setClass(StuMainActivity.this, AskHelpActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
