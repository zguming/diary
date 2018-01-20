package com.botian.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.register_username)
    AppCompatEditText registerUsername;
    @BindView(R.id.register_password)
    AppCompatEditText registerPassword;
    @BindView(R.id.register_btn)
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

    }

    public void btnShow() {
        final String username = registerUsername.getText().toString();
        final String password = registerPassword.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (registerUsername.length() < 4) {
            Toast.makeText(this, "用户名不能少于4位", Toast.LENGTH_SHORT).show();
            return;
        }

        if (registerPassword.length() < 6) {
            Toast.makeText(this, "密码不能低于6位", Toast.LENGTH_SHORT).show();
            return;
        }

        /**
         * Bmob注册
         */
        final BmobUser user = new BmobUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(this, new SaveListener() { //回调2个方法，成功，失败
            @Override
            public void onSuccess() {
                Toast.makeText(RegisterActivity.this, "注册成功，返回登录", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(RegisterActivity.this, "注册失败，用户名已存在或网络问题", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick(R.id.register_btn)
    public void onViewClicked() {
        btnShow();
    }
}