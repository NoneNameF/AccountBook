package com.example.accountbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;
import java.util.GregorianCalendar;

import top.defaults.view.DateTimePickerView;

public class ChooseDate extends AppCompatActivity implements View.OnClickListener {
    private long begin;
    private long done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);
        ActivityCollector.addActivity(this);
        MaterialButton materialButton = findViewById(R.id.find);
        MaterialButton materialButton1 = findViewById(R.id.choosereturnmain);

        materialButton.setOnClickListener(this);
        materialButton1.setOnClickListener(this);
        //配置DateTimePickerView
        DateTimePickerView dateTimePickerView = findViewById(R.id.ChooseDataBegin);
        dateTimePickerView.setStartDate(Calendar.getInstance());
        dateTimePickerView.setSelectedDate(new GregorianCalendar(2021, 0, 1, 0, 0));
        dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
            public void onSelectedDateChanged(Calendar date) {
                begin = date.getTimeInMillis()/1000;
            }
        });
        DateTimePickerView dateTimePickerView1 = findViewById(R.id.ChooseDataDone);
        dateTimePickerView1.setStartDate(Calendar.getInstance());
        dateTimePickerView1.setSelectedDate(new GregorianCalendar(2021, 0, 1, 0, 0));
        dateTimePickerView1.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
            public void onSelectedDateChanged(Calendar date) {
                done = date.getTimeInMillis()/1000;
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.find:
                intent = new Intent(ChooseDate.this, ShowChoose.class);
                intent.putExtra("begin", begin);
                intent.putExtra("done", done);
                startActivity(intent);
                break;
            case R.id.choosereturnmain:
                intent = new Intent(ChooseDate.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}