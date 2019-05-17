package com.example.schoolguidance.volun;

import android.app.Application;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.example.schoolguidance.R;
import com.example.schoolguidance.tool.CustomUserProvider;

import cn.leancloud.chatkit.LCChatKit;

public class ChatKit extends Application {

    // appId、appKey 可以在「LeanCloud  控制台 > 设置 > 应用 Key」获取
    private final String APP_ID = "yn5Wn5zVTVNbLmPdfliIgjTh-gzGzoHsz";
    private final String APP_KEY = "8VGe9xoKiNqrRJprjncvxTSL";

    @Override
    public void onCreate() {
        super.onCreate();
        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        AVOSCloud.setDebugLogEnabled(true);
//    AVOSCloud.useAVCloudUS();
        LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);
        AVIMClient.setAutoOpen(true);
        PushService.setDefaultPushCallback(this, VolunMainActivity.class);
        PushService.setAutoWakeUp(true);
        PushService.setDefaultChannelId(this, "default");

        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // 保存成功
                    String installationId = AVInstallation.getCurrentInstallation().getInstallationId();
                    System.out.println("---  " + installationId);
                } else {
                    // 保存失败，输出错误信息
                    System.out.println("failed to save installation.");
                }
            }
        });
    }
}