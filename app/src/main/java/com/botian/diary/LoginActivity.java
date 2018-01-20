package com.botian.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.acet_username)
    AppCompatEditText acetUsername;
    @BindView(R.id.acet_password)
    AppCompatEditText acetPassword;

    private static final String APP_ID = "fef72facfc7001fd987b8974530d8e9d"; //把你在Bmob官网获取的APPID放到这里
    @BindView(R.id.login_btn)
    public Button loginBtn;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Bmob.initialize(this, APP_ID); //初始化BmobSDK

    }

    public void btnShowLogin() {
        final String username = acetUsername.getText().toString();
        final String password = acetPassword.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        final BmobUser user = new BmobUser();
        user.setUsername(username);
        user.setPassword(password);
        user.login(LoginActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(LoginActivity.this, "登录失败，请检查用户名及密码", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick({R.id.login_btn, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                btnShowLogin();
                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }


}