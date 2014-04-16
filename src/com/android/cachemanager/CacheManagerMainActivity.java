package com.android.cachemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;

public class CacheManagerMainActivity extends Activity {

	Context mc;
	
	public CacheManagerMainActivity(Context cxt) {
		// TODO Auto-generated constructor stub
		this.mc = cxt;
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cache_manager_main);
			
		
	}
	
	private JSONArray getJSONArray(String url, String filename) {

    	InputStream is = null;
    	String result = "";
    	JSONArray jArray = null;
    	//String FILENAME = "storedata";

    	FileOutputStream fos;
    	FileInputStream fis;
    	Boolean check =fileExistance(filename);
		if(check == true && isNetworkAvailable() == false){
			
			
			try {
				fis = mc.openFileInput(filename);
				   InputStreamReader isr = new InputStreamReader(fis);
				   BufferedReader bufferedReader = new BufferedReader(isr);
				   StringBuilder sb = new StringBuilder();
				   String line;
				   while ((line = bufferedReader.readLine()) != null) {
				       sb.append(line);
				   }
				   result=sb.toString();
				jArray = new JSONArray(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jArray;
		}else if(check == false && isNetworkAvailable()== true ){
			 try{
  	           HttpClient httpclient = new DefaultHttpClient();
  	           HttpPost httppost = new HttpPost(url);
  	           HttpResponse response = httpclient.execute(httppost);
  	           HttpEntity entity = response.getEntity();
  	           is = entity.getContent();

  	   }catch(Exception e){
  	           Log.e("log_tag", "Error in http connection "+e.toString());
  	   }
  	   
  	 //convert response to string
  	   try{
  	           BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
  	           StringBuilder sb = new StringBuilder();
  	           String line = null;
  	           while ((line = reader.readLine()) != null) {
  	                   sb.append(line + "\n");
  	           }
  	           is.close();
  	           result=sb.toString();
  	         working(filename,result);
  	   }catch(Exception e){
  	           Log.e("log_tag", "Error converting result "+e.toString());
  	   }
  	   
  	  
  	   return jArray;
		}
		else if(fileExistance(filename)== true && isNetworkAvailable()== true){
			try{
	  	           HttpClient httpclient = new DefaultHttpClient();
	  	           HttpPost httppost = new HttpPost(url);
	  	           HttpResponse response = httpclient.execute(httppost);
	  	           HttpEntity entity = response.getEntity();
	  	           is = entity.getContent();

	  	   }catch(Exception e){
	  	           Log.e("log_tag", "Error in http connection "+e.toString());
	  	   }
	  	   
	  	 //convert response to string
	  	   try{
	  	           BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	  	           StringBuilder sb = new StringBuilder();
	  	           String line = null;
	  	           while ((line = reader.readLine()) != null) {
	  	                   sb.append(line + "\n");
	  	           }
	  	           is.close();
	  	           result=sb.toString();
	  	         File file = mc.getFileStreamPath(filename);
	  	         file.delete();
	  	         working(filename,result);
	  	   }catch(Exception e){
	  	           Log.e("log_tag", "Error converting result "+e.toString());
	  	   }
	  	   
	  	 
	  	   return jArray;

		}
	
		else{
    	
    	  
    	   return null;
		}  
    	  
    	}
    
    public  boolean fileExistance(String fname){
        File file = mc.getFileStreamPath(fname);
        return file.exists();
    }
    
    public  void working(String name, String data) {
        // try to write the content
        try {
            // open myfilename.txt for writing
            FileOutputStream out = mc.openFileOutput(name,Context.MODE_APPEND);
            // write the contents on mySettings to the file
            out.write(data.getBytes());
            // close the file
            out.close();
        } catch (java.io.IOException e) {
            //do something if an IOException occurs.
            e.printStackTrace();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)mc.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    
    public  JSONObject getJSONObject(String url,String filename){
    	InputStream is = null;
    	String result = "";
    	JSONObject jObject = null;
    	//String FILENAME = "storedata";

    	FileOutputStream fos;
    	FileInputStream fis;
    	Boolean check =fileExistance(filename);
		if(check == true && isNetworkAvailable() == false){
			
			
			try {
				fis = mc.openFileInput(filename);
				   InputStreamReader isr = new InputStreamReader(fis);
				   BufferedReader bufferedReader = new BufferedReader(isr);
				   StringBuilder sb = new StringBuilder();
				   String line;
				   while ((line = bufferedReader.readLine()) != null) {
				       sb.append(line);
				   }
				   result=sb.toString();
				   jObject = new JSONObject(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jObject;
		}else if(check == false && isNetworkAvailable()== true ){
			 try{
  	           HttpClient httpclient = new DefaultHttpClient();
  	           HttpPost httppost = new HttpPost(url);
  	           HttpResponse response = httpclient.execute(httppost);
  	           HttpEntity entity = response.getEntity();
  	           is = entity.getContent();

  	   }catch(Exception e){
  	           Log.e("log_tag", "Error in http connection "+e.toString());
  	   }
  	   
  	 //convert response to string
  	   try{
  	           BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
  	           StringBuilder sb = new StringBuilder();
  	           String line = null;
  	           while ((line = reader.readLine()) != null) {
  	                   sb.append(line + "\n");
  	           }
  	           is.close();
  	           result=sb.toString();
  	         working(filename,result);
  	   }catch(Exception e){
  	           Log.e("log_tag", "Error converting result "+e.toString());
  	   }
  	   
  	   try{
  	   
  		 jObject = new JSONObject(result);            
  	   }catch(JSONException e){
  	           Log.e("log_tag", "Error parsing data "+e.toString());
  	   }
  	   return jObject;
		}
		else if(fileExistance(filename)== true && isNetworkAvailable()== true){
			try{
	  	           HttpClient httpclient = new DefaultHttpClient();
	  	           HttpPost httppost = new HttpPost(url);
	  	           HttpResponse response = httpclient.execute(httppost);
	  	           HttpEntity entity = response.getEntity();
	  	           is = entity.getContent();

	  	   }catch(Exception e){
	  	           Log.e("log_tag", "Error in http connection "+e.toString());
	  	   }
	  	   
	  	 //convert response to string
	  	   try{
	  	           BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	  	           StringBuilder sb = new StringBuilder();
	  	           String line = null;
	  	           while ((line = reader.readLine()) != null) {
	  	                   sb.append(line + "\n");
	  	           }
	  	           is.close();
	  	           result=sb.toString();
	  	         File file = mc.getFileStreamPath(filename);
	  	         file.delete();
	  	         working(filename,result);
	  	   }catch(Exception e){
	  	           Log.e("log_tag", "Error converting result "+e.toString());
	  	   }
	  	   
	  	   try{
	  	   
	  	            jObject = new JSONObject(result);            
	  	   }catch(JSONException e){
	  	           Log.e("log_tag", "Error parsing data "+e.toString());
	  	   }
	  	   return jObject;

		}
	
		else{
    	
    	
    	   return null;
		}  
    	  
    	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cache_manager_main, menu);
		return true;
	}

}
