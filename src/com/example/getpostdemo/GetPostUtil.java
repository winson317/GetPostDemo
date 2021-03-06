package com.example.getpostdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

//工具类

public class GetPostUtil {
	
	/*
	 * 向指定URL发送GET方法的请求：
	 * url 发送请求的URL；
	 * params 请求参数，请求参数应该是name1=value1 & name2=value2的形式；
	 * URL代表远程资源的响应。
	 */
	public static String sendGet(String url, String params) 
	{
		String result = "";
		BufferedReader in = null;
		try 
		{
			String urlName = url + "?" + params;
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection(); //打开和URL之间的连接
			//设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0(compatiable; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.connect(); //建立实际的连接
			
			Map<String, List<String>> map = conn.getHeaderFields(); //获取所有的响应头字段
			for (String key : map.keySet()) //遍历所有的响应头字段
			{
				System.out.println(key + "---->" + map.get(key));
			}
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream())); //定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) 
			{
				result += "\n" + line;
			}
		} 
		catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		//使用finally块来关闭输入流
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
	 * 向指定URL发送GET方法的请求:
	 * url 发送请求的URL；
	 * params 请求参数，请求参数应该是name1=value1 & name2=value2的形式；
	 * URL代表远程资源的响应
	 */
	
	public static String sendPost(String url, String params) 
	{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try 
		{
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection(); //打开和URL之间的连接
			//设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0(compatiable; MSIE 6.0; Windows NT 5.1; SV1)");
			
			//发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			out = new PrintWriter(conn.getOutputStream()); //获取URLConnection对象对应的输出流
			out.print(params); //发送请求参数
			out.flush(); //flush输出流的缓冲
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream())); //定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) 
			{
				result += "\n" + line;
			}
			
		} 
		catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
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
