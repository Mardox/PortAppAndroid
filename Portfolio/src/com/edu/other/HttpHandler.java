package com.edu.other;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.edu.tube.BuildConfig;


import android.util.Log;

 
public class HttpHandler 
{
	static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;
    String url_part = "http://gdata.youtube.com/feeds/api/videos?q=";
    String url_playlist = "https://gdata.youtube.com/feeds/api/playlists/snippets?q=";
    String start_index_pl="&start-index=";
    String maxresult;
    String search;
    String end="&v=2&alt=jsonc";
    
    //https://gdata.youtube.com/feeds/api/playlists/4DAEFAF23BB3CDD0?start-index=1&max-results=2&alt=jsonc&v=2
    
    String url_playlist_item_search="https://gdata.youtube.com/feeds/api/playlists/";
    String end_play_list_search="?alt=jsonc&v=2";
    
    String url_user_pl="https://gdata.youtube.com/feeds/api/users/";
    
    String url_user_pl_end=	"/playlists?v=2&alt=jsonc";
    
    
    
    String url_pl_ID_item="https://gdata.youtube.com/feeds/api/playlists/";
    String start_index="?start-index=";
    String max_Result="&max-results=";
    String end_indx="&alt=jsonc&v=2";
    
    
    
    
    StringBuilder builder = new StringBuilder();
 
    public HttpHandler() 
    {
		// TODO Auto-generated constructor stub
	}
    
    
    //======================= @ API CALL FOR SEARCH PLAYLIST VIDEOS @ ===========//
    public String makeServiceCallforPlayUserList(String user ) 
    {
        try {
        	builder.setLength(0);
            	DefaultHttpClient httpClient = new DefaultHttpClient();
        		HttpEntity httpEntity = null;
        		HttpResponse httpResponse = null;
        		String url=url_user_pl+user+url_user_pl_end;
        		logV(url);
        		HttpGet httpGet = new HttpGet("https://"+url);
        		httpResponse = httpClient.execute(httpGet);
        		StatusLine statusLine = httpResponse.getStatusLine();
        		int statusCode = statusLine.getStatusCode();
        	      if (statusCode == 200)
        	      {
        	         HttpEntity entity = httpResponse.getEntity();
        	         InputStream content = entity.getContent();
        	         BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
        	        String line;
        	        
        	        while ((line = reader.readLine()) != null) 
        	        {
        	          builder.append(line);
        	        }
        	      } else {
        	        logV("Failed to download file");
        	      }
        	    } catch (ClientProtocolException e) {
        	      e.printStackTrace();
        	      logV("readYoutubeFeed exeption1");
        	    } catch (IOException e) {
        	      e.printStackTrace();
        	      logV("readYoutubeFeed exeption2");
        	    }
        	logV(builder.toString());
        	    return builder.toString();
    }
    
    public String makeServiceCallforPlayListItemForIndex(String pl_id,String start_indx,String max_result) 
    {
        try 
        {
        	builder.setLength(0);
            	DefaultHttpClient httpClient = new DefaultHttpClient();
        		HttpEntity httpEntity = null;
        		HttpResponse httpResponse = null;
        		String url=url_pl_ID_item+pl_id+start_index+start_indx+max_Result+max_result+end_indx;
        		logV(url);
        		HttpGet httpGet = new HttpGet(url);
        		httpResponse = httpClient.execute(httpGet);
        		StatusLine statusLine = httpResponse.getStatusLine();
        		int statusCode = statusLine.getStatusCode();
        	    if (statusCode == 200)
        	    {
        	       HttpEntity entity = httpResponse.getEntity();
        	       InputStream content = entity.getContent();
        	       BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
        	       String line;
        	       while ((line = reader.readLine()) != null) 
        	       {
        	          builder.append(line);
        	       }
        	      } 
        	      else 
        	      {
        	        logV("Failed to download file");
        	      }
        	    } 
        	catch (ClientProtocolException e) 
        	{
        	      e.printStackTrace();
        	      logV("readYoutubeFeed exeption1");
        	}
        catch (IOException e) 
        {
              e.printStackTrace();
             logV("readYoutubeFeed exeption2");
        }
        	logV(builder.toString());
        	return builder.toString();
    }
     
  //======================= @ API CALL FOR SEARCH PLAYLIST VIDEOS @ ===========//
    public String makeServiceCallforPlayListItem(String id) 
    {
        try {
        	builder.setLength(0);
            	DefaultHttpClient httpClient = new DefaultHttpClient();
        		HttpEntity httpEntity = null;
        		HttpResponse httpResponse = null;
        		String url=url_playlist_item_search+id+end_play_list_search;
        		logV(url);
        		HttpGet httpGet = new HttpGet(url);
        		httpResponse = httpClient.execute(httpGet);
        		StatusLine statusLine = httpResponse.getStatusLine();
        		int statusCode = statusLine.getStatusCode();
        	      if (statusCode == 200)
        	      {
        	         HttpEntity entity = httpResponse.getEntity();
        	         InputStream content = entity.getContent();
        	         BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
        	        String line;
        	        
        	        while ((line = reader.readLine()) != null) 
        	        {
        	          builder.append(line);
        	        }
        	      } else {
        	        logV("Failed to download file");
        	      }
        	    } catch (ClientProtocolException e) {
        	      e.printStackTrace();
        	      logV("readYoutubeFeed exeption1");
        	    } catch (IOException e) {
        	      e.printStackTrace();
        	      logV("readYoutubeFeed exeption2");
        	    }
        	logV(builder.toString());
        	    return builder.toString();
    }
    
    
    //======================= @ API CALL FOR SHOW PLAYLIST ACCORDING TO KEYWORD @ ===========//
    public String makeServiceCallforPlayList(String searchType, String start_index,String number_of_result) 
    {
        try {
        	builder.setLength(0);
            	DefaultHttpClient httpClient = new DefaultHttpClient();
        		HttpEntity httpEntity = null;
        		HttpResponse httpResponse = null;
        		String url=url_playlist+searchType+start_index_pl+start_index+"&max-results="+number_of_result+end;
        		logV(url);
        		String newUrl = URLEncoder.encode(url, "UTF-8"); 
        		logV(newUrl);
        		HttpGet httpGet = new HttpGet(url);
        		httpResponse = httpClient.execute(httpGet);
        		StatusLine statusLine = httpResponse.getStatusLine();
        		int statusCode = statusLine.getStatusCode();
        	      if (statusCode == 200)
        	      {
        	         HttpEntity entity = httpResponse.getEntity();
        	         InputStream content = entity.getContent();
        	         BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
        	        String line;
        	        
        	        while ((line = reader.readLine()) != null) 
        	        {
        	        logV("0000000000000000000000000"+reader.readLine());	
        	          builder.append(line);
        	        }
        	      } else {
        	        logV("Failed to download file");
        	      }
        	    } catch (ClientProtocolException e) {
        	      e.printStackTrace();
        	      logV("readYoutubeFeed exeption1");
        	    } catch (IOException e) {
        	      e.printStackTrace();
        	      logV("readYoutubeFeed exeption2");
        	    }
        	logV(builder.toString());
        	    return builder.toString();
    }
    
     
    public String makeServiceCall(String searchType, String number_of_result) 
    {
        try {
            	DefaultHttpClient httpClient = new DefaultHttpClient();
        		HttpEntity httpEntity = null;
        		HttpResponse httpResponse = null;
        		String url=url_part+searchType+"&max-results="+number_of_result+end;
        		logV(url);
        		HttpGet httpGet = new HttpGet(url);
        		httpResponse = httpClient.execute(httpGet);
        		StatusLine statusLine = httpResponse.getStatusLine();
        		int statusCode = statusLine.getStatusCode();
        	      if (statusCode == 200)
        	      {
        	         HttpEntity entity = httpResponse.getEntity();
        	         InputStream content = entity.getContent();
        	         BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
        	        String line;
        	        while ((line = reader.readLine()) != null) 
        	        {
        	          builder.append(line);
        	        }
        	      } else {
        	        logV("Failed to download file");
        	      }
        	    } catch (ClientProtocolException e) {
        	      e.printStackTrace();
        	      logV("readYoutubeFeed exeption1");
        	    } catch (IOException e) {
        	      e.printStackTrace();
        	      logV("readYoutubeFeed exeption2");
        	    }
        	logV(builder.toString());
        	    return builder.toString();
    }
    public void logV(String msg)
    {
    	if(BuildConfig.DEBUG)
    	{
    		Log.v(this.getClass().getName(), msg);
    	}
    }
}
