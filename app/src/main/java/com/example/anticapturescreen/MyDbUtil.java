package com.example.anticapturescreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huangmingliang on 2016/5/30 0030.
 */
public class MyDbUtil {

    private String TAG = getClass().getSimpleName();
    private Context context;
    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    MyDbUtil(Context context) {
        this.context = context;
        mySQLiteHelper = new MySQLiteHelper(context, Constant.DB_NAME, null, Constant.DB_VERSION);
        db = mySQLiteHelper.getReadableDatabase();
    }

    public Cursor getAntiCapturePackNames() {
        if (db == null || !db.isOpen()) {
            db = mySQLiteHelper.getReadableDatabase();
        }
        cursor = db.query(Constant.TABLE_NAME, new String[]{Constant.Pack_NAME}, null, null, null, null, null);
        return cursor;
    }

    public long addAntiCapturePackNames(String packName) {
        long index = -1;
        if (db == null || !db.isOpen()) {
            db = mySQLiteHelper.getReadableDatabase();
        }
        if (!isDataAlreadyInTable(packName)) {
            ContentValues values = new ContentValues();
            values.put(Constant.Pack_NAME, packName);
            index = db.insert(Constant.TABLE_NAME, null, values);
        }else{
            index=0;
        }
        return index;
    }

    public int removeAntiCapturePackNames(String packName) {
        int index = -1;
        if (db == null || !db.isOpen()) {
            db = mySQLiteHelper.getReadableDatabase();
        }
        if (isDataAlreadyInTable(packName)) {
            index = db.delete(Constant.TABLE_NAME, Constant.Pack_NAME + "=?", new String[]{packName});
        }
        return index;
    }

    public void release() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public List<String> getPackNamesFromTable() {
        List<String> packNames = new ArrayList<>();
        String packName = null;
        getAntiCapturePackNames();
        if (cursor == null) {
            Log.e(TAG, "cursor is null");
            packNames.clear();
            return packNames;
        }
        if (cursor.moveToFirst()) {
            packName = cursor.getString(cursor.getColumnIndex(Constant.Pack_NAME));
            if (packName != null) {
                packNames.add(packName);
            }
        }
        while (cursor.moveToNext()) {
            packName = cursor.getString(cursor.getColumnIndex(Constant.Pack_NAME));
            if (packName != null) {
                packNames.add(packName);
            }
        }
        return packNames;
    }

    private boolean isDataAlreadyInTable(String packName) {
        List<String> packNames = new ArrayList<>();
        packNames = getPackNamesFromTable();
        if (packName != null && !packNames.contains(packName)) {
            Log.e(TAG, "packageName " + packName + " not exists in the table " + Constant.TABLE_NAME);
            return false;
        } else {
            Log.e(TAG, "packageName " + packName + " exists in the table " + Constant.TABLE_NAME);
            return true;
        }
    }
}
