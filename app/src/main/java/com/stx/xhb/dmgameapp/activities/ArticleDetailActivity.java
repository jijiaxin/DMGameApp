package com.stx.xhb.dmgameapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.google.gson.Gson;
import com.software.shell.fab.ActionButton;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.Detail;
import com.stx.xhb.dmgameapp.entity.LoginEntity;
import com.stx.xhb.dmgameapp.entity.UserEntity;
import com.stx.xhb.dmgameapp.utils.DateUtils;
import com.stx.xhb.dmgameapp.utils.HttpAdress;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.stx.xhb.dmgameapp.utils.SystemBarTintManager;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.utils.UserUtils;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.Constant;
import com.umeng.socialize.media.UMImage;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.CookieStore;
import java.net.URLDecoder;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * 文章详情界面
 */
public class ArticleDetailActivity extends ActionBarActivity implements View.OnClickListener {

    private WebView comment_web;
    private Toolbar toolbar;
    private String body;
    private String id;
    private String typeid;
    private String title;//标题
    private String writer;//作者
    private String senddate;//发布时间
    private SmoothProgressBar webProgress;//进度条
    private ActionButton actionButton;//评论按钮
    String url ;

    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
            {
                    SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,
                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
            };
    private String decode;
    private String arcurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initWindow();
        initView();
        url = getIntent().getStringExtra("url");
        initData(url);
        initListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onResume(this);       //统计时长
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPause(this);
    }

    //初始化窗体布局
    private void initWindow() {
        SystemBarTintManager tintManager;
        //由于沉浸式状态栏需要在Android4.4.4以上才能使用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorBackground));
            tintManager.setStatusBarTintEnabled(true);
        }
    }


    //获取控件
    private void initView() {
        comment_web = (WebView) findViewById(R.id.coment_web);
        actionButton = (ActionButton) findViewById(R.id.action_button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        webProgress = (SmoothProgressBar) findViewById(R.id.web_progress);
        //2.替代
        setSupportActionBar(toolbar);
        //加载背景颜色
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBackground)));
        //设置显示返回上一级的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置标题
        getSupportActionBar().setTitle("文章详情");
        //设置标题栏字体颜色
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        //设置悬浮按钮的背景图片
        actionButton.setImageResource(R.drawable.note_publish_img_unpressed);//设置按钮资源文件
        actionButton.setImageSize(65);//设置图片按钮的大小
        //修改友盟分享对话框
        ProgressDialog dialog =  new ProgressDialog(this);
        dialog.setMessage("分享中...");
        Config.dialog = dialog;

    }


    //初始化数据
    private void initData(String url) {
        //设置cookie
        synCookies();
        //启用支持javascript
        WebSettings settings = comment_web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setTextSize(WebSettings.TextSize.LARGEST);//设置字体大小
        settings.setDefaultTextEncodingName("utf-8");//设置默认编码格式
//        //自适应屏幕
        settings.setUseWideViewPort(true);
        comment_web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                webProgress.setProgress(newProgress);
                if (webProgress != null && newProgress != 100) {
                    webProgress.setVisibility(View.VISIBLE);
                } else if (webProgress != null) {
                    webProgress.setVisibility(View.GONE);
                }
            }
        });
        //加载网络资源
            //设置在同一个webview中打开新的网页
        comment_web.loadUrl(url);
            comment_web.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });

    }

    //改写物理按键——返回的逻辑
    //返回无效是重定向的原因
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (comment_web != null && comment_web.canGoBack()) {
                comment_web.canGoBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);

    }

    //设置事件监听
    private void initListener() {
        //toolbard的返回按钮事件监听
        toolbar.setNavigationOnClickListener(this);
        //点击按钮跳转到评论界面
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticleDetailActivity.this, CommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("typeid", typeid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    //toolbar事件监听方法
    @Override
    public void onClick(View v) {
        //返回上一页
        finish();
    }
    //点击分享
    @OnClick(R.id.article_share)
    public void onClick() {
        new ShareAction(this).setDisplayList( displaylist )
                .withText(title)
                .withTitle(title)
                .withTargetUrl(arcurl)
                .withMedia(new UMImage(this,R.drawable.app))
                .setListenerList(getUmShareListener(), getUmShareListener()).open();
    }

    @NonNull
    private UMShareListener getUmShareListener() {
        return new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA share_media) {
                ToastUtil.showAtCenter(ArticleDetailActivity.this,"分享成功");
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                ToastUtil.showAtCenter(ArticleDetailActivity.this,"分享失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                ToastUtil.showAtCenter(ArticleDetailActivity.this,"取消分享");
            }
        };
    }


    /**
     * 同步一下cookie
     */
    public void synCookies() {
        LogUtil.e("set cookie================");
        LoginEntity loginEntity = UserUtils.getLoginInfo(this);
        if (loginEntity != null){
            CookieSyncManager.createInstance(this);
            String token = loginEntity.getData().getToken();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setCookie(url, "token="+token);
            CookieSyncManager.getInstance().sync();
            LogUtil.e("cookie: " + cookieManager.getCookie(url));
        }
    }
}
