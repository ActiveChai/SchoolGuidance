package com.example.schoolguidance.volun;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

/**
 * Created by wli on 15/12/4.
 * 实现自定义用户体系
 */
public class CustomUserProvider implements LCChatProfileProvider {

  private static CustomUserProvider customUserProvider;

  public synchronized static CustomUserProvider getInstance() {
    if (null == customUserProvider) {
      customUserProvider = new CustomUserProvider();
    }
    return customUserProvider;
  }

  private CustomUserProvider() {
  }

  public static List<LCChatKitUser> partUsers = new ArrayList<LCChatKitUser>();

  // 此数据均为 fake，仅供参考
  static {
    partUsers.add(new LCChatKitUser("志愿者007", "志愿者007", "emoji_unselected.png"));
    partUsers.add(new LCChatKitUser("新生2019", "新生2019", "emoji_unselected.png"));
    partUsers.add(new LCChatKitUser("新生2018", "新生2018", "emoji_unselected.png"));
  }

  @Override
  public void fetchProfiles(List<String> list, LCChatProfilesCallBack callBack) {
    List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
    for (String userId : list) {
      for (LCChatKitUser user : partUsers) {
        if (user.getUserId().equals(userId)) {
          userList.add(user);
          break;
        }
      }
    }
    callBack.done(userList, null);
  }

  @Override
  public List<LCChatKitUser> getAllUsers() {
    return partUsers;
  }
}
