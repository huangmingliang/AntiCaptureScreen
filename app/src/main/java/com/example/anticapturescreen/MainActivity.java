package com.example.anticapturescreen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private Context context;
    private DataBaseContext dataBaseContext;
    private Util util;
    private ListView appListView;
    private MyAdapter adapter;
    private List<AppInfo> appInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);
        context=this;
        dataBaseContext=new DataBaseContext(context,Constant.DB_DIR,Constant.DB_NAME);
        util=new Util(dataBaseContext);
        appListView=(ListView)findViewById(R.id.appList);
        appInfoList=util.getInstalledPackNames(dataBaseContext,false);
        adapter=new MyAdapter(dataBaseContext,appInfoList);
        appListView.setAdapter(adapter);
    }


}
