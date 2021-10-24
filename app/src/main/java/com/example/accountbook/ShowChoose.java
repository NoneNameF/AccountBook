package com.example.accountbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ShowChoose extends AppCompatActivity {
    private long begin;
    private long done;
    private final List<Account> accountList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_show_choose);
        Intent intent = getIntent();
        begin = intent.getLongExtra("begin", Long.MIN_VALUE);
        done = intent.getLongExtra("done", Long.MAX_VALUE);


        Log.d("USER", "开始添加内容");
        ProgressDialog progressDialog = new ProgressDialog(ShowChoose.this);
        progressDialog.setTitle("请等待加载");
        progressDialog.setMessage("正在加载中");
        progressDialog.setCancelable(false);
        progressDialog.show();
        List<Account> Accounts = LitePal.where("belong=?", User.LoginName).order("sec desc").find(Account.class);
        accountList.clear();
        Log.d("USER", "获取数据库");
        if (Accounts.isEmpty()) {
            Log.d("USER", "数据空");
            TextView textView=findViewById(R.id.nothings);
            textView.setText("这里什么也没有");
        }
        else {
            Log.d("USER", "开始整合");
            for (Account account1 : Accounts) {
//虽然有更好的办法 但是我在这一步耽误太多时间了不想想了 所以就用了笨办法
                String belong = account1.getBelong();
                String type = account1.getType();
                double money = account1.getMoney();
                String remark = account1.getRemark();
                int year = account1.getYear();
                int month = account1.getMonth();
                int day = account1.getDay();
                int hour = account1.getHour();
                int min = account1.getHour();
                long ID = account1.getID();
                long sec = account1.getSec();
                Account account = new Account(belong, Account.StringToInOrOutType(type), money, remark, year, month, day, hour, min, ID, sec);
                if ((account.getSec()<begin) || (account.getSec()>done)) continue;
                Log.d("USER", account.getType());
                accountList.add(account);
            }
            if (accountList.isEmpty()){
                TextView textView=findViewById(R.id.nothings);
                textView.setText("这里什么也没有");
            }
            Log.d("USER", "整合完成");
            RecyclerView recyclerView = findViewById(R.id.RecyclerViewShowChoose);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            AccountAdapter accountAdapter = new AccountAdapter(accountList, this);
            recyclerView.setAdapter(accountAdapter);
        }
        progressDialog.cancel();
    }
}