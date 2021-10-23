package com.example.accountbook;

import org.litepal.crud.LitePalSupport;

import java.util.List;

public class Account extends LitePalSupport {
    private final String belong;          //所属用户
    private String type;       //账单类型
    private double money;           //金额
    private String remark;          //备注
    private int year;      //日期
    private int month;
    private int day;
    private int hour;
    private int min;
private long ID;
private long sec;
    public static enum  InOrOutType {
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
        others                      //其他
    }

    public Account(String belong, InOrOutType type, double money, String remark, int year, int month, int day, int hour, int min, long ID, long sec) {
        this.belong = belong;
        this.type = InOrOutTypeToString(type);
        this.money = money;
        this.remark = remark;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.ID = ID;
        this.sec = sec;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        
        this.ID = ID;
    }

    public String getType() {
        return this.type;
    }

    public long getSec() {
        return sec;
    }

    public void setSec(long sec) {
        this.sec = sec;
    }

    public void setType(Account.InOrOutType type) {
        this.type = InOrOutTypeToString(type);
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


    public String getBelong() {
        return belong;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public static Account.InOrOutType StringToInOrOutType(String str) {
        Account.InOrOutType uploadType = Account.InOrOutType.food;
        switch (str) {
            case "餐饮美食":
                uploadType = Account.InOrOutType.food;
                break;
            case "服饰装扮":
                uploadType = Account.InOrOutType.clothes;
                break;
            case "日用百货":
                uploadType = Account.InOrOutType.general_merchandise;
                break;
            case "家居家装":
                uploadType = Account.InOrOutType.household_decoration;
                break;
            case "数码电器":
                uploadType = Account.InOrOutType.digital_products;
                break;
            case "运动户外":
                uploadType = Account.InOrOutType.sports;
                break;
            case "美容美发":
                uploadType = Account.InOrOutType.beauty_hair;
                break;
            case "母婴亲子":
                uploadType = Account.InOrOutType.family;
                break;
            case "宠物":
                uploadType = Account.InOrOutType.pets;
                break;
            case "交通出行":
                uploadType = Account.InOrOutType.traffic;
                break;
            case "爱车养车":
                uploadType = Account.InOrOutType.cars;
                break;
            case "住房物业":
                uploadType = Account.InOrOutType.housing;
                break;
            case "酒店旅游":
                uploadType = Account.InOrOutType.hotel;
                break;
            case "文化休闲":
                uploadType = Account.InOrOutType.cultural_leisure;
                break;
            case "教育培训":
                uploadType = Account.InOrOutType.education;
                break;
            case "医疗健康":
                uploadType = Account.InOrOutType.health;
                break;
            case "生活服务":
                uploadType = Account.InOrOutType.live_service;
                break;
            case "公共服务":
                uploadType = Account.InOrOutType.public_service;
                break;
            case "商业服务":
                uploadType = Account.InOrOutType.business_service;
                break;
            case "公益捐赠":
                uploadType = Account.InOrOutType.donate;
                break;
            case "投资理财":
                uploadType = Account.InOrOutType.invest;
                break;
            case "保险":
                uploadType = Account.InOrOutType.insurance;
                break;
            case "信用借还":
                uploadType = Account.InOrOutType.borrow_and_return;
                break;
            case "充值缴费":
                uploadType = Account.InOrOutType.payment;
                break;
            case "收入":
                uploadType = Account.InOrOutType.income;
                break;
            case "转账红包":
                uploadType = Account.InOrOutType.red_envelope;
                break;
            case "亲友代付":
                uploadType = Account.InOrOutType.family_replace_pay;
                break;
            case "账户存取":
                uploadType = Account.InOrOutType.account_access;
                break;
            case "退款":
                uploadType = Account.InOrOutType.refund;
                break;
            case "其他":
                uploadType = Account.InOrOutType.others;
                break;
        }
        return uploadType;
    }

    public static String InOrOutTypeToString(Account.InOrOutType inOrOutType) {
        String str = "餐饮美食";
        switch (inOrOutType) {
            case food:
                str = "餐饮美食";
                break;
            case clothes:
                str = "服饰装扮";
                break;
            case general_merchandise:
                str = "日用百货";
                break;
            case household_decoration:
                str = "家居家装";
                break;
            case digital_products:
                str = "数码电器";
                break;
            case sports:
                str = "运动户外";
                break;
            case beauty_hair:
                str = "美容美发";
                break;
            case family:
                str = "母婴亲子";
                break;
            case pets:
                str = "宠物";
                break;
            case traffic:
                str = "交通出行";
                break;
            case cars:
                str = "爱车养车";
                break;
            case housing:
                str = "住房物业";
                break;
            case hotel:
                str = "酒店旅游";
                break;
            case cultural_leisure:
                str = "文化休闲";
                break;
            case education:
                str = "教育培训";
                break;
            case health:
                str = "医疗健康";
                break;
            case live_service:
                str = "生活服务";
                break;
            case public_service:
                str = "公共服务";
                break;
            case business_service:
                str = "商业服务";
                break;
            case donate:
                str = "公益捐赠";
                break;
            case invest:
                str = "投资理财";
                break;
            case insurance:
                str = "保险";
                break;
            case borrow_and_return:
                str = "信用借还";
                break;
            case payment:
                str = "充值缴费";
                break;
            case income:
                str = "收入";
                break;
            case red_envelope:
                str = "转账红包";
                break;
            case family_replace_pay:
                str = "亲友代付";
                break;
            case account_access:
                str = "账户存取";
                break;
            case refund:
                str = "退款";
                break;
            case others:
                str = "其他";
                break;
        }
        return str;
    }

}
