package com.edu.service;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import com.edu.been.FeedDataBeens;
import com.edu.been.PlaylistItems;
import com.edu.database.PlaylistDB;
import com.edu.other.HttpHandler;
import com.edu.other.Singleton;
import com.edu.tube.BuildConfig;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;

public class VideoListService extends Service 
{


	 IBinder mBinder = new Mbinder();
	private HttpHandler handler;
	private PlaylistDB db;
	private ArrayList<FeedDataBeens> beensList=new ArrayList<FeedDataBeens>();
	private Timer timer=new Timer();
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) 
	{
		// TODO Auto-generated method stub
		logD("onStartCommand");
		
		//return super.onStartCommand(intent, flags, startId);
		
		return Service.START_STICKY;
	}
	@Override
	public void onCreate() 
	{
		// TODO Auto-generated method stub
		super.onCreate();
		logD("onCreate");
		db=new PlaylistDB(this);
		handler=new HttpHandler();
		timer.schedule(new TimerTask() 
		{
			@Override
			public void run() 
			{
				// TODO Auto-generated method stub
				new VideoInsertTask();
			}
		}, 1000);
	}
	
	@Override
	public IBinder onBind(Intent intent) 
	{
		// TODO Auto-generated method stub
		return mBinder;
	}
	@Override
	public void onDestroy() 
	{
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	public class Mbinder extends Binder
	{
		public VideoListService getServiceInstance()
		{
			return VideoListService.this;
		}
	}
	class VideoInsertTask extends AsyncTask<Void, Void, Void>
	{
		int curret_postion=0;
		int size;
		
		@Override
		protected void onPreExecute() 
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			
		}

		@Override
		protected Void doInBackground(Void... params) 
		{
			// TODO Auto-generated method stub
			beensList=db.getLocalPlaylists();
			size=beensList.size();
			for(int i=0;i<beensList.size();i++)
			{
				logD(size+"");
				logD(""+i);
				String pl_id=beensList.get(i).getID();
				getPlayListItem(pl_id);
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			timer.schedule(new TimerTask() 
			{
				@Override
				public void run() 
				{
					// TODO Auto-generated method stub
					new VideoInsertTask().execute();
				}
			},2000);
		}
		
	}
	
	
	public void  getPlayListItem(String ids)
	{
		/*ArrayList<PlaylistItems> pl_Items = new ArrayList<PlaylistItems>();*/
		String jesonData = handler.makeServiceCallforPlayListItem(ids);
    	logD(ids);
    	logD(jesonData);
    	String pl_id="123";
    	String vid_id="123";
    	String thumb="123";
    	String vid_url="123";
    	String video_name = "123";
    	String playlist_name="123";
    	String channel_name="123";
    	try 
    	{       
    	    JSONObject jObj = new JSONObject(jesonData); 
    	    JSONObject jobjPL=jObj.getJSONObject("data");
    	    pl_id = jobjPL.getString("id");
    	    logD("id is " +pl_id);
    	    channel_name=jobjPL.getString("author");
    	    logD("Channel name is "+channel_name);
    	    playlist_name=jobjPL.getString("title");
    	    logD("Playlist name is "+playlist_name);
    	    
    	    if(jObj.getJSONObject("data").has("items"))
    	    {
    	    	logD("Response has Items in json");
    	    	JSONArray ja = jObj.getJSONObject("data").getJSONArray("items");
    	    	
    	    	if(ja.length()<10)
    	    	{
    	    		Singleton.hasMOre=false;
    	    	}
    	    	else
    	    	{
    	    		Singleton.hasMOre=true;
    	    	}
    	    	 for(int i=0;i<ja.length();i++)
        	    {
        	    	JSONObject jo = (JSONObject) ja.get(i);
                	JSONObject vidObj=jo.getJSONObject("video");
                	vid_id=vidObj.getString("id");
                	logD("Video ID is "+vid_id);
                	video_name=vidObj.getString("title");
                	logD("Video Name is  "+video_name);
                	
                	if(vidObj.has("thumbnail") && vidObj.has("player"))
                	{
                		logD("==================================== has thumb ");
                		JSONObject thum= vidObj.getJSONObject("thumbnail");
                    	thumb=thum.getString("hqDefault");
                    	logD("Thumb is "+thumb);
                    	JSONObject vidPlayer=vidObj.getJSONObject("player");
                    	vid_url=vidPlayer.getString("default");
                    	//pl_Items.add(new PlaylistItems(pl_id, playlist_name, vid_id, vid_url, video_name, thumb, channel_name));
                    	db.addVideoList(pl_id, playlist_name, vid_id, vid_url, video_name, thumb, channel_name,pl_id,playlist_name,0,1);
                    	
                    	
                    	
                    	
                    	//Singleton.pl_Items.add(new PlaylistItems(pl_id, playlist_name, vid_id, vid_url, video_name, thumb, channel_name));
                    }
                	else
                	{
                		logD("==================================== has no thumb ");
                	}
                }
    	    }
    	    else
    	    {
    	    	logD("+++++++++++++++++++++++++++ No More Videos");
    	    	Singleton.hasMOre=false;
    	    	
    	    }
    	 }
    	catch (Exception e) 
    	{
    	    e.printStackTrace();
    	    logD("error occerd");
    	}
    }
	
	private void logD(String msg)
	{
		if(BuildConfig.DEBUG)
		{
			Log.d("VideoListService ",msg);
		}
	}

}
