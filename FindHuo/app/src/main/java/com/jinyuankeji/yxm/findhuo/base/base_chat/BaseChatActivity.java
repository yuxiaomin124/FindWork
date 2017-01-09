package com.jinyuankeji.yxm.findhuo.base.base_chat;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/8/12.
 */
public class BaseChatActivity extends AppCompatActivity {
    public Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mActivity = this;
    }
}
