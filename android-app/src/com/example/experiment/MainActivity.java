package com.example.experiment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tool.ServiceState;

import com.example.kepler.service.MainService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button controlbutton;
	Button list;
	boolean service_close = false;
	Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
        this.getWindow().setFlags(
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,  
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);//进行全屏   
        setContentView(R.layout.activity_main);
        controlbutton = (Button)findViewById(R.id.endbutton);
//        Intent intent = new Intent(MainActivity.this,Map_Activity.class);
//		startActivity(intent);
        list = (Button)findViewById(R.id.listbutton);
        
        context = getApplicationContext();
        final SharedPreferences sharepreference = getSharedPreferences("exam",0);
        service_close = !ServiceState.serviceisrunning(context);
        if(service_close){        	
        	startService(new Intent(this,com.example.kepler.service.MainService.class));
        	controlbutton.setText("关闭服务");
        }
        controlbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				service_close = !ServiceState.serviceisrunning(context);
 				if(service_close){
				   //buttontext改为关闭服务
				   //重启服务 
					Intent intent=new Intent(MainActivity.this,com.example.kepler.service.MainService.class);
					intent.setAction("com.example.kepler.service.MainService");
					startService(intent);
					controlbutton.setText("关闭服务");
				}else{
				   //buttontext改为开始服务
				   //发布广播关闭服务
					Intent intent=new Intent(MainActivity.this,com.example.kepler.service.MainService.class);
					intent.setAction("com.example.kepler.service.MainService");
					boolean a = stopService(intent);
 					if(!a){
						intent=new Intent(MainActivity.this,com.example.kepler.service.MainService.class);
						intent.setAction("com.example.kepler.service.MainService");
						startService(intent);
						controlbutton.setText("关闭服务");
					}
					else{						
						controlbutton.setText("启动服务");
					}
				}
			}
		});
        
        list.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//启动list activity
				Intent intent = new Intent(MainActivity.this,ListActivity.class);
				startActivity(intent);
			}
		});
        
    }
}
