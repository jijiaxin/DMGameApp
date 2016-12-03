package com.stx.xhb.dmgameapp.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.stx.xhb.dmgameapp.MainActivity;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.utils.ChannelUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * 启动页
 */
public class SplashActivity extends FragmentActivity {
    //private SplashAD splashAD;
    private LinearLayout ll_ad;

    @Override
    protected void onPause() {
        super.onPause();
        getChannelInfo();
        MobclickAgent.onResume(this);       //统计时长
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(R.layout.activity_welcome);
        ll_ad = (LinearLayout) findViewById(R.id.ll_ad);
        getChannelInfo();
        jumpToMain();
    }

    //初始化窗体布局
    private void initWindow() {
        //设置透明状态栏
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    //@Override
    public void onADDismissed() {
        jumpToMain();
    }

    private void jumpToMain() {
        //使用Handler发送一个延迟1000ms的消息
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);//设定睡眠时间1000ms
    }

    //@Override
    public void onNoAD(int i) {
        jumpToMain();
    }

    //@Override
    public void onADPresent() {

    }

    //@Override
    public void onADClicked() {

    }

    //防止用户返回键退出APP
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getChannelInfo() {
        ChannelUtils channelUtils = new ChannelUtils(this);
        channelUtils.getChannelInfoNet();
    }
}
