package com.example.accountbook;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class User extends LitePalSupport {
    private String name;             //用户名
    private String password;         //密码

    public User(String editText1, String editText2) {
        this.name=editText1;
        this.password=editText2;
    }

    public User() {
    }

    public boolean Logout(){
        return true;
    }
    public boolean SignIn(){
//        SQLiteDatabase db = LitePal.getDatabase();
        List<User> Users = LitePal.where("name=?", "" +this.name+ "").find(User.class);
//        LitePal.deleteAll(User.class);

        if (Users.isEmpty()) return false;
        else {
            User user=Users.get(0);
            Log.d("USER","密码"+user.getPassword()+"this"+this.password);
            if (this.password.equals(user.password)) return true;
            else return false;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
