package com.example.accountbook;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
/**
* @author CMS
*
* */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final List<Account> accountList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Log.d("USER", "进入MainActivity");
        MyContext.setContext(getApplicationContext());
//将此Activity添加进管理
        ActivityCollector.addActivity(this);
//将系统默认的actionbar取消了 使用自己写的Toolbar 想要菜单显示在Toolbar上就要先把toolbar设为Actionbar
        Toolbar toolbar = findViewById(R.id.main_appbar);
        setSupportActionBar(toolbar);
//为左边的导航按钮设置点击事件 打开抽屉
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawerLayout = findViewById(R.id.MainDrawerLayout);
                drawerLayout.openDrawer(GravityCompat.START);
//更新用户名
//本来是写在外面的但是不成功  得到的textview对象是null
//问过大佬知道了是这时候抽屉的布局还没有加载出来
//在网上找到几个方法 尝试view.inflate加载 但是失败
//所以干脆写到点击事件里 每次打开抽屉都检查更改一次
                TextView textView = findViewById(R.id.UserName);
                textView.setText(User.LoginName);
            }
        });
//为侧边滑栏设置点击事件
        NavigationView navigationView = findViewById(R.id.NavigationView);
        navigationView.setCheckedItem(R.id.Home);
        navigationView.setNavigationItemSelectedListener(this);
//测试代码  测试列表是不是正常工作
        refresh();
    }

    private void refresh() {
        Log.d("USER", "开始添加内容");
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("请等待加载");
        progressDialog.setMessage("正在加载中");
        progressDialog.setCancelable(false);
        progressDialog.show();
        List<Account> Accounts = LitePal.where("belong=?", User.LoginName).order("sec desc").find(Account.class);
        accountList.clear();
        Log.d("USER", "获取数据库");
        if (Accounts.isEmpty()) {
            Log.d("USER", "数据空");
            TextView textView=findViewById(R.id.mainnothings);
            textView.setText("这里什么也没有");
        }
        else {
            TextView textView=findViewById(R.id.mainnothings);
            textView.setText("");
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
                long ID=account1.getID();
                long sec=account1.getSec();
                Account account = new Account(belong, Account.StringToInOrOutType(type), money, remark, year, month, day, hour, min,ID,sec);
                Log.d("USER", account.getType());
                accountList.add(account);
            }
            Log.d("USER", "整合完成");
        }
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        AccountAdapter accountAdapter = new AccountAdapter(accountList, this);
        recyclerView.setAdapter(accountAdapter);
        progressDialog.cancel();
    }

    //侧面滑栏的点击事件
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.Home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:
                intent=new Intent(MainActivity.this,ChooseDate.class);
                startActivity(intent);
//                Toast.makeText(this, "没有实现这个功能", Toast.LENGTH_SHORT).show();
                break;
//跳转到修改密码的界面
            case R.id.setting:
                intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;
//退出登录 跳转到登录界面
            case R.id.logout:
                SharedPreferences.Editor editor = getSharedPreferences("UserSave", MODE_PRIVATE).edit();
                editor.putBoolean("IsChecked", false);
                editor.putString("Name", "");
                editor.putString("Password", "");
                editor.apply();
                intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //右上角Item的点击事件
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                refresh();
                break;
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, AddAccount.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}