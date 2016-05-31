package com.example.anticapturescreen;

import android.os.Environment;

import java.io.File;

/**
 * Created by Huangmingliang on 2016/5/30 0030.
 */
public class Constant {
    public static final String SD_PATH= Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String DB_DIR=SD_PATH+ File.separator+"/AntiCapture";
    public static final String DB_NAME="AntiCaptureScreen.db";
    public static final int DB_VERSION=1;
    public static final String TABLE_NAME="AntiCapturePackage";
    public static final String ID="_id";
    public static final String Pack_NAME="_packName";
}
