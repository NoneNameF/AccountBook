package com.example.accountbook;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    private List<Account> accountList = null;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView money;           //金额
        TextView calendar;      //日期
        TextView type;               //账单类型

        public ViewHolder(View view) {
            super(view);
            money = view.findViewById(R.id.MoneyCount);
            calendar = view.findViewById(R.id.date);
            type = view.findViewById(R.id.type);
        }
    }

    public AccountAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accountitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.money.setText((CharSequence) String.valueOf(account.getMoney()));
        holder.type.setText((CharSequence) String.valueOf(account.getType()));
        Calendar calendar=account.getCalendar();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str=df.format(calendar.getTime());
        holder.calendar.setText((CharSequence) str);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }
}
