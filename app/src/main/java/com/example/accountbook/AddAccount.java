package com.example.accountbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;

import top.defaults.view.DateTimePickerView;

public class AddAccount extends AppCompatActivity implements View.OnClickListener {
    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int minute;
    private String UserName;
    private String[] type;
    private Account.InOrOutType uploadType;
    private final Calendar calendar = new GregorianCalendar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        ActivityCollector.addActivity(this);
        Log.d("USER", "进入添加界面");

//配置DateTimePickerView
        DateTimePickerView dateTimePickerView = findViewById(R.id.datePickerView);
        dateTimePickerView.setStartDate(Calendar.getInstance());
        dateTimePickerView.setSelectedDate(new GregorianCalendar(2021, 0, 1, 0, 0));
        dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
            public void onSelectedDateChanged(Calendar date) {
                year = date.get(Calendar.YEAR);
                month = date.get(Calendar.MONTH);
                dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                hour = date.get(Calendar.HOUR_OF_DAY);
                minute = date.get(Calendar.MINUTE);
//                String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日%02d时%02d分", year, month, dayOfMonth, hour, minute);
//                Toast.makeText(AddAccount.this, dateString, Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("USER", "配置DateTimePickerView完成");
//注册Spinner选中事件
        Spinner spinner = findViewById(R.id.ChooseType);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = getResources().getStringArray(R.array.Type);
//                Toast.makeText(AddAccount.this, type[position], Toast.LENGTH_SHORT).show();
                uploadType = Account.StringToInOrOutType(type[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.d("USER", "注册Spinner选中事件完成");
//注册按键
        Button button = findViewById(R.id.addAccount);
        Button button1 = findViewById(R.id.returnMain);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        Log.d("USER", "按键配置完成");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(AddAccount.this, MainActivity.class);
        switch (v.getId()) {
            case R.id.addAccount:
                Log.d("USER", "开始添加");
                EditText editText = findViewById(R.id.addAccountMoney);
                String money = editText.getText().toString();
                EditText editText1 = findViewById(R.id.remarkText);
                String remark = editText1.getText().toString();
                Log.d("USER", "开始构造account实例");
                Log.d("USER", "USER==" + User.LoginName);
//                Log.d("USER", Account.InOrOutTypeToString(uploadType));
                Account account2= LitePal.findLast(Account.class);
                long ID;
                if (account2==null) ID=0;
                else ID=account2.getID();
                Calendar calendar=new GregorianCalendar();
                calendar.clear();
                calendar.set(year, month, dayOfMonth, hour, minute);
                Log.d("USER",String.valueOf(ID));
                Account account = new Account(
                        User.LoginName,
                        uploadType,
                        Double.parseDouble(money),
                        remark,
                        year, month, dayOfMonth, hour, minute,
                        ID,
                        calendar.getTimeInMillis()/1000);
//                Log.d("USER", account.getType());
                account.save();

                Log.d("USER", "添加成功");
                startActivity(intent);
                finish();
                break;
            case R.id.returnMain:
                startActivity(intent);
                finish();
                break;
        }
    }
}