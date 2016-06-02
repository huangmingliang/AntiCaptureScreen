package com.example.anticapturescreen;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;

/**
 * Created by Huangmingliang on 2016/5/26 0026.
 */
public class ProviderUtil extends ContentProvider {

    private final String TAG=getClass().getSimpleName();

    private final String AUTHORITY = "com.example.anticapturescreen";
    private final String PATH = "packageName";
    private final int CODE = 0;
    private UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    private final String SD_PATH= Environment.getExternalStorageDirectory().getAbsolutePath();
    private final String DB_DIR=SD_PATH+ File.separator+"/AntiCapture";
    private final String DB_NAME="AntiCapture.db";
    private DbUtil dbUtil;
    private Context mContext;

    @Override
    public boolean onCreate() {
        matcher.addURI(AUTHORITY,PATH,CODE);
        mContext=new DBContext(this.getContext(),Constant.DB_DIR,Constant.DB_NAME);
        dbUtil =new DbUtil(mContext,Constant.DB_NAME);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor=null;
        if (matcher.match(uri)==CODE){
            cursor= dbUtil.getAntiCapturePackNamesCursor();
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)) {
            case CODE:
                Log.e(TAG,"Request set MIME data");
                return "vnd.android.cursor.dir/" + PATH;
            default:
                Log.e(TAG,"Unknown uri");
                throw new IllegalArgumentException("Unknown uri:" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
