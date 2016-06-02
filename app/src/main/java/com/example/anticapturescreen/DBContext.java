package com.example.anticapturescreen;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.IOException;

/**
 * Created by Huangmingliang on 2016/6/1 0001.
 * 自定义SQLiteOpenHelper创建数据库的路路径
 */
public class DBContext extends ContextWrapper {
    private String TAG = getClass().getSimpleName();
    private String dbAsbDir;
    private String dbName;

    public DBContext(Context base) {
        super(base);
    }

    public DBContext(Context context, String dbAsbDir, String dbName) {
        this(context);
        this.dbAsbDir = dbAsbDir;
        this.dbName = dbName;

    }

    @Override
    public File getDatabasePath(String name) {
        if (dbAsbDir == null) {
            return super.getDatabasePath(name);
        }
        if (dbName == null) {

        }
        File dir = new File(dbAsbDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String dbAsbPath = dbAsbDir + File.separator + dbName;
        File dbFile = new File(dbAsbPath);
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
                return dbFile;
            } catch (IOException e) {
                e.printStackTrace();
                return super.getDatabasePath(name);
            }
        }
        return dbFile;
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        String dbName = getDatabasePath(name).getAbsolutePath();
        return super.openOrCreateDatabase(dbName, mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        String dbName = getDatabasePath(name).getAbsolutePath();
        return super.openOrCreateDatabase(dbName, mode, factory, errorHandler);
    }
}
