package com.example.accountbook;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private CharSequence UserName = null;
    private final List<Account> accountList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ActivityCollector.addActivity(this);
//获取用户的用户名
        Intent intent = getIntent();
        UserName = intent.getStringExtra("IntentName");
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
                textView.setText(UserName);
            }
        });
//为侧边滑栏设置点击事件
        NavigationView navigationView = findViewById(R.id.NavigationView);
        navigationView.setCheckedItem(R.id.Home);
        navigationView.setNavigationItemSelectedListener(this);
//测试代码  测试列表是不是正常工作
        testCode();
    }

    private void testCode() {
        int x = 5;
        while ((x--) != 0) {
            Calendar calendar = new GregorianCalendar();
            Account account = new Account(UserName.toString(),Account.InOrOutType.food, 32.8, "test1", calendar);
            accountList.add(account);
            Account account1 = new Account(UserName.toString(),Account.InOrOutType.beauty_hair, 3.8, "test2", calendar);
            accountList.add(account1);
            Account account2 = new Account(UserName.toString(),Account.InOrOutType.cars, 56.8, "test3", calendar);
            accountList.add(account2);
            Account account3 = new Account(UserName.toString(),Account.InOrOutType.clothes, 122.8, "test4", calendar);
            accountList.add(account3);
        }
        RecyclerView recyclerView=findViewById(R.id.RecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        AccountAdapter accountAdapter=new AccountAdapter(accountList);
        recyclerView.setAdapter(accountAdapter);
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
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                break;
//跳转到修改密码的界面
            case R.id.setting:
                intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
                intent.putExtra("Name", UserName);
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
            case R.id.search:

                break;
            case R.id.refresh:

                break;
            case R.id.add:
Intent intent=new Intent(MainActivity.this,AddAccount.class);
startActivity(intent);
                break;

        }
        return true;
    }
}