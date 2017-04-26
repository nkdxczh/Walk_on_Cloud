package org.gr.wocandroid.internet;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class WebAccessUtils {

	// ���ԣ��Զ���һ�������ַ����
	private static final String URI = "http://"+InternetConfig.IP+":"+InternetConfig.PORT+"/"+InternetConfig.PROJECT+"/";
	
	// ����1���Զ���һ����������������Ӧ����
	public static String httpRequest(final String webServiceName){
		String uri = URI + webServiceName;
		System.out.println("URI:>" + uri);
		HttpPost httpPostRequest = new HttpPost(uri);
		try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpPostRequest);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				String data = EntityUtils.toString(httpResponse.getEntity());
				return data;
			}else{
				return "101";
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "102";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "103";
		}
	}
	
	// ����2���Զ���һ��������������Ӧ����
	public static String httpRequest(final String webServiceName, final List<? extends NameValuePair> lstNameValuePairs){
		String uri = URI + webServiceName;
		System.out.println("URI:>" + uri);
		HttpPost httpPostRequest = new HttpPost(uri);
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(lstNameValuePairs, HTTP.UTF_8);
			httpPostRequest.setEntity(entity);
			
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpPostRequest);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				String data = EntityUtils.toString(httpResponse.getEntity());
				return data;
			}else{
				return "101";
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "102";
	}

	public static Bitmap getBitmapFromServer(String imagePath){
		imagePath=URI+imagePath;
		HttpGet get=new HttpGet(imagePath);
		System.out.println(imagePath);
		HttpClient client=new DefaultHttpClient();
		Bitmap pic=null;
		HttpResponse response;
		try {
			response = client.execute(get);
			HttpEntity entity=response.getEntity();
			InputStream is=entity.getContent();
			pic=BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pic;
	}
	
	/*public static void uploadFile(String uploadFile){
		uploadFile="/storage/emulated/0/1.txt";
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDD");
		String end="\r\n";
		String twoHyphens="--";
		String boundary="*****";
		try {
			URL url=new URL(URI+"");
			HttpURLConnection con=(HttpURLConnection)url.openConnection();
			
			
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
			
			
			DataOutputStream ds=new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens+boundary+end);
			ds.writeBytes("Content-Disposition:form-data;"+"name=\"file1\";filename=\""+generateUnqieName()+"\""+end);
			ds.writeBytes(end);
			
			
			FileInputStream fstream=new FileInputStream(uploadFile);
			int bufferSize=1024;
			byte[] buffer=new byte[bufferSize];
			int length=-1;
			while((length=fstream.read(buffer))!=-1){
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			ds.writeBytes(twoHyphens+boundary+end);
			fstream.close();
			ds.flush();
			InputStream is=con.getInputStream();
			int ch;
			StringBuffer b=new StringBuffer();
			while((ch=is.read())!=-1){
				b.append(ch);
				System.out.println(b);
			}
			ds.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

	private synchronized static String generateUnqieName() {
		return String.valueOf(System.nanoTime())+".txt";
	}
}
