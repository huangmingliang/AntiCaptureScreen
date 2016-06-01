package com.example.anticapturescreen;

import android.graphics.drawable.Drawable;

/**
 * Created by Huangmingliang on 2016/5/31 0031.
 */
public class AppInfo {
    private String packName;
    private String appName;
    private Drawable packIcon;
    private boolean isOpen;        //是否已开启防截屏
    private String sortChar;       //用于列表数据排序

    public String getSortChar() {
        return sortChar;
    }

    public void setSortChar(String sortChar) {
        this.sortChar = sortChar;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public Drawable getPackIcon() {
        return packIcon;
    }

    public void setPackIcon(Drawable packIcon) {
        this.packIcon = packIcon;
    }
}
