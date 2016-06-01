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
    private Util util;
    private ListView appListView;
    private MyAdapter adapter;
    private List<AppInfo> appInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);
        context=this;
        util=new Util(context);
        appListView=(ListView)findViewById(R.id.appList);
        appInfoList=util.getInstalledPackNames(context,false);
        adapter=new MyAdapter(context,appInfoList);
        appListView.setAdapter(adapter);
    }


}
