package com.example.schoolguidance.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.schoolguidance.R;

public class IssueStep extends AppCompatActivity implements View.OnClickListener {
    private EditText step1name;
    private EditText step1time;
    private EditText step1place;
    private EditText step1ps;
    private EditText step2name;
    private EditText step2time;
    private EditText step2place;
    private EditText step2ps;
    private EditText step3name;
    private EditText step3time;
    private EditText step3place;
    private EditText step3ps;
    private EditText step4name;
    private EditText step4time;
    private EditText step4place;
    private EditText step4ps;
    private Button stepSubmit;
    static public String step1_name;
    static public String step1_time;
    static public String step1_place;
    static public String step1_ps;
    static public String step2_name;
    static public String step2_time;
    static public String step2_place;
    static public String step2_ps;
    static public String step3_name;
    static public String step3_time;
    static public String step3_place;
    static public String step3_ps;
    static public String step4_name;
    static public String step4_time;
    static public String step4_place;
    static public String step4_ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.admin_issue_step);
        step1name=findViewById(R.id.step1_name);
        step1time=findViewById(R.id.step1_time);
        step1place=findViewById(R.id.step1_place);
        step1ps=findViewById(R.id.step1_ps);
        step2name=findViewById(R.id.step2_name);
        step2time=findViewById(R.id.step2_time);
        step2place=findViewById(R.id.step2_place);
        step2ps=findViewById(R.id.step2_ps);
        step3name=findViewById(R.id.step3_name);
        step3time=findViewById(R.id.step3_time);
        step3place=findViewById(R.id.step3_place);
        step3ps=findViewById(R.id.step3_ps);
        step4name=findViewById(R.id.step4_name);
        step4time=findViewById(R.id.step4_time);
        step4place=findViewById(R.id.step4_place);
        step4ps=findViewById(R.id.step4_ps);

        stepSubmit=(Button)findViewById(R.id.btn_submit_step);
        stepSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        step1_name=step1name.getText().toString();
        step1_time=step1time.getText().toString();
        step1_place=step1place.getText().toString();
        step1_ps=step1ps.getText().toString();
        step2_name=step2name.getText().toString();
        step2_time=step2time.getText().toString();
        step2_place=step2place.getText().toString();
        step2_ps=step2ps.getText().toString();
        step3_name=step3name.getText().toString();
        step3_time=step3time.getText().toString();
        step3_place=step3place.getText().toString();
        step3_ps=step3ps.getText().toString();
        step4_name=step4name.getText().toString();
        step4_time=step4time.getText().toString();
        step4_place=step4place.getText().toString();
        step4_ps=step4ps.getText().toString();
    }
}
