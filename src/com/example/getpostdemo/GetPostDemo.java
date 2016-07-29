package com.example.getpostdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/*
 * 通常创建一个URL连接，并发送请求、读取此URL引用的资源需要如下几个步骤：
 * 1、通过调用URL对象openConnection()方法来创建URLConnection对象。
 * 2、设置URLConnection的参数和普通请求属性。
 * 3、如果只是发送GET方式请求，使用connect方法建立和远程资源之间的实际连接即可；
 *   如果只是发送POST方式的请求，需要获取URLConnection实例对应的输出流来发送请求参数。
 * 4、远程资源变为可用，程序可以访问远程资源的头字段，或通过输入流读取远程资源的数据。
 */

public class GetPostDemo extends Activity {
	
	private Button get, post;
	private TextView show;
	String response; //代表服务器响应的字符串
	
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) 
		{
			if (msg.what == 0x123)
			{
				show.setText(response); //设置show组件显示服务器响应
			}
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        get = (Button)findViewById(R.id.get);
        post = (Button)findViewById(R.id.post);
        show = (TextView)findViewById(R.id.show);
        
        get.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				new Thread()
				{
					@Override
					public void run() {
						response = GetPostUtil.sendGet("http://192.168.1.88:8888/abc/a.jsp", null);
						handler.sendEmptyMessage(0x123); //发送消息通知UI线程更新UI组件
					}
					
				}.start();
			}
		});
        
        post.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread()
				{
					@Override
					public void run() 
					{
						response = GetPostUtil.sendPost("http://192.168.1.88:8888/abc/login.jsp", "name=winson & pass=win");
					}
					
				}.start();
				
				handler.sendEmptyMessage(0x123); //发送消息通知UI线程更新UI组件
			}
		});
        
    }
}
