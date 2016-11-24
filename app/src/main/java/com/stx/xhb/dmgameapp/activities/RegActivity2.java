package com.stx.xhb.dmgameapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;

public class RegActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg2);


        TextView tv_title = (TextView) findViewById(R.id.title);
        tv_title.setText(this.getString(R.string.reg_tip));
    }
}
