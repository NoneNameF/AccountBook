package com.example.accountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
private User changeuser=new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        ActivityCollector.addActivity(this);

        Button button = findViewById(R.id.change);
        button.setOnClickListener(this);
        Button button1=findViewById(R.id.returnHome);
        button1.setOnClickListener(this);

        changeuser.setName(User.LoginName);
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.change){
            EditText editText = findViewById(R.id.Text1);
            EditText editText1 = findViewById(R.id.Text2);
            String password = editText.getText().toString();
            String password_again = editText1.getText().toString();
            if (password.equals(password_again)) {
                SQLiteDatabase db = LitePal.getDatabase();
                List<User> Users = LitePal.where("name=?", "" + changeuser.getName() + "").find(User.class);
                if (Users.isEmpty()){
                    Toast.makeText(MyContext.getContext(), "程序错误 联系管理员", Toast.LENGTH_SHORT).show();
                }else {
                    for (User user : Users) {
                        if (user.getName().equals(changeuser.getName())) {
//                            Log.d("USER",""+user.getName()+"   "+user.getPassword());
                            changeuser.setPassword(password);
                            changeuser.updateAll("name=?",user.getName());
                            Log.d("USER","修改成功");
                            Intent intent = new Intent(ChangePasswordActivity.this, LogInActivity.class);
                            startActivity(intent);
                            ActivityCollector.finishAll();
                        } else {
                            Toast.makeText(MyContext.getContext(), "程序错误 联系管理员", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } else {
                Toast.makeText(MyContext.getContext(), "两次输入的密码不一致 请检查", Toast.LENGTH_SHORT).show();
            }
        }else if (v.getId()==R.id.returnHome){
            Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}