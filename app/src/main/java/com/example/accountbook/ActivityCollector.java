package com.example.accountbook;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public static List<Activity> activities =new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity:activities){
            activity.finish();
        }
    }
    public static void finishOthers(){
        boolean is=false;
        for (Activity activity:activities){
            if (is){
                activity.finish();
            }else is=true;

        }
    }
}
