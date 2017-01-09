package com.jinyuankeji.yxm.findhuo.base.base_chat;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;


/**
 * Created by HDL on 2016/8/11.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options = new EMOptions();
        options.setMipushConfig("2882303761517500800", "5371750035800");

        options.setAcceptInvitationAlways(true);

        EaseUI.getInstance().init(this, options);

//        EMClient.getInstance().setDebugMode(true);
        EMClient.getInstance().updateCurrentUserNick(getSharedPreferences(GlobalField.USERINFO_FILENAME, MODE_PRIVATE).getString("username", "hdl"));//设置推送的昵称

    }
}
