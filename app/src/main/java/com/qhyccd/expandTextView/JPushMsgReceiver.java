package com.qhyccd.expandTextView;

import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * Created by tx on 2018/9/8.
 */

public class JPushMsgReceiver extends JPushMessageReceiver {

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        Log.i("》》》 "," jPushMessage "+jPushMessage.toString());
        super.onAliasOperatorResult(context, jPushMessage);
    }
}
