package com.example.accountbook;

import android.annotation.SuppressLint;
import android.content.Context;

public class MyContext {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public MyContext(Context context) {
        MyContext.context = context;
    }

    public static Context getContext() {
        return context;
    }
}
