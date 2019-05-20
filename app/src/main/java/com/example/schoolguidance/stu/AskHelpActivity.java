package com.example.schoolguidance.stu;

import android.content.Intent;
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
import com.example.schoolguidance.volun.CurrentTask;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class AskHelpActivity extends AppCompatActivity {

    EditText content_ask_help;
    QMUIRoundButton btn_submit_help;
    QMUIRoundButton btn_cancel_help;
    TextView const_text;
    Boolean askIsWait=false;//后台获取，该请求状态为真代表：等待志愿者接
    Boolean askIsTaken=false;//后台获取，该请求状态为真代表：有志愿者接了
    String content="lalalal";//后台获取，刚才发布的请求的内容


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_ask_help);
        init();
        if (askIsWait)
        {
            const_text.setText("您有请求正在等待志愿者接受，请耐心等待！您也可以选择取消，重新发布新的请求！");
            content_ask_help.setHint(content);
            content_ask_help.setEnabled(false);
            btn_submit_help.setEnabled(false);
            btn_cancel_help.setEnabled(true);
        }
        else if(askIsTaken)
            startTalk();
    }

    public void startTalk()
    {
        LCChatKit.getInstance().open("新生2019", new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    finish();
                    Intent intent = new Intent(AskHelpActivity.this, LCIMConversationActivity.class);
                    intent.putExtra(LCIMConstants.PEER_ID, "志愿者007");
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

                }
                else
                {
                    const_text.setText("您有请求正在等待志愿者接受，请耐心等待！您也可以选择取消，重新发布新的请求！");
                    content_ask_help.setHint(content);
                    content_ask_help.setEnabled(false);
                    btn_submit_help.setEnabled(false);
                    btn_cancel_help.setEnabled(true);
                }
                break;
            case R.id.btn_cancel_help:
                askIsWait=false;
                const_text.setText("请输入你的困难与想得到的帮助");
                content_ask_help.setHint("");
                content_ask_help.setEnabled(true);
                btn_submit_help.setEnabled(true);
                btn_cancel_help.setEnabled(false);
                break;
        }
    }
}