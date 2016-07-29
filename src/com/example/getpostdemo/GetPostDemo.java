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
 * ͨ������һ��URL���ӣ����������󡢶�ȡ��URL���õ���Դ��Ҫ���¼������裺
 * 1��ͨ������URL����openConnection()����������URLConnection����
 * 2������URLConnection�Ĳ�������ͨ�������ԡ�
 * 3�����ֻ�Ƿ���GET��ʽ����ʹ��connect����������Զ����Դ֮���ʵ�����Ӽ��ɣ�
 *   ���ֻ�Ƿ���POST��ʽ��������Ҫ��ȡURLConnectionʵ����Ӧ����������������������
 * 4��Զ����Դ��Ϊ���ã�������Է���Զ����Դ��ͷ�ֶΣ���ͨ����������ȡԶ����Դ�����ݡ�
 */

public class GetPostDemo extends Activity {
	
	private Button get, post;
	private TextView show;
	String response; //�����������Ӧ���ַ���
	
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) 
		{
			if (msg.what == 0x123)
			{
				show.setText(response); //����show�����ʾ��������Ӧ
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
						handler.sendEmptyMessage(0x123); //������Ϣ֪ͨUI�̸߳���UI���
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
				
				handler.sendEmptyMessage(0x123); //������Ϣ֪ͨUI�̸߳���UI���
			}
		});
        
    }
}
