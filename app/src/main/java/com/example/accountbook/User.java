package com.example.accountbook;

import android.util.Log;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class User extends LitePalSupport {
    public static String LoginName;
    private String name;             //用户名
    private String password;         //密码

    public User(String editText1, String editText2) {
        this.name = editText1;
        this.password = editText2;
    }

    public User() {
    }

    public boolean SignIn() {
        Log.d("USER", "尝试登录");
//        SQLiteDatabase db = LitePal.getDatabase();
        List<User> Users = LitePal.where("name=?", "" + this.name + "").find(User.class);
//        LitePal.deleteAll(User.class);
        Log.d("USER", "获取数据库成功");
        if (Users.isEmpty()) return false;
        else {
            User user = Users.get(0);
//            Log.d("USER",""+ user.getName()+"  " +user.getPassword() +"   "+this.password+"");
            return this.password.equals(user.password);
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
