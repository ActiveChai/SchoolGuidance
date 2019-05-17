package com.example.schoolguidance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.schoolguidance.admin.AdminMainActivity;
import com.example.schoolguidance.stu.StuMainActivity;
import com.example.schoolguidance.volun.VolunMainActivity;

public class Login extends AppCompatActivity {
    Button inStu;
    Button inAdmin;
    Button inVolun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        inStu=(Button)findViewById(R.id.in_stu);
        inAdmin=(Button)findViewById(R.id.in_admin);
        inVolun=(Button)findViewById(R.id.in_volun);

        inStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, StuMainActivity.class));
            }
        });

        inAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, AdminMainActivity.class));
            }
        });

        inVolun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, VolunMainActivity.class));
            }
        });
    }
}
