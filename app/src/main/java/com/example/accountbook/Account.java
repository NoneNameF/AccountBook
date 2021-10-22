package com.example.accountbook;

import org.litepal.crud.LitePalSupport;

import java.util.Calendar;
import java.util.GregorianCalendar;


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

    public enum InOrOutType {
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

    public InOrOutType getType() {
        return StringToInOrOutType(this.type);
    }

    public void setType(InOrOutType type) {
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

    public Calendar getCalendar() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(this.year, this.month - 1, this.day, this.hour, this.min);
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH)+1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.min = calendar.get(Calendar.MINUTE);
    }

    public String getBelong() {
        return belong;
    }

    public Account(String belong, InOrOutType type, double money, String remark, Calendar calendar) {
        this.belong = belong;
        this.type = InOrOutTypeToString(type);
        this.money = money;
        this.remark = remark;
        setCalendar(calendar);
    }

    public static InOrOutType StringToInOrOutType(String str) {
        InOrOutType uploadType = InOrOutType.food;
        switch (str) {
            case "餐饮美食":
                uploadType = InOrOutType.food;
                break;
            case "服饰装扮":
                uploadType = InOrOutType.clothes;
                break;
            case "日用百货":
                uploadType = InOrOutType.general_merchandise;
                break;
            case "家居家装":
                uploadType = InOrOutType.household_decoration;
                break;
            case "数码电器":
                uploadType = InOrOutType.digital_products;
                break;
            case "运动户外":
                uploadType = InOrOutType.sports;
                break;
            case "美容美发":
                uploadType = InOrOutType.beauty_hair;
                break;
            case "母婴亲子":
                uploadType = InOrOutType.family;
                break;
            case "宠物":
                uploadType = InOrOutType.pets;
                break;
            case "交通出行":
                uploadType = InOrOutType.traffic;
                break;
            case "爱车养车":
                uploadType = InOrOutType.cars;
                break;
            case "住房物业":
                uploadType = InOrOutType.housing;
                break;
            case "酒店旅游":
                uploadType = InOrOutType.hotel;
                break;
            case "文化休闲":
                uploadType = InOrOutType.cultural_leisure;
                break;
            case "教育培训":
                uploadType = InOrOutType.education;
                break;
            case "医疗健康":
                uploadType = InOrOutType.health;
                break;
            case "生活服务":
                uploadType = InOrOutType.live_service;
                break;
            case "公共服务":
                uploadType = InOrOutType.public_service;
                break;
            case "商业服务":
                uploadType = InOrOutType.business_service;
                break;
            case "公益捐赠":
                uploadType = InOrOutType.donate;
                break;
            case "投资理财":
                uploadType = InOrOutType.invest;
                break;
            case "保险":
                uploadType = InOrOutType.insurance;
                break;
            case "信用借还":
                uploadType = InOrOutType.borrow_and_return;
                break;
            case "充值缴费":
                uploadType = InOrOutType.payment;
                break;
            case "收入":
                uploadType = InOrOutType.income;
                break;
            case "转账红包":
                uploadType = InOrOutType.red_envelope;
                break;
            case "亲友代付":
                uploadType = InOrOutType.family_replace_pay;
                break;
            case "账户存取":
                uploadType = InOrOutType.account_access;
                break;
            case "退款":
                uploadType = InOrOutType.refund;
                break;
            case "其他":
                uploadType = InOrOutType.others;
                break;
        }
        return uploadType;
    }

    public static String InOrOutTypeToString(InOrOutType inOrOutType) {
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
