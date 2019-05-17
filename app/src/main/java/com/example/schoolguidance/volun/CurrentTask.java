package com.example.schoolguidance.volun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.schoolguidance.R;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class CurrentTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volun_curr_task);
        startTalk();
    }

    public void startTalk()
    {
        LCChatKit.getInstance().open("志愿者007", new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    finish();
                    Intent intent = new Intent(CurrentTask.this, LCIMConversationActivity.class);
                    intent.putExtra(LCIMConstants.PEER_ID, "新生2019");
                    startActivity(intent);
                } else {
                    Toast.makeText(CurrentTask.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
