package com.stx.xhb.dmgameapp.activities;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.UserToken;
import com.stx.xhb.dmgameapp.entity.ValidateEntity;
import com.stx.xhb.dmgameapp.utils.HttpAdress;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.stx.xhb.dmgameapp.view.LoadingDialog;

import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class RegActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private String reg_token; //会话token
    private ImageView iv_verify_pic;
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mPicVerifyCode;
    private AutoCompleteTextView mUserNameView;
    private View mLoginFormView;
    private TextView tv_login; //登录

    private static final int REQUEST_READ_CONTACTS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        //设置toolbar menu控件图片
        ImageButton main_action_menu = (ImageButton) findViewById(R.id.main_action_menu);
        main_action_menu.setImageResource(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        main_action_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tv_title = (TextView) findViewById(R.id.title);
        tv_title.setText(this.getString(R.string.reg_tip_title));
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mUserNameView = (AutoCompleteTextView) findViewById(R.id.reg_username);
//        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPicVerifyCode = (EditText) findViewById(R.id.reg_pic_verify_code);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmailVerify();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        tv_login = (TextView) findViewById(R.id.tv_reg_tip);


        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(RegActivity.this, LoginActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        //下载图片，优先使用本地缓存图片
        iv_verify_pic = (ImageView) findViewById(R.id.reg_verify_pic);
        iv_verify_pic.setOnClickListener(new View.OnClickListener() { //点击图片验证码，重新获取
            @Override
            public void onClick(View v) {
                getVerifyPic();
            }
        });
        reg_token = "";

        getVerifyPic();
    }

    private void getSessionToken() {
        String url = String.format(HttpAdress.REG_INIT_URL);
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    String json = new String(result);
                    UserToken userNet = new Gson().fromJson(JsonUtils.removeBOM(json), UserToken.class);
                    if (userNet != null && (userNet.getData()) != null) {
                        reg_token = (userNet.getData()).getGUID();

                        String url = String.format(HttpAdress.REG_PIC_VERIFY_URL, reg_token);
                        x.image().bind(iv_verify_pic, url);
                    }
                } catch (Exception ex) {
                    Log.i("getSessionToken error:", ex.getMessage());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void getVerifyPic() {
        getSessionToken();
    }

    private void sendEmailVerify(final String email, final String username, String pic_code) { //发送图片验证码
        String url = String.format(HttpAdress.REG_EMAIL_VERIFY_URL, email, username, reg_token, pic_code);
        Log.i("news sendEmail url:", url);
        showProgress(true);
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    showProgress(false);
                    String json = new String(result);
                    Log.i("news getSessionToken:", json);
                    ValidateEntity validateEntity = new Gson().fromJson(JsonUtils.removeBOM(json), ValidateEntity.class);
                    Log.i("news usernet:", validateEntity.toString());
                    if (validateEntity.getSignal() == 1) {
                        Toast.makeText(RegActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegActivity.this, RegConfirmActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    } else {
                        try {
                            String errorTip = ValidateEntity.getErroMsg(result);
                            Toast.makeText(RegActivity.this, errorTip, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        getVerifyPic();
                    }
                } catch (Exception ex) {
                    Log.i("news getSession error:", ex.getMessage());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                showProgress(false);

            }

            @Override
            public void onCancelled(CancelledException cex) {
                showProgress(false);

            }

            @Override
            public void onFinished() {
                showProgress(false);

            }
        });
    }

    private void getEmailVerify() {
        String email = mEmailView.getText().toString();
        String username = mUserNameView.getText().toString();
        String picVerifyCode = mPicVerifyCode.getText().toString();

        // Reset errors.
        mEmailView.setError(null);

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(RegActivity.this, R.string.error_field_required, Toast.LENGTH_LONG).show();
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            Toast.makeText(RegActivity.this, R.string.error_invalid_email, Toast.LENGTH_LONG).show();
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            sendEmailVerify(email, username, picVerifyCode);
        }
    }

    private void showVerifyPic(String imageUrl) {
        if (imageUrl.length() < 1)
            imageUrl = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3538246725,1762874322&fm=116&gp=0.jpg";

        x.image().bind(iv_verify_pic, imageUrl); //从网络下载图片
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private void showProgress(final boolean show) {
        if (show) {
            LoadingDialog.create(this);
        } else {
            LoadingDialog.cancel();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(RegActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }
}
