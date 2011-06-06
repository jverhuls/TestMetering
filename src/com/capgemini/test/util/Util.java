package com.capgemini.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Util {

	public static boolean isIpadUserAgent(String userAgent) {
		return userAgent.indexOf("iPad") != -1;
	}

	public static String encrypt(String s) {
		byte[] defaultBytes = s.getBytes();
		try{
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();
		            
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
			return hexString.toString();
		}catch(NoSuchAlgorithmException nsae){
			nsae.printStackTrace();   
		}
		return null;
		
	}

	public static Object getBean(ServletContext context, String beanName) {
		WebApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(context);
		return appContext.getBean(beanName);
	}
	
	public static String getOverviewFileName(String userId,String path, boolean isIpad){
		return path + "\\" + userId + "_overview" + (isIpad ? "_ipad" : "_iphone") + ".png";	
	}
	
	public static String getOverviewCacheFileName(String userId,String path, boolean isIpad){
		return path + "\\" + userId + "_overview" + (isIpad ? "_ipad" : "_iphone") + "_cached.png";	
	}

	public static String generateImage(String filename, boolean isIpad) {
		String strFilePath = filename;
		String strCachedFilePath = filename.replaceAll(".png", "_cached.png");
		
		Properties props = new Properties();
		try {
			props.load(Util.class.getClassLoader().getResourceAsStream("config.properties"));
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		DefaultHttpClient httpclient = new DefaultHttpClient();
		if (Boolean.valueOf(props.getProperty("proxy"))) {
			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope(props.getProperty("proxy.server"),
							Integer.parseInt(props.getProperty("proxy.port"))),
					new UsernamePasswordCredentials(props
							.getProperty("proxy.username"), props
							.getProperty("proxy.password")));
			HttpHost proxy = new HttpHost(props.getProperty("proxy.server"), Integer.parseInt(props.getProperty("proxy.port")));

			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					proxy);

		}
		HttpHost targetHost = new HttpHost("chart.apis.google.com", 80, "http");
		int x = 250;
		int y = 300;
		if (isIpad) {
			x = 500;
			y = 500;
		}
		HttpGet httpget = new HttpGet(
				"/chart?chf=bg,s,65432100&chg=10,10,1,5&chxt=x,y,x&chxl=0:%7CJan%7CJuly%7CJan%7CJuly%7C1:%7C0%7C1,000%7C2,000%7C3,000%7C4,000%7C5,000%7C6,000%7C7,000%7C2:%7C2010%7C%7C2011%7C&cht=lxy&chs="
						+ x
						+ "x"
						+ y
						+ "&chd=t:0,20,40%7C10,20,30,40,50,60,70,80%7C-1%7C5,10,20,30,80&chco=00FF00,FF0000&chtt=Grafiek%20Electriciteit&chdl=maandverbruik%7CReferentie&chdlp=bv");

		try {

			ResponseHandler<byte[]> handler = new ResponseHandler<byte[]>() {
				public byte[] handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						return EntityUtils.toByteArray(entity);
					} else {
						return null;
					}
				}
			};

			byte[] response = httpclient.execute(targetHost, httpget, handler);

			

			try {
				File f = new File(strFilePath.substring(0, strFilePath.lastIndexOf("\\")));
				f.mkdirs();
				FileOutputStream fos = new FileOutputStream(strFilePath);
				fos.write(response);
				fos.close();
				
				copyFile(strFilePath, strCachedFilePath);

			} catch (FileNotFoundException ex) {
				System.out.println("FileNotFoundException : " + ex);
			} catch (IOException ioe) {
				System.out.println("IOException : " + ioe);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		httpclient.getConnectionManager().shutdown();
		return strFilePath;

	}

	public static void copyFile(String inStr, String outStr) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try{
		File f1 = new File(inStr);
	      File f2 = new File(outStr);
	      f2.createNewFile();
	      in = new FileInputStream(f1);
	      out = new FileOutputStream(f2);

	      byte[] buf = new byte[1024];
	      int len;
	      while ((len = in.read(buf)) > 0){
	        out.write(buf, 0, len);
	      }
	      in.close();
	      out.close();
	      System.out.println("File copied.");
	    }
	    catch(FileNotFoundException ex){
	      System.out.println(ex.getMessage() + " in the specified directory.");
	    }
	    catch(IOException e){
	      System.out.println(e.getMessage());      
	    }finally{
	    	if(in != null){
	    		in.close();
	    	}
	    	if(out != null){
	    		out.close();
	    	}
	    }
	}
	
	public static boolean exists(String fileName){
		return new File(fileName).exists();
	}
}
