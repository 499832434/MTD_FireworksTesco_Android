package com.chem99.fireworkstesco.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.chem99.fireworkstesco.R;
import com.chem99.fireworkstesco.activity.BaseActivity;
import com.chem99.fireworkstesco.utils.TimesUtils;
import com.chem99.fireworkstesco.view.CustomTitleBar;

/**
 * Created by zongshuo on 2017/7/10.
 */
public class ForgotPasswordActivity extends BaseActivity{

    private EditText telephoneET,verificationCodeET;
    private Button commitB;
    private TimesUtils timesUtils=null;
    private TextView verificationCodeTV;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initView();
    }


    private void initView(){
        ((CustomTitleBar)findViewById(R.id.customTitleBar)).setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        commitB= (Button) findViewById(R.id.commitB);
        commitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this,ResetPasswordActivity.class));
            }
        });
        telephoneET= (EditText) findViewById(R.id.telephoneET);
        verificationCodeET= (EditText) findViewById(R.id.verificationCodeET);
        telephoneET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(telephoneET.getText().toString())) {
                    if (!TextUtils.isEmpty(verificationCodeET.getText().toString())) {
                        commitB.setBackgroundResource(R.drawable.ic_bg_login_button);
                    } else {
                        commitB.setBackgroundResource(R.drawable.ic_bg_login_button_un);
                    }
                } else {
                    commitB.setBackgroundResource(R.drawable.ic_bg_login_button_un);
                }
            }
        });

        verificationCodeET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(verificationCodeET.getText().toString())) {
                    if (!TextUtils.isEmpty(telephoneET.getText().toString())) {
                        commitB.setBackgroundResource(R.drawable.ic_bg_login_button);
                    } else {
                        commitB.setBackgroundResource(R.drawable.ic_bg_login_button_un);
                    }
                } else {
                    commitB.setBackgroundResource(R.drawable.ic_bg_login_button_un);
                }
            }
        });

        verificationCodeTV= (TextView) findViewById(R.id.verificationCodeTV);
        verificationCodeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timesUtils=new TimesUtils(60000,1000,verificationCodeTV,ForgotPasswordActivity.this);
                timesUtils.start();
            }
        });
    }
}
