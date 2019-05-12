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

public class AskHelpActivity extends AppCompatActivity {

    EditText content_ask_help;
    QMUIRoundButton btn_submit_help;
    TextView const_text;

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
        setContentView(R.layout.stu_ask_help);
        init();
    }

    private void init() {
        content_ask_help = (EditText) findViewById(R.id.content_ask_help);
        btn_submit_help = (QMUIRoundButton) findViewById(R.id.btn_submit_help);
        const_text = (TextView) findViewById(R.id.const_text);
    }


    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_submit_help:
                String text = content_ask_help.getText().toString();
                if (text.equals("")) {
                    final QMUITipDialog tipDialog = new QMUITipDialog.Builder(this)
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                            .setTipWord("输入为空")
                            .create();
                    tipDialog.show();
                    const_text.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tipDialog.dismiss();
                        }
                    }, 1000);

                } else {
                    httpTool = new HttpTool(HttpTool.MODE_POST, "http://129.211.28.150:8443/api/addHelpContent", MESS_WHAT_TEST_POST, handler);

                    httpTool.clearData();
                    httpTool.addData("content_ask_help", content_ask_help.getText().toString());

//                    System.out.println(content_ask_help.getText());
                    httpTool.start();
                }
                break;

        }
    }
}