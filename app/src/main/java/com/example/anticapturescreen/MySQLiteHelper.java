package com.example.anticapturescreen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Huangmingliang on 2016/5/27 0027.
 */
    class MySQLiteHelper extends SQLiteOpenHelper{

    private final String TABLE_NAME="AntiCapturePackage";
    private final String ID="_id";
    private final String Pack_NAME="_packName";
    private String sql_create_table=
            "create table if not exists " +TABLE_NAME+
            "("+ID+" integer primary key autoincrement," +Pack_NAME+" varchar(50))";

    MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql_create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
