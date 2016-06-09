package com.yupeibiao.json;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
	public static void main(String [] args){
		String json=getJsonContent("https://news-at.zhihu.com/api/4/start-image/1080*1776");
		JSONTools.getSimpleMessage("",json);
		JSONTools.getMoreMessage("",getJsonContent("http://news-at.zhihu.com/api/4/themes"));
	}

	public static String  getJsonContent(String path){
		URL url;
		try {
			url = new URL(path);
			HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setReadTimeout(3000);
			httpURLConnection.setDoInput(true);
			//httpURLConnection.connect();
			int respondCode=httpURLConnection.getResponseCode();
			
			if(respondCode==200){
				InputStream is=httpURLConnection.getInputStream();
				String message=getStreamContent(is);
				//System.out.println(message);
				return message;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
		
	}
	/**
	 * 
	 * @param is
	 * @return
	 */
	private static String getStreamContent(InputStream is) {
		// TODO Auto-generated method stub
		String jsonMessage="";
		try{
			
			ByteArrayOutputStream byteArrayInputStream =new ByteArrayOutputStream();
			int len=0;
			byte [] data=new byte[1024];
			while((len=is.read(data))!=-1){
				byteArrayInputStream.write(data, 0, len);
			}
			jsonMessage=new String(byteArrayInputStream.toByteArray());
		}catch(Exception e){
			
		}
		return jsonMessage;
		
	}
}
