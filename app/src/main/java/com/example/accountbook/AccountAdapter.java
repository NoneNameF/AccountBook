package com.example.accountbook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.LitePal;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    private List<Account> accountList = null;
    private final Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View AccountView;
        TextView money;           //金额
        TextView date;      //日期
        TextView type;               //账单类型
        ImageButton delete;

        public ViewHolder(View view) {
            super(view);
            money = view.findViewById(R.id.MoneyCount);
            date = view.findViewById(R.id.date);
            type = view.findViewById(R.id.type);
            delete = view.findViewById(R.id.deletebutton);
            AccountView = view;
        }
    }

    public AccountAdapter(List<Account> accountList, Context context) {
        this.accountList = accountList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accountitem, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.AccountView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
//                Log.d("USER",String.valueOf(position+1));
                Account account = accountList.get(position);
//得到了点击的具体的实例
//本想通过获取position得到一个id添加进List里然后按删除键删除 但是不知道为什么总是闪退 所以换方法
//添加一个imgbutton 但是这样的话只能一个一个的删除了
//                if (DeleteList.contains(position)){
//Account.DeleteList.add(account);
//                for (int i:Checked){
//                    Log.d("USER",String.valueOf(i));
//                }
//                int i=DeleteList.indexOf(1) ;
//                Log.d("USER",String.valueOf(i));
//                    Toast.makeText(MyContext.getContext(), "click", Toast.LENGTH_SHORT).show();
//                    v.setBackgroundColor(Color.parseColor("#b3b8fc"));
//                    DeleteList.remove(position);
//                }else {
//这里偷懒了  没有去获取里边的卡片式布局的对象 而是把布局前面留白然后设置背景色的方式展现出来不一样的效果
//                    v.setBackgroundColor(Color.parseColor("#ff4040"));
//                    DeleteList.add(position);
//                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context)
                        .setTitle("确定要删除这项吗")
                        .setMessage("此操作无法恢复")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int position = holder.getAdapterPosition();
                                Account account = accountList.get(position);
                                List<Account> account1 = LitePal.where("ID=?", String.valueOf(account.getID())).find(Account.class);
                                if (account1.isEmpty()) {
                                    Toast.makeText(MyContext.getContext(), "出错了 请联系管理员", Toast.LENGTH_SHORT).show();
                                } else {
                                    Account account2 = account1.get(0);
                                    account2.delete();
                                    Toast.makeText(MyContext.getContext(), "请及时刷新查看", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                if (dialog == null) Log.d("USER", "dialog空");
                else dialog.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.money.setText((CharSequence) String.valueOf(account.getMoney()));
        holder.type.setText((CharSequence) String.valueOf(account.getType()));
        String year = String.valueOf(account.getYear());
        String month = String.valueOf(account.getMonth() + 1);
        String day = String.valueOf(account.getDay());
        String hour = String.valueOf(account.getHour());
        String min = String.valueOf(account.getMin());
        String str = year + "年" + month + "月" + day + "日" + hour + "时" + min + "分";
        holder.date.setText((CharSequence) str);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }
}
