package com.xiaoqiang.clockdemo;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
/**
 * Created by Administrator on 2019/1/26 0026.
 */
public class ClockActivity extends Activity implements View.OnClickListener {

    private Button btn_close;
    private MediaPlayer player1;
    private Vibrator vibrator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_activity);
        btn_close = findViewById(R.id.close);
        btn_close.setOnClickListener(this);
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        player1 = new MediaPlayer();
        player1.create(this,R.raw.one).start();
       /* Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);*/
        vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        long[] patter = {500, 1000, 1000, 50};
        vibrator.vibrate(patter, 0);
       /* new AlertDialog.Builder(this).setTitle("闹钟").setMessage("小猪小猪快起床~")
                .setPositiveButton("关闭闹铃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ClockActivity.this.finish();

                    }
                })
                .show();*/
    }

    @Override
    public void onClick(View view) {
        ClockActivity.this.finish();
        player1.stop();
        player1.release();
        vibrator.cancel();
    }


}
