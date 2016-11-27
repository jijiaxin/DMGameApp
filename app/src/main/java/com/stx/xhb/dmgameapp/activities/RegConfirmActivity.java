package com.stx.xhb.dmgameapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.ValidateEntity;
import com.stx.xhb.dmgameapp.utils.HttpAdress;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.stx.xhb.dmgameapp.view.LoadingDialog;

import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class RegConfirmActivity extends Activity {

    String userName = "";
    String email = "";

    private EditText pwd1, pwd2;
    private EditText validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg2);

        initIntent();
        initView();
    }

    private void initIntent(){
        userName = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");

    }

    private void initView(){
        //设置toolbar menu控件图片
        ImageButton main_action_menu = (ImageButton)findViewById(R.id.main_action_menu);
        main_action_menu.setImageResource(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        main_action_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pwd1 = (EditText)findViewById(R.id.reg2_pwd);
        pwd2 = (EditText)findViewById(R.id.reg2_pwd2);
        validate = (EditText) findViewById(R.id.validate);
        TextView tv_title = (TextView) findViewById(R.id.title);
        tv_title.setText(this.getString(R.string.reg_tip));

        findViewById(R.id.email_sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (varifyPwd()){
                    reg();
                }
            }
        });
    }

    /**
     * 验证密码
     */
    private boolean varifyPwd(){
        String pwdStr1 = pwd1.getText().toString();
        String pwdStr2 = pwd2.getText().toString();
        if (pwdStr1.equals(pwdStr2)){
            return true;
        }else{
            Toast.makeText(this, "两次输入的密码不一样，请重新输入", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void reg(){
        LoadingDialog.create(this);
        String pwd = pwd1.getText().toString();
        String verifyCode = validate.getText().toString();
        String url = String.format(HttpAdress.REG_URL,verifyCode, email, userName, pwd);
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LoadingDialog.cancel();
                String json = new String(result);
                LogUtil.e(json);
                ValidateEntity validateEntity = new Gson().fromJson(JsonUtils.removeBOM(json), ValidateEntity.class);
                if (validateEntity.getSignal() == 1){
                    Toast.makeText(RegConfirmActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                }else{
                    try {
                        String erroTip = ValidateEntity.getErroMsg(result);
                        Toast.makeText(RegConfirmActivity.this, erroTip, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LoadingDialog.cancel();

            }

            @Override
            public void onCancelled(CancelledException cex) {
                LoadingDialog.cancel();

            }

            @Override
            public void onFinished() {
                LoadingDialog.cancel();

            }
        });
    }
}
