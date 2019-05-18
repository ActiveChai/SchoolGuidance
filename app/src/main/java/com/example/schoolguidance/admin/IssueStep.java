package com.example.schoolguidance.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.schoolguidance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.schoolguidance.volun.VolunMainActivity.adapter;

public class IssueStep extends AppCompatActivity implements View.OnClickListener{
    private EditText step1num;
    private Button stepNumSubmit;
    private Button stepSubmit;
    private ListView steplist;
    private int step_num;
    static public List<Map<String, Object>> totalList = new ArrayList<Map<String, Object>>();
    final ArrayList<String> Names = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.admin_issue);
        step1num=findViewById(R.id.issue_num);
        steplist=findViewById(R.id.list_issue);
        stepSubmit=(Button)findViewById(R.id.btn_submit_step);
        stepNumSubmit=(Button)findViewById(R.id.btn_submit_stepnum);
        stepNumSubmit.setOnClickListener(this);
        stepSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.btn_submit_stepnum:
                submitNumAction();
                break;
            case R.id.btn_submit_step:
                submitStepAction();
                break;
        }
    }

    public void submitNumAction()
    {
        totalList.clear();
        Names.clear();
        step_num= Integer.parseInt(step1num.getText().toString());
        for (int i=0;i<step_num;i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("stepname", "");
            map.put("steptime", "");
            map.put("stepissue", "");
            map.put("stepps", "");
            totalList.add(map);
        }
        adapter = new SimpleAdapter(this, totalList, R.layout.admin_item_issue_step,
                new String[] {"stepname","steptime","stepissue","stepps"},
                new int[] { R.id.step_name, R.id.step_time ,R.id.step_place,R.id.step_ps});
        steplist.setAdapter(adapter);
        System.out.println(totalList.size()+"sizelslsls");

        stepSubmit.setVisibility(View.VISIBLE);
    }

    public void submitStepAction()
    {
        for (int i=0;i<step_num;i++)
        {
            steplist.getItemAtPosition(i);//setSelection(i);//;getSelectedItem();
            EditText name = (EditText)findViewById(R.id.step_name);
            Names.add(name.getText().toString()+"s");
            System.out.println(Names.size()+"sizelslsls"+Names);
            final EditText time = (EditText)findViewById(R.id.step_time );
//                Names.add((String) name.getText().toString());
            final EditText space = (EditText)findViewById(R.id.step_place);
//                Names.add((String) name.getText().toString());
            final EditText ps = (EditText)findViewById(R.id.step_ps);
//                Names.add((String) name.getText().toString());
        }

    }

}
