package com.example.getpostdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

//������

public class GetPostUtil {
	
	/*
	 * ��ָ��URL����GET����������
	 * url ���������URL��
	 * params ����������������Ӧ����name1=value1 & name2=value2����ʽ��
	 * URL����Զ����Դ����Ӧ��
	 */
	public static String sendGet(String url, String params) 
	{
		String result = "";
		BufferedReader in = null;
		try 
		{
			String urlName = url + "?" + params;
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection(); //�򿪺�URL֮�������
			//����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0(compatiable; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.connect(); //����ʵ�ʵ�����
			
			Map<String, List<String>> map = conn.getHeaderFields(); //��ȡ���е���Ӧͷ�ֶ�
			for (String key : map.keySet()) //�������е���Ӧͷ�ֶ�
			{
				System.out.println(key + "---->" + map.get(key));
			}
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream())); //����BufferedReader����������ȡURL����Ӧ
			String line;
			while ((line = in.readLine()) != null) 
			{
				result += "\n" + line;
			}
		} 
		catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		//ʹ��finally�����ر�������
		finally
		{
			try 
			{
				if (in != null)
				{
					in.close();
				}
			} 
			catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		
		return result;
	}
	
	/*
	 * ��ָ��URL����GET����������:
	 * url ���������URL��
	 * params ����������������Ӧ����name1=value1 & name2=value2����ʽ��
	 * URL����Զ����Դ����Ӧ
	 */
	
	public static String sendPost(String url, String params) 
	{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try 
		{
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection(); //�򿪺�URL֮�������
			//����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0(compatiable; MSIE 6.0; Windows NT 5.1; SV1)");
			
			//����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			out = new PrintWriter(conn.getOutputStream()); //��ȡURLConnection�����Ӧ�������
			out.print(params); //�����������
			out.flush(); //flush������Ļ���
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream())); //����BufferedReader����������ȡURL����Ӧ
			String line;
			while ((line = in.readLine()) != null) 
			{
				result += "\n" + line;
			}
			
		} 
		catch (Exception e) {
			System.out.println("����POST��������쳣��" + e);
			e.printStackTrace();
		}
		//ʹ��finally�����ر��������������
		finally
		{
			try 
			{
				if (out != null)
				{
					out.close();
				}
				if (in != null)
				{
					in.close();
				}
			} 
			catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
