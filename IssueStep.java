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
    private EditText step1;
    private EditText step2;
    private EditText step3;
    private EditText step4;
    private Button stepSubmit;
    static public String issue_step1;
    static public String issue_step2;
    static public String issue_step3;
    static public String issue_step4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.admin_issue_step);
        step1=findViewById(R.id.step1_content);
        step2=findViewById(R.id.step2_content);
        step3=findViewById(R.id.step3_content);
        step4=findViewById(R.id.step4_content);
        stepSubmit=(Button)findViewById(R.id.btn_submit_step);
        stepSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        issue_step1=step1.getText().toString();
        issue_step2=step2.getText().toString();
        issue_step3=step3.getText().toString();
        issue_step4=step4.getText().toString();
    }
}
