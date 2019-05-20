package com.example.schoolguidance.stu;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.schoolguidance.R;
import com.example.schoolguidance.tool.HttpTool;
import com.example.schoolguidance.volun.CurrentTask;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class AskHelpActivity extends AppCompatActivity {

    private static final int MESS_SELECTVOLUNTEERSERVICE = 201;
    private static final int MESS_INSERTVOLUNTEERSERVICE = 202;

    private HttpTool selectVolunteerService;
    private HttpTool insertVolunteerService;
    String serviceStatus;
    String serviceContent;

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg.obj);

            switch (msg.what) {
                case MESS_SELECTVOLUNTEERSERVICE:
                    try {
                        JSONObject jsonObj = new JSONObject((String) msg.obj);
                        serviceStatus = jsonObj.optString("serviceStatus");
                        serviceContent = jsonObj.optString("serviceContent");
                        Sno=jsonObj.optInt("freshNo");
                        Vno=jsonObj.optInt("volunNo");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (serviceStatus.equals("未接受")) {
                        askIsWait = true;
                        askIsTaken = false;
                        content = serviceContent;
                    } else if (serviceStatus.equals("进行中")) {
                        askIsWait = false;
                        askIsTaken = true;
                    }
                    break;
                case MESS_INSERTVOLUNTEERSERVICE:
                    Toast toast = Toast.makeText(getApplicationContext(), "请求帮助成功", Toast.LENGTH_SHORT);
                    toast.show();//显示请求帮助是否成功信息
                    break;
                default:
                    break;
            }

            if (askIsWait) {
                const_text.setText("您有请求正在等待志愿者接受，请耐心等待！您也可以选择取消，重新发布新的请求！");
                content_ask_help.setHint(content);
                content_ask_help.setEnabled(false);
                btn_submit_help.setEnabled(false);
                btn_cancel_help.setEnabled(true);
            } else if (askIsTaken) {
                startTalk();
            }

        }
    };

    EditText content_ask_help;
    QMUIRoundButton btn_submit_help;
    QMUIRoundButton btn_cancel_help;
    TextView const_text;
    Boolean askIsWait;//后台获取，该请求状态为真代表：等待志愿者接
    Boolean askIsTaken;//后台获取，该请求状态为真代表：有志愿者接了
    String content;//后台获取，刚才发布的请求的内容
    int Vno;
    int Sno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_ask_help);
        init();

        selectVolunteerService = new HttpTool(HttpTool.MODE_POST, "/freshman/selectVolunteerService", MESS_SELECTVOLUNTEERSERVICE, handler);

        selectVolunteerService.addData("freshNo", String.valueOf("2"));
        selectVolunteerService.addData("tags","迎新");

        selectVolunteerService.start();
    }

    public void startTalk() {
        LCChatKit.getInstance().open("新生"+Sno, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    finish();
                    Intent intent = new Intent(AskHelpActivity.this, LCIMConversationActivity.class);
                    intent.putExtra(LCIMConstants.PEER_ID, "志愿者"+Vno);
                    intent.putExtra("activityNo",0);
                    startActivity(intent);
                } else {
                    Toast.makeText(AskHelpActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        const_text = (TextView) findViewById(R.id.const_text);
        content_ask_help = (EditText) findViewById(R.id.content_ask_help);
        btn_submit_help = (QMUIRoundButton) findViewById(R.id.btn_submit_help);
        btn_cancel_help = (QMUIRoundButton) findViewById(R.id.btn_cancel_help);
        btn_cancel_help.setEnabled(false);
    }


    public void onClick(View view) {
        Intent intent = new Intent();
        String text = content_ask_help.getText().toString();
        switch (view.getId()) {
            case R.id.btn_submit_help:
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
                    insertVolunteerService = new HttpTool(HttpTool.MODE_POST, "/freshman/insertVolunteerService", MESS_INSERTVOLUNTEERSERVICE, handler);

                    insertVolunteerService.addData("freshNo", String.valueOf("1"));
                    insertVolunteerService.addData("serviceContent", text);
                    insertVolunteerService.addData("serviceStatus", serviceStatus);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");//获取当前时间
                    Date date = new Date(System.currentTimeMillis());
                    insertVolunteerService.addData("startTime", simpleDateFormat.format(date));
                    insertVolunteerService.addData("tags", "迎新");

                    insertVolunteerService.start();

                    const_text.setText("您有请求正在等待志愿者接受，请耐心等待！您也可以选择取消，重新发布新的请求！");
                    content_ask_help.setHint(text);
                    content_ask_help.setEnabled(false);
                    btn_submit_help.setEnabled(false);
                    btn_cancel_help.setEnabled(true);
                }
                break;
            case R.id.btn_cancel_help:
                askIsWait = false;
                const_text.setText("请输入你的困难与想得到的帮助");
                content_ask_help.setHint("");
                content_ask_help.setEnabled(true);
                btn_submit_help.setEnabled(true);
                btn_cancel_help.setEnabled(false);
                break;
        }
    }
}