package com.xiaoqiang.clockdemo;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.xiaoqiang.clockdemo.clock.AlarmHelper;
import com.xiaoqiang.clockdemo.clock.ObjectPool;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start, end;
    Calendar mCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        start.setOnClickListener(this);
        end.setOnClickListener(this);
        ObjectPool.mAlarmHelper = new AlarmHelper(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                /*int mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = mCalendar.get(Calendar.MINUTE);
                new TimePickerDialog(MainActivity.this,
                        (view, hourOfDay, minute) -> {
                            mCalendar.setTimeInMillis(System
                                    .currentTimeMillis());
                            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            mCalendar.set(Calendar.MINUTE, minute);
                            mCalendar.set(Calendar.SECOND, 0);
                            mCalendar.set(Calendar.MILLISECOND, 0);
                            ObjectPool.mAlarmHelper.openAlarm(32, "闹钟",
                                    "请设置提醒时间", mCalendar.getTimeInMillis());
                        }, mHour, mMinute, true).show();*/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
            String str = "201901261735";
            try {
                long millionSeconds = sdf.parse(str).getTime();
                ObjectPool.mAlarmHelper.openAlarm(32, "闹钟",
                        "请设置提醒时间", millionSeconds);
            } catch (ParseException e) {
               e.printStackTrace();
           }

//                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        mCalendar.setTimeInMillis(System
//                                .currentTimeMillis());
//                        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        mCalendar.set(Calendar.MINUTE, minute);
//                        mCalendar.set(Calendar.SECOND, 0);
//                        mCalendar.set(Calendar.MILLISECOND, 0);
//                        ObjectPool.mAlarmHelper.openAlarm(32, "闹钟",
//                                "请设置提醒时间", mCalendar.getTimeInMillis());
//                    }
//                },mHour,mMinute,true).show();

                break;
            case R.id.end:
                showBackDialog();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showBackDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void showBackDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("是否关闭闹钟")
                .setPositiveButton("关闭",
                        (dialog, which) -> {
                            System.exit(0);
                            android.os.Process
                                    .killProcess(android.os.Process
                                            .myPid());

                            dialog.dismiss();
                        })
                .setNegativeButton("取消",
                        (dialog, which) -> dialog.dismiss());
        AlertDialog ad = builder.create();
        ad.show();
    }
}
