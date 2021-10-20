package com.example.accountbook;

import android.widget.EditText;

public class User {
    private String account_info;     //账户号
    private String name;             //用户名
    private String user_head_img;    //头像路径
    private String password;         //密码

    public User(String editText1, String editText2) {
        this.account_info=editText1;
        this.password=editText2;
    }

    public boolean Logout(){
        return true;
    }
    public boolean SignIn(){
        
        return true;
    }
    public String getAccount_info() {
        return account_info;
    }

    public void setAccount_info(String account_info) {
        this.account_info = account_info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_head_img() {
        return user_head_img;
    }

    public void setUser_head_img(String user_head_img) {
        this.user_head_img = user_head_img;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
