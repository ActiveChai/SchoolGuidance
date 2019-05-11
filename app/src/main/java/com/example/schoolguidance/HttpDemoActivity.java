package com.example.school;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.schoolguidance.tool.HttpTool;

import java.lang.ref.WeakReference;

public class HttpDemoActivity extends AppCompatActivity {

    private static final int MESS_WHAT_TEST_POST = 301;
    private static final int MESS_WHAT_TEST_GET = 302;


    private HttpTool httpTool;
    private Button test_POST;
    private Button test_GET;


    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            System.out.println(msg);

            switch (msg.what) {
                case MESS_WHAT_TEST_POST:
                    test_POST.setText(msg.obj.toString());
                    break;
                case MESS_WHAT_TEST_GET:
                    test_GET.setText("SUCC");
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test_GET = (Button) findViewById(R.id.test_GET);
        test_POST = (Button) findViewById(R.id.test_POST);



        test_POST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpTool = new HttpTool(HttpTool.MODE_POST, "http://129.211.28.150:8443/api/loginInUsername", MESS_WHAT_TEST_POST, handler);

                httpTool.clearData();
                httpTool.addData("userName", "201626811302");
                httpTool.addData("password", "123456");

                httpTool.start();
            }
        });

        test_GET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpTool = new HttpTool(HttpTool.MODE_GET, "http://129.211.28.150:8443/api/getRouteList", MESS_WHAT_TEST_GET, handler);

                httpTool.clearData();
//                httpTool.addData("","");如果有数据要传的话

                httpTool.start();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        httpTool.stop();
    }

}
