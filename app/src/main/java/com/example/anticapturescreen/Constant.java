package com.example.anticapturescreen;

import android.os.Environment;

import java.io.File;

/**
 * Created by Huangmingliang on 2016/5/30 0030.
 */
class Constant {
    static final String SD_PATH= Environment.getExternalStorageDirectory().getAbsolutePath();
    static final String DB_DIR=SD_PATH+ File.separator+"/AntiCapture";
    static final String DB_NAME="AntiCaptureScreen.db";
    static final int DB_VERSION=1;
    static final String TABLE_NAME="AntiCapturePackage";
    static final String ID="_id";
    static final String Pack_NAME="_packName";
}
