package com.example.accountbook;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import java.util.List;

public class registerActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActivityCollector.addActivity(this);
        Button button = findViewById(R.id.register);
        button.setOnClickListener(this);
        Button button1=findViewById(R.id.returnLogin);
        button1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.register){
            EditText editText = findViewById(R.id.account_input);
            EditText editText1 = findViewById(R.id.password_input);
            EditText editText2 = findViewById(R.id.password_input_again);
            String name = editText.getText().toString();
            String password = editText1.getText().toString();
            String password_again = editText2.getText().toString();
            if (password.equals(password_again)) {
                SQLiteDatabase db = LitePal.getDatabase();
                List<User> Users = LitePal.where("name=?", "" + name + "").find(User.class);
                if (Users.isEmpty()){
                    User user1 = new User(name, password);
                    user1.save();
                    Toast.makeText(registerActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registerActivity.this, LogInActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    for (User user : Users) {
                        if (user.getName().equals(name)) {
                            Toast.makeText(registerActivity.this, "用户名已被注册 请更改", Toast.LENGTH_SHORT).show();
                        } else {
                            User user1 = new User(name, password);
                            user1.save();
                            Toast.makeText(registerActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(registerActivity.this, LogInActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            } else {
                Toast.makeText(registerActivity.this, "两次输入的密码不一致 请检查", Toast.LENGTH_SHORT).show();
            }
        }else if (v.getId()==R.id.returnLogin){
            Intent intent = new Intent(registerActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        }

    }
}