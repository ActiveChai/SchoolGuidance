package com.example.schoolguidance.stu;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.schoolguidance.R;
import com.example.schoolguidance.admin.IssueStep;
import com.example.schoolguidance.tool.HttpTool;
import com.example.schoolguidance.volun.CurrentTask;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import org.json.JSONObject;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class MessagesActivity extends AppCompatActivity {

    private final static int MESS_SELECTVOLUNTEERSERVICE = 206;
    QMUIGroupListView informationList;
    String temp;
    String str1 = "现在报道同学较多，大家可以错峰到校。";
    String str2 = "已经收到你的反馈，安排了7号志愿者前往你处帮忙。";
    String str3 = "如遇到自称学长学姐的推销人员，请拒绝购买推销产品并报告给保卫处，电话：85290110。";
    private String volunNo = "1";
    private String freshNo = "1";
    private String activityNo = "1";

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg);

            switch (msg.what) {
                case MESS_SELECTVOLUNTEERSERVICE:
                    String obj = (String) msg.obj;

                    try {
                        JSONObject jsonObject = new JSONObject(obj);
                        if(jsonObject.getString("serviceStatus").equals("未接受")){
                            Toast.makeText(MessagesActivity.this, "暂时没有新的消息", Toast.LENGTH_LONG).show();
                            break;
                        }
                        QMUICommonListItemView information1 = informationList.createItemView("请求帮助反馈");//这里的mQMUIGroupListView是xml文件中控件
                        information1.setOrientation(QMUICommonListItemView.VERTICAL);//设置为垂直模式，默认水平模式
                        information1.setDetailText("已经收到你的反馈，安排了" + jsonObject.getString("volunNo") + "号志愿者前往你处帮忙。");//在标题下方的详细信息
                        volunNo = jsonObject.getString("volunNo");
                        freshNo = jsonObject.getString("freshNo");
                        activityNo = jsonObject.getString("activityNo");

                        QMUIGroupListView.newSection(MessagesActivity.this)
                                .addItemView(information1, onClickListener)
                                .addTo(informationList);
                    } catch (Exception e) {

                    }

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        informationList = (QMUIGroupListView) findViewById(R.id.informationList);
        init();
    }


    private void init() {

        HttpTool selectVolunteerService = new HttpTool(HttpTool.MODE_POST, "/freshman/selectVolunteerService", MESS_SELECTVOLUNTEERSERVICE, handler);
        selectVolunteerService.addData("freshNo", "1");
        selectVolunteerService.addData("tags","迎新");
        selectVolunteerService.start();

    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof QMUICommonListItemView) {
                startTalk();
            }
        }
    };


    public void startTalk() {
        LCChatKit.getInstance().open("志愿者" + volunNo, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    finish();
                    Intent intent = new Intent(MessagesActivity.this, LCIMConversationActivity.class);
                    intent.putExtra(LCIMConstants.PEER_ID, "新生" + freshNo);
                    intent.putExtra("activityNo",activityNo);
                    startActivity(intent);
                } else {
                    Toast.makeText(MessagesActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}