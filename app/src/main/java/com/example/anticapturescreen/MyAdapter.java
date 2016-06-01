package com.example.anticapturescreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Huangmingliang on 2016/6/1 0001.
 */
public class MyAdapter extends BaseAdapter{

    private String TAG=getClass().getSimpleName();
    Context context;
    List<AppInfo>  appInfoList;
    MyAdapter(Context context, List<AppInfo> appInfoList){
        this.context=context;
        this.appInfoList=appInfoList;
    }
    @Override
    public int getCount() {
        return appInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return appInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        final HolderView holderView;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_applist,null);
            holderView=new HolderView();
            holderView.packName=(TextView) convertView.findViewById(R.id.packName);
            holderView.appName=(TextView)convertView.findViewById(R.id.appName);
            holderView.packIcon=(ImageView)convertView.findViewById(R.id.packIcon);
            holderView.mSwitch=(ImageView)convertView.findViewById(R.id.switch1);
            convertView.setTag(holderView);
        }else {
            holderView=(HolderView) convertView.getTag();
        }
        holderView.packName.setText(appInfoList.get(position).getPackName());
        holderView.appName.setText(appInfoList.get(position).getAppName());
        holderView.packIcon.setImageDrawable(appInfoList.get(position).getPackIcon());
        if (appInfoList.get(position).isOpen()){
            holderView.mSwitch.setImageResource(R.mipmap.on);
        }else {
            holderView.mSwitch.setImageResource(R.mipmap.off);
        }
        holderView.mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbUtil dbUtil=new MyDbUtil(context,Constant.DB_NAME);
                if (appInfoList.get(position).isOpen()){
                    dbUtil.removeAntiCapturePackNames(appInfoList.get(position).getPackName());
                    holderView.mSwitch.setImageResource(R.mipmap.off);
                    appInfoList.get(position).setSortChar("b");
                }else {
                    dbUtil.addAntiCapturePackNames(appInfoList.get(position).getPackName());
                    holderView.mSwitch.setImageResource(R.mipmap.on);
                    appInfoList.get(position).setSortChar("a");
                }
                appInfoList.get(position).setOpen(!appInfoList.get(position).isOpen());
            }
        });
        return convertView;
    }

    class HolderView{
        private TextView packName;
        private TextView appName;
        private ImageView packIcon;
        private ImageView mSwitch;
    }
}
