package com.example.anticapturescreen;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.IOException;

/**
 * Created by Huangmingliang on 2016/6/1 0001.
 */
public class DataBaseContext extends ContextWrapper{
    private String dbAsbDir;
    private String dbName;
    public DataBaseContext(Context base) {
        super(base);
    }

    public DataBaseContext(Context context,String dbAsbDir,String dbName){
        this(context);
        this.dbAsbDir=dbAsbDir;
        this.dbName=dbName;

    }

    @Override
    public File getDatabasePath(String name) {
        if (dbAsbDir==null){
            return super.getDatabasePath(name);
        }
        if (dbName==null){
            return super.getDatabasePath(name);
        }
        File dir=new File(dbAsbDir);
        if (!dir.exists()){
            dir.mkdirs();
        }
        String dbAsbPath=dbAsbDir+File.separator+dbName;
        File dbFile=new File(dbAsbPath);
        if (!dbFile.exists()){
            try {
                dbFile.createNewFile();
                return dbFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.getDatabasePath(name);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        String dbName=getDatabasePath(name).getAbsolutePath();
        return super.openOrCreateDatabase(dbName, mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        String dbName=getDatabasePath(name).getAbsolutePath();
        return super.openOrCreateDatabase(dbName, mode, factory, errorHandler);
    }
}
