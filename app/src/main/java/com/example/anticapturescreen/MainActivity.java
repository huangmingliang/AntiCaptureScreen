package com.example.anticapturescreen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private Context context;
    private DBContext dbContext;
    private CommonUtil commonUtil;
    private ListView appListView;
    private AppListAdapter adapter;
    private List<AppInfo> appInfoList;
    private boolean isContainSystemApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);
        init();
    }

    private void init() {
        context = this;
        dbContext = new DBContext(context, Constant.DB_DIR, Constant.DB_NAME);
        commonUtil = new CommonUtil(dbContext);
        isContainSystemApp = commonUtil.isContainSystemApp();
        appListView = (ListView) findViewById(R.id.appList);
        appInfoList = commonUtil.getInstalledPackNames(dbContext, isContainSystemApp);
        adapter = new AppListAdapter(dbContext, appInfoList);
        appListView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.switch1);
        if (isContainSystemApp) {                                //设置系统应用过滤开关初始图标
            menuItem.setIcon(R.mipmap.off);
        } else {
            menuItem.setIcon(R.mipmap.on);
        }
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                boolean isContainSystemApp = commonUtil.isContainSystemApp();
                if (isContainSystemApp) {                           //每次点击系统应用过滤开关切换图标，并且跟新应用列表
                    menuItem.setIcon(R.mipmap.on);
                } else {
                    menuItem.setIcon(R.mipmap.off);
                }
                isContainSystemApp = !isContainSystemApp;
                appInfoList.clear();
                appInfoList.addAll(commonUtil.getInstalledPackNames(dbContext, isContainSystemApp));
                commonUtil.saveSystemAppFilterStatus(isContainSystemApp);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
