package com.stx.xhb.dmgameapp.activities;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.content.Intent;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.Usernet;
import com.stx.xhb.dmgameapp.entity.Usertoken;
import com.stx.xhb.dmgameapp.utils.HttpAdress;
import com.stx.xhb.dmgameapp.utils.JsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class RegActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private UserSigninTask mAuthTask = null;

    private String reg_token; //会话token
    private ImageView iv_verify_pic;
    private Image img_verify_pic; //验证码图片
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private EditText mPicVerifyCode;
    private EditText mEmailVerifyCode;
    private AutoCompleteTextView mUserNameView;
    private TextView mSendEmailVerify;
    private View mLoginFormView;
    private TextView tv_login; //登录


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        TextView tv_title = (TextView) findViewById(R.id.title);
        tv_title.setText(this.getString(R.string.reg_tip));
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mUserNameView = (AutoCompleteTextView) findViewById(R.id.reg_username);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
//        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == R.id.login || id == EditorInfo.IME_NULL) {
//                    attemptSignin();
//                    return true;
//                }
//                return false;
//            }
//        });
//        mPicVerifyCode =(EditText) findViewById(R.id.reg_pic_verify_code);
//        mEmailVerifyCode = (EditText) findViewById(R.id.reg_email_verify_code);
//        mSendEmailVerify =(TextView) findViewById(R.id.reg_send_email_verify);

//        mSendEmailVerify.setOnClickListener(new View.OnClickListener(){ //发送邮件验证码
//            @Override
//            public void onClick(View view) {
//                getEmailVerify();
//            }
//        });
//
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                attemptSignin();
                startActivity(new Intent(RegActivity.this, RegActivity2.class));
            }
        });
//
//        mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress);
        tv_login = (TextView) findViewById(R.id.tv_reg_tip);


        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(RegActivity.this, LoginActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        //String imageUrl = HttpAdress.DMGEAME_URL + litpic;

        //下载图片，优先使用本地缓存图片
        iv_verify_pic =(ImageView) findViewById(R.id.reg_verify_pic);
        iv_verify_pic.setOnClickListener(new View.OnClickListener() { //点击图片验证码，重新获取
            @Override
            public void onClick(View v) {
                RegActivity.this.getVerifyPic();
            }
        });
        reg_token = "";

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        getVerifyPic();

    }

    private void getSessionToken(){
        String url = String.format(HttpAdress.REG_INIT_URL);
        Log.i("news getSession url:", url);
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    String json = new String(result);
                    Log.i("news getSessionToken:", json);
                    Usertoken usernet = new Gson().fromJson(JsonUtils.removeBOM(json), Usertoken.class);
                    Log.i("news usernet:", usernet.toString());
                    if (usernet != null && ((Usertoken.Results) usernet.getData()) != null) {
                        reg_token = ((Usertoken.Results) usernet.getData()).getGUID();

                        String url = String.format(HttpAdress.REG_PIC_VERIFY_URL, reg_token);
                        Log.i("news getVerifyPic:", url);
                        x.image().bind(iv_verify_pic, url);
                    }
                }
                catch(Exception ex){
                    Log.i("getSessionToken error:", ex.getMessage());
                }
                //initData();
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
    private void getVerifyPic(){
        //if(reg_token.length() < 1 )
        getSessionToken();
        //String url = String.format(HttpAdress.REG_PIC_VERIFY_URL, reg_token );
        //Log.i("getVerifyPic url:", url);
        //x.image().bind( iv_verify_pic, url );
        /*get(new RequestParams(url), new Callback.CommonCallback<>() {
            @Override
            public void onSuccess(String result) {
                String json = new String(result);
                Log.i("getSessionToken ret:", json);
                Usernet usernet = new Gson().fromJson(JsonUtils.removeBOM(json), Usernet.class);
                Log.i("usernet:", usernet.toString());
                if (usernet != null && ((Usernet.Results) usernet.getData()) != null) {
                    reg_token = ((Usernet.Results) usernet.getData()).getGUID();
                }
                //initData();
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
        });*/
    }

    private void sendEmailVerify(String email, String username, String pic_code ){ //发送图片验证码
        String url = String.format(HttpAdress.REG_EMAIL_VERIFY_URL, email, username, reg_token, pic_code);
        Log.i("news sendEmail url:", url);
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    //String nulldata = "\"data\":[]";
                    //String objectdata = "\"data\":{}";
                    String json = new String(result);
                    //json.replaceAll(nulldata, objectdata);
                    Log.i("news getSessionToken:", json);
                    Usernet usernet = new Gson().fromJson(JsonUtils.removeBOM(json), Usernet.class);
                    Log.i("news usernet:", usernet.toString());
                    if (usernet != null && ((Usertoken.Results) usernet.getData()) != null) {
                        reg_token = ((Usertoken.Results) usernet.getData()).getGUID();
                    }
                }
                catch(Exception ex){
                    Log.i("news getSession error:", ex.getMessage());
                }
                //initData();
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
    private void getEmailVerify(){
        String email = mEmailView.getText().toString();
        String username = mUserNameView.getText().toString();
        String picVerifyCode = mPicVerifyCode.getText().toString();
        sendEmailVerify(email, username, picVerifyCode);
    }
    private void showVerifyPic(String imageUrl){
        if(imageUrl.length() < 1)
            imageUrl="https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3538246725,1762874322&fm=116&gp=0.jpg";

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


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptSignin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String emailVerifyCode = mEmailVerifyCode.getText().toString();
        String userName = mUserNameView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserSigninTask(email, password, emailVerifyCode, userName);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       /* client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.stx.xhb.dmgameapp.activities/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
        */
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       /* Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.stx.xhb.dmgameapp.activities/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();*/
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserSigninTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final String mEmailVerifyCode;
        private final String mUserName;

        UserSigninTask(String email, String password, String emailVerifyCode,String userName) {
            mEmail = email;
            mPassword = password;
            mEmailVerifyCode = emailVerifyCode;
            mUserName = userName;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                signin();
                // Simulate network access.
                // Thread.sleep(2000);
            } catch (Exception e) {
                Log.i("news LException=>", e.getStackTrace().toString());
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        private void signin() {
            String url = String.format(HttpAdress.REG_URL, mEmailVerifyCode, mEmail, mUserName, mPassword);
            Log.i("news signin url:", url);
            x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    try {
                        String json = new String(result);
                        Log.i("news signin ret:", json);
                        //json解析
                        Usernet usernet = new Gson().fromJson(JsonUtils.removeBOM(json), Usernet.class);
                        Log.i("news usernet:", usernet.toString());
                        int signal = usernet.getSignal();//响应状态码

                    }
                    catch(Exception ex){
                        Log.i("news signin error:", ex.getMessage());
                    }
                    //initData();
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

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
