package com.example.accountbook;

import com.google.gson.internal.$Gson$Types;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.Calendar;


public class Account extends LitePalSupport {
    private String belong;          //所属用户
    private InOrOutType type;               //账单类型
    private double money;           //金额
    private String remark;          //备注
    private Calendar calendar;      //日期
    public static enum InOrOutType{
        food,                       //餐饮美食
        clothes,                    //服饰装扮
        general_merchandise,        //日用百货
        household_decoration,       //家居家装
        digital_products,           //数码电器
        sports,                     //运动户外
        beauty_hair,                //美容美发
        family,                     //母婴亲子
        pets,                       //宠物
        traffic,                    //交通出行
        cars,                       //爱车养车
        housing,                    //住房物业
        hotel,                      //酒店旅游
        cultural_leisure,           //文化休闲
        education,                  //教育培训
        health,                     //医疗健康
        live_service,               //生活服务
        public_service,             //公共服务
        business_service,           //商业服务
        donate,                     //公益捐赠
        invest,                     //投资理财
        insurance,                  //保险
        borrow_and_return,          //信用借还
        payment,                    //充值缴费
        income,                     //收入
        red_envelope,               //转账红包
        family_replace_pay,         //亲友代付
        account_access,             //账户存取
        refund,                     //退款
        others;                     //其他
    }

    public InOrOutType getType() {
        return type;
    }

    public void setType(InOrOutType type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Account(String belong, InOrOutType type, double money, String remark, Calendar calendar) {
        this.belong = belong;
        this.type = type;
        this.money = money;
        this.remark = remark;
        this.calendar = calendar;
    }

}
