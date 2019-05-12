package com.example.schoolguidance.stu;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.schoolguidance.R;
import com.example.schoolguidance.tool.HttpTool;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

public class FeedbackActivity extends AppCompatActivity {


    EditText content_feed;
    QMUIRoundButton btn_submit_feed;
    TextView const_text_feed;

    private static final int MESS_WHAT_TEST_POST = 301;

    private HttpTool httpTool;

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg);

            switch (msg.what) {
                case MESS_WHAT_TEST_POST:
                    System.out.println("post ok");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_feedback);
        init();
    }

    public void init() {
        const_text_feed = (TextView) findViewById(R.id.const_text_feed);
        btn_submit_feed = (QMUIRoundButton) findViewById(R.id.btn_submit_feed);
        content_feed = (EditText) findViewById(R.id.content_feed);
    }


    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_submit_feed:
                String text = content_feed.getText().toString();
                if (text.equals("")) {
                    final QMUITipDialog tipDialog = new QMUITipDialog.Builder(this)
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                            .setTipWord("输入为空")
                            .create();
                    tipDialog.show();
                    const_text_feed.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tipDialog.dismiss();
                        }
                    }, 1000);
                } else {
                    httpTool = new HttpTool(HttpTool.MODE_POST, "http://129.211.28.150:8443/api/addfeed", MESS_WHAT_TEST_POST, handler);

                    httpTool.clearData();
                    httpTool.addData("content_feed", content_feed.getText().toString());

                    httpTool.start();
                }
                break;

        }
    }
}