package com.example.anticapturescreen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = getClass().getSimpleName();
    private Context context;
    private Button btn_add,btn_remove;
    private TextView input_packName;
//    private EditText input_packName;
    private MyDbUtil myDbUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        btn_add=(Button)findViewById(R.id.button);
        btn_remove=(Button)findViewById(R.id.button2);
        input_packName=(TextView)findViewById(R.id.textView);
        input_packName.setOnClickListener(this);
//        input_packName=(EditText)findViewById(R.id.editText);
        btn_remove.setOnClickListener(this);
        btn_add.setOnClickListener(this);
//        input_packName.setOnClickListener(this);
        myDbUtil=new MyDbUtil(context);
    }

    @Override
    public void onClick(View v) {
//        String packName=input_packName.getText().toString().trim();
        String packName=input_packName.getText().toString().trim();
        if (packName==null){
            return;
        }
        switch (v.getId()){
            case R.id.button:
                long index1=myDbUtil.addAntiCapturePackNames(packName);
                if (index1<0){
                    Toast.makeText(context,packName+"添加失败",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,packName+"已添加",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button2:
                long index2=myDbUtil.removeAntiCapturePackNames(packName);
                if (index2<0){
                    Toast.makeText(context,packName+"移除失败",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,packName+"已移除",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
        input_packName.setText("");
    }
}
