package com.example.anticapturescreen;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Huangmingliang on 2016/5/31 0031.
 */
public class Util {
    private String TAG=getClass().getSimpleName();
    private Context mContext;

    public Util(Context context){
        mContext=context;
    }

    public List<AppInfo> getInstalledPackNames(Context context,boolean isContainSystemApp){
        MyDbUtil myDbUtil=new MyDbUtil(context,Constant.DB_NAME);
        List<AppInfo> appInfoList=new ArrayList<>();
        List<String> packNames=new ArrayList<>();
        packNames=myDbUtil.getAntiCapturePackNames();
        if (context==null){
            return appInfoList;
        }
        List<PackageInfo> packageInfoList=new ArrayList<>();
        packageInfoList=context.getPackageManager().getInstalledPackages(0);
        for (PackageInfo packageInfo:packageInfoList){
            if (!isContainSystemApp&&(packageInfo.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)!=0){
                continue;
            }
            AppInfo appInfo=new AppInfo();
            appInfo.setPackName(packageInfo.packageName);
            appInfo.setAppName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
            appInfo.setPackIcon(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));
            if (packNames.contains(packageInfo.packageName)){
                appInfo.setOpen(true);
                appInfo.setSortChar("a");
            }else{
                appInfo.setOpen(false);
                appInfo.setSortChar("b");
            }
            appInfoList.add(appInfo);
        }
        Collections.sort(appInfoList, new Comparator<AppInfo>() {
            @Override
            public int compare(AppInfo lhs, AppInfo rhs) {
               return lhs.getSortChar().compareTo(rhs.getSortChar());
            }
        });
        return appInfoList;
    }
}
