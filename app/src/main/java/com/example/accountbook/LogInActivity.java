package com.example.accountbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.checkbox.MaterialCheckBox;

import org.litepal.LitePal;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
//      生成全局Context
        Context context = getApplicationContext();
        MyContext myContext = new MyContext(context);
//      初始化litepal
//        LitePal.initialize(MyContext.getContext());
//        db = LitePal.getDatabase();
//      检测之前是否选择了记住用户
        SharedPreferences pref = getSharedPreferences("UserSave", MODE_PRIVATE);
        if (pref.getBoolean("IsChecked", false)) {
            EditText editText=findViewById(R.id.LoginName);
            EditText editText2=findViewById(R.id.LoginPassword);
            String name = pref.getString("Name", "");
            String password = pref.getString("Password", "");
            User user = new User(name, password);
            if(user.SignIn()) ToMain();
            else Toast.makeText(this, "抱歉，登录失败。可能密码已经变更", Toast.LENGTH_SHORT).show();
        } else {

        }
//      为两个按钮注册点击事件
        Button button1 = findViewById(R.id.loginButton);
        button1.setOnClickListener(this);
        Button button2 = findViewById(R.id.registerButton);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton) {
//获取用户输入的信息
            EditText editText1 = findViewById(R.id.LoginName);
            EditText editText2 = findViewById(R.id.LoginPassword);
            String Name = editText1.getText().toString();
            String Password = editText2.getText().toString();
            User user = new User(Name, Password);
//          尝试登录
            if (user.SignIn()) {
                //检查是否勾选了记住功能 保存用户数据
                MaterialCheckBox checkBox = findViewById(R.id.RememberMe);
                if (checkBox.isChecked()) {
                    @SuppressLint("CommitPrefEdits")
                    SharedPreferences.Editor editor = getSharedPreferences("UserSave", MODE_PRIVATE).edit();
                    editor.putBoolean("IsChecked", true);
                    editor.putString("Name", Name);
                    editor.putString("Password", Password);
                    editor.apply();
                }
                ToMain();
            } else {
                Toast.makeText(this, "抱歉，登录失败", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.registerButton) {
            Toast.makeText(this, " 此功能暂不开放", Toast.LENGTH_SHORT).show();
        }
    }

    private void ToMain(){
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}