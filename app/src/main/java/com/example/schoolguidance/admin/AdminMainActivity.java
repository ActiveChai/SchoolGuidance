package com.example.schoolguidance.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.schoolguidance.stu.AskHelpActivity;
import com.example.schoolguidance.stu.FeedbackActivity;
import com.example.schoolguidance.stu.MapActivity;
import com.example.schoolguidance.stu.MessagesActivity;
import com.example.schoolguidance.stu.PunchCardsActivity;
import com.example.schoolguidance.R;

public class AdminMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
            intent.setClass(AdminMainActivity.this, MessagesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.manage_stu) {
            // Handle the camera action
            Intent intent = new Intent();
            intent.setClass(AdminMainActivity.this, ManageStu.class);
            startActivity(intent);

        } else if (id == R.id.issue_step) {
            Intent intent = new Intent();
            intent.setClass(AdminMainActivity.this, IssueStep.class);
            startActivity(intent);

        } else if (id == R.id.issue_inform) {
            Intent intent = new Intent();
            intent.setClass(AdminMainActivity.this, IssueInform.class);
            startActivity(intent);

        } else if (id == R.id.manage_volunte) {
            Intent intent = new Intent();
            intent.setClass(AdminMainActivity.this, ManageVolun.class);
            startActivity(intent);

        } else if (id == R.id.ststistics_schedule) {
            Intent intent = new Intent();
            intent.setClass(AdminMainActivity.this, StstSchedule.class);
            startActivity(intent);

        }else if(id==R.id.manage_feedback){
            Intent intent=new Intent();
            intent.setClass(AdminMainActivity.this,ManageFeedback.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
