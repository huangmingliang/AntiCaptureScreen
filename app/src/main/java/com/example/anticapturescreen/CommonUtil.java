package com.example.anticapturescreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Huangmingliang on 2016/5/31 0031.
 */
public class CommonUtil {
    private String TAG=getClass().getSimpleName();
    private Context mContext;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private final String SP_NAME="systemAppFilter";
    private final String FILTER_STATUS="isFilter";

    public CommonUtil(Context context){
        mContext=context;
        sp=context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        editor=sp.edit();
    }

    public List<AppInfo> getInstalledPackNames(Context context,boolean isContainSystemApp){
        List<AppInfo> appInfoList=new ArrayList<>();
        List<String> packNames=new ArrayList<>();
        List<PackageInfo> packageInfoList=new ArrayList<>();
        if (context==null){
            return appInfoList;
        }
        DbUtil dbUtil =new DbUtil(context,Constant.DB_NAME);
        packNames= dbUtil.getAntiCapturePackNames();
        packageInfoList=context.getPackageManager().getInstalledPackages(0);
        for (PackageInfo packageInfo:packageInfoList){
            if (!isContainSystemApp&&(packageInfo.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)!=0){      //过滤系统应用
                continue;
            }
            AppInfo appInfo=new AppInfo();
            appInfo.setPackName(packageInfo.packageName);
            appInfo.setAppName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
            appInfo.setPackIcon(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));
            if (packNames.contains(packageInfo.packageName)){
                appInfo.setOpen(true);
                appInfo.setSortChar("a");                //设置应用排序字符串，使已开启防截屏的应用显示在列表前面
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

    public void saveSystemAppFilterStatus(boolean isContainSystemApp){
        editor.clear();
        editor.putBoolean(FILTER_STATUS,isContainSystemApp);
        editor.commit();
    }

    public boolean isContainSystemApp(){
        boolean isContain;
        isContain=sp.getBoolean(FILTER_STATUS,false);
        return  isContain;
    }
}
