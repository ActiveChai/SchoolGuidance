package com.example.schoolguidance.stu;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.schoolguidance.R;
import com.example.schoolguidance.tool.HttpTool;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

public class PunchCardsActivity extends AppCompatActivity {

    private static final int MESS_WHAT_TEST_GET1 = 301;
    private static final int MESS_WHAT_TEST_GET2 = 302;
    private static final int MESS_WHAT_TEST_GET3 = 303;
    private static final int MESS_WHAT_TEST_GET4 = 304;


    private HttpTool httpTool1;
    private HttpTool httpTool2;
    private HttpTool httpTool3;
    private HttpTool httpTool4;

    String card1 = "";
    String card2 = "";
    String card3 = "";
    String card4 = "";

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg);

            switch (msg.what) {
                case MESS_WHAT_TEST_GET1:
                    card1 = msg.obj.toString();
                    break;
                case MESS_WHAT_TEST_GET2:
                    card1 = msg.obj.toString();
                    break;
                case MESS_WHAT_TEST_GET3:
                    card1 = msg.obj.toString();
                    break;
                case MESS_WHAT_TEST_GET4:
                    card1 = msg.obj.toString();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_punch_cards);
        init();

        httpTool1 = new HttpTool(HttpTool.MODE_GET, "http://129.211.28.150:8443/api/getProcess1", MESS_WHAT_TEST_GET1, handler);
        httpTool1.clearData();
        httpTool1.start();

        httpTool2 = new HttpTool(HttpTool.MODE_GET, "http://129.211.28.150:8443/api/getProcess2", MESS_WHAT_TEST_GET2, handler);
        httpTool2.clearData();
        httpTool2.start();

        httpTool3 = new HttpTool(HttpTool.MODE_GET, "http://129.211.28.150:8443/api/getProcess3", MESS_WHAT_TEST_GET3, handler);
        httpTool3.clearData();
        httpTool3.start();

        httpTool4 = new HttpTool(HttpTool.MODE_GET, "http://129.211.28.150:8443/api/getProcess4", MESS_WHAT_TEST_GET4, handler);
        httpTool4.clearData();
        httpTool4.start();
    }

    public void onClick(View view) {
        String text = "";
        switch (view.getId()) {
            case R.id.pre_register:
                text = card1;
                break;
            case R.id.register:
                text = card2;
                break;
            case R.id.dormitory:
                text = card3;
                break;
            case R.id.books:
                text = card4;
                break;
        }
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("来自辅导员的通知")
                .setMessage(text)
                .addAction("了解", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("完成", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void init() {
        QMUIRoundButton preBtn = (QMUIRoundButton) findViewById(R.id.pre_register);
        QMUIRoundButton registerBtn = (QMUIRoundButton) findViewById(R.id.register);
        QMUIRoundButton dormitoryBtn = (QMUIRoundButton) findViewById(R.id.dormitory);
        QMUIRoundButton booksBtn = (QMUIRoundButton) findViewById(R.id.books);
    }
}
