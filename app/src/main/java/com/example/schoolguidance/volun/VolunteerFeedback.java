package com.example.schoolguidance.volun;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolguidance.R;
import com.example.schoolguidance.tool.HttpTool;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

public class VolunteerFeedback extends AppCompatActivity {

    private static final int MESS_INSERTFEEDBACK = 304;

    private HttpTool insertFeedback;

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg.obj);

            switch (msg.what) {
                case MESS_INSERTFEEDBACK:
                    Toast toast = Toast.makeText(getApplicationContext(), "反馈成功", Toast.LENGTH_SHORT);
                    toast.show();//显示反馈是否成功信息
                    break;
                default:
                    break;
            }
        }
    };

    EditText content_feed;
    QMUIRoundButton btn_submit_feed;
    TextView const_text_feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volun_feedback);
        init();
    }

    public void init() {
        const_text_feed = (TextView) findViewById(R.id.const_text_feed);
        btn_submit_feed = (QMUIRoundButton) findViewById(R.id.btn_submit_feed);
        content_feed = (EditText) findViewById(R.id.content_feed);
    }


    public void onClick(View view) {
        Intent intent = new Intent();
        String text = content_feed.getText().toString();
        switch (view.getId()) {
            case R.id.btn_submit_feed:
                if (text.equals("")) {
                    final QMUITipDialog tipDialog = new QMUITipDialog.Builder(this)
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                            .setTipWord("输入为空")
                            .create();
                    tipDialog.show();
                    System.out.println(111);
                    const_text_feed.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tipDialog.dismiss();
                        }
                    }, 1000);
                } else {
                    insertFeedback = new HttpTool(HttpTool.MODE_POST, "/volunteer/insertFeedback", MESS_INSERTFEEDBACK, handler);

                    insertFeedback.addData("feedbackcontent", text);

                    insertFeedback.addData("tags","迎新");

                    insertFeedback.start();
                }
                break;

        }
    }
}
