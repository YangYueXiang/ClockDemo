package com.xiaoqiang.clockdemo.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.xiaoqiang.clockdemo.ClockActivity;


public class CallAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent.setClass(context, ClockActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
