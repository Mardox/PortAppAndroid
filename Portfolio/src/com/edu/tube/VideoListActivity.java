package com.edu.tube;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.edu.adapter.Local_list_adpter;
import com.edu.been.FeedDataBeens;
import com.edu.been.PlaylistItems;
import com.edu.database.PlaylistDB;
import com.edu.loader.ImageLoader;
import com.edu.other.HttpHandler;
import com.edu.other.Singleton;
import com.edu.service.VideoListService;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class VideoListActivity extends Activity 
{
	private LinearLayout loadding_pannel;
	private ListView videoListVIew;
	private HttpHandler handler;
	
	private String pl_name;
	private String pl_id;
	private String pl_username;
	private String pl_count;
	private String pl_thumb;
	private int  pl_child;
	private int pl_sync;
	private Bundle bundle;
	private VideoListAdpter adpter;
	private Context context;
	private int currentPage=11;
	
	
	private ArrayList<PlaylistItems> itemsList=new ArrayList<PlaylistItems>();
	ArrayList<FeedDataBeens> beensList=new ArrayList<FeedDataBeens>();
	private LinearLayout footet_ll;
	private boolean isVisible=false;
	
	private PlaylistDB playlistDB;
	private ListView playlistNameList;
	
	private LinearLayout lowerPannel;
	private int current_postion;
	private FrameLayout framLayout;
	
	private ToggleButton local_selector;
	private Button back;
	private Button down;
	private LinearLayout back_ll;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_list);
		videoListVIew=(ListView) findViewById(R.id.video_list);
		loadding_pannel=(LinearLayout) findViewById(R.id.pannel);
		
		/*bundle=getIntent().getBundleExtra(Singleton.bundle);
		pl_name=bundle.getString(Singleton.playlist_name);
		pl_id=bundle.getString(Singleton.playlist_id);
		pl_username=bundle.getString(Singleton.playlist_username);
		pl_thumb=bundle.getString(Singleton.playlist_thumb);
		pl_sync=bundle.getInt(Singleton.playlist_sync);
		pl_child=bundle.getInt(Singleton.playlist_child);*/
		back=(Button) findViewById(R.id.back);
		handler=new HttpHandler();
		context=this;
		playlistDB=new PlaylistDB(context);
		
		pl_id=getResources().getString(R.string.playlist_id);
		
		
		loadding_pannel=(LinearLayout) findViewById(R.id.loadding_pannel);
		
		back=(Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		back_ll=(LinearLayout) findViewById(R.id.back_ll);
		back_ll.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		/*down.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				slideOUT(lowerPannel);
			}
		});*/
		setFooterLoder(videoListVIew);
		
	/*	if(playlistDB.isPlaylistAlreadyAdd(pl_id))
		{
			local_selector.setChecked(true);
		}
		else
		{
			local_selector.setChecked(false);
		}*/
		new getVideosTask().execute(new String[]{pl_id,"1"});
		
		
		videoListVIew.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
				// TODO Auto-generated method stub
				String v_id=itemsList.get(position).v_id;
				Intent intent=new Intent(context, VideoPlayerActivity.class);
				intent.putExtra(Singleton.VIDEO_ID, v_id);
				startActivity(intent);
			}
		});
		
		/*videoListVIew.setOnTouchListener(new OnTouchListener() 
		{
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(isVisible==true)
				{
					slideOUT(lowerPannel);
				}
				else
				{
					slideIN(lowerPannel);
				}
				return true;
			}
		});*/
		
		/*local_selector.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if(local_selector.isChecked()==true)
				{
					playlistDB.addPlaylist(pl_name, pl_id, pl_username, pl_count, pl_thumb, pl_child, pl_sync);
					new VideoInsertTask().execute(new String[]{pl_id});
				}
				else
				{
					playlistDB.deletePlaylist(pl_id);
					new VideoRemoveTask().execute(new String[]{pl_id});
				}
			}
		});*/
		back.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
			finish();
			}
		});
		/*playlistNameList.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
			{
				// TODO Auto-generated method stub
				
				String p_ud=beensList.get(arg2).yt_id;
				String p_name=beensList.get(arg2).yt_title;
				boolean isAdded=playlistDB.isVideoAlreadyAdded(p_ud, itemsList.get(current_postion).v_id);
				if(isAdded)
				{
					logD("Alreday Added");
					slideOUT(lowerPannel);
				}
				else
				{
					logE("Not Added");
					//playlistDB.addVideoToOtherPlaylist(itemsList.get(current_postion).v_id, p_ud);
					playlistDB.addVideoList(itemsList.get(current_postion).playlist_id, itemsList.get(current_postion).playlist_name, itemsList.get(current_postion).v_id, itemsList.get(current_postion).v_url, itemsList.get(current_postion).v_name, itemsList.get(current_postion).thumb, itemsList.get(current_postion).channel, p_ud, pl_name, 1, 1);
					slideOUT(lowerPannel);
				}
				
			}
		});*/
		videoListVIew.setOnScrollListener(new OnScrollListener() 
		{
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) 
			{
				// TODO Auto-generated method stub
				if(listIsAtBottom(videoListVIew))
				{
					if(Singleton.hasMOre)
					{
						new loadMoreVIdeos().execute(new String[]{pl_id,String.valueOf(currentPage)} );
						//new Demo2().execute(new String[]{pl_id,String.valueOf(currentPage)});
					}
				}
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) 
			{
				// TODO Auto-generated method stub
			}
		});
		Intent inten=new Intent(context,VideoListService.class);
		startService(inten);
	}
	@Override
	public void onBackPressed() 
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(isVisible==true)
		{
			slideOUT(lowerPannel);
		}
	}
	
	class VideoRemoveTask extends AsyncTask<String,Void,Void>
	{

		@Override
		protected Void doInBackground(String... params) 
		{
			// TODO Auto-generated method stub
			
			playlistDB.deleteVideoPL(params[0]);
			return null;
		}
		
	}
	
	
	class VideoInsertTask extends AsyncTask<String, Void, Void>
	{
		@Override
		protected void onPreExecute() 
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			logE("Video insertTask");
		}

		@Override
		protected Void doInBackground(String... params) 
		{
			// TODO Auto-generated method stub
			getPlayListItem(params[0]);
			
			return null;
		}
		@Override
		protected void onPostExecute(Void result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
		}
		
	}
	public void  getPlayListItem(String ids)
	{
		/*ArrayList<PlaylistItems> pl_Items = new ArrayList<PlaylistItems>();*/
		String jesonData = handler.makeServiceCallforPlayListItem(ids);
    	logE(ids);
    	logE(jesonData);
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
    	    logE("id is " +pl_id);
    	    channel_name=jobjPL.getString("author");
    	    logE("Channel name is "+channel_name);
    	    playlist_name=jobjPL.getString("title");
    	    logE("Playlist name is "+playlist_name);
    	    
    	    if(jObj.getJSONObject("data").has("items"))
    	    {
    	    	logE("Response has Items in json");
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
                	logE("Video ID is "+vid_id);
                	video_name=vidObj.getString("title");
                	logE("Video Name is  "+video_name);
                	
                	if(vidObj.has("thumbnail") && vidObj.has("player"))
                	{
                		logE("==================================== has thumb ");
                		JSONObject thum= vidObj.getJSONObject("thumbnail");
                    	thumb=thum.getString("hqDefault");
                    	logE("Thumb is "+thumb);
                    	JSONObject vidPlayer=vidObj.getJSONObject("player");
                    	vid_url=vidPlayer.getString("default");
                    	playlistDB.addVideoList(pl_id, playlist_name, vid_id, vid_url, video_name, thumb, channel_name,pl_id,playlist_name,0,0);
                    }
                	else
                	{
                		logE("==================================== has no thumb ");
                	}
                }
    	    }
    	    else
    	    {
    	    	logE("+++++++++++++++++++++++++++ No More Videos");
    	    	Singleton.hasMOre=false;
    	    	
    	    }
    	 }
    	catch (Exception e) 
    	{
    	    e.printStackTrace();
    	    logE("error occerd");
    	}
    }
	class GetAllPlaylistfromDBTask extends AsyncTask<Void, Void, ArrayList<FeedDataBeens>>
	{

		@Override
		protected ArrayList<FeedDataBeens> doInBackground(Void... params) 
		{
			// TODO Auto-generated method stub
			beensList=new ArrayList<FeedDataBeens>();
			beensList=playlistDB.getLocalPlaylists();
			return beensList;
		}
		@Override
		protected void onPostExecute(ArrayList<FeedDataBeens> result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Local_list_adpter localAdpter=new Local_list_adpter(context, result);
			playlistNameList.setAdapter(localAdpter);
		}
		
	}
	private boolean listIsAtBottom(ListView list)
	{
		if (list.getLastVisiblePosition() == list.getAdapter().getCount() - 1&& list.getChildAt(list.getChildCount() - 1).getBottom() <= list.getHeight()) 
		{
			logE("List Bottom Came");
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*public void onAddButtonClick(View v)
	{
		new GetAllPlaylistfromDBTask().execute();
		if(isVisible==true)
		{
			slideOUT(lowerPannel);
		}
		else
		{
			slideIN(lowerPannel);
		}
	}*/
	public class VideoListAdpter extends BaseAdapter 
	{

		private Context context;
		private ArrayList<PlaylistItems> plItems=new ArrayList<PlaylistItems>();
		public ImageLoader imageLoader;
		
		public VideoListAdpter(Context context,ArrayList<PlaylistItems> plItems)
		{
			this.context=context;
			this.plItems=plItems;
			imageLoader=new ImageLoader(context);
		}
		
		
		@Override
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return plItems.size();
		}

		@Override
		public Object getItem(int position) 
		{
			// TODO Auto-generated method stub
			return plItems.get(position);
		}

		@Override
		public long getItemId(int position) 
		{
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) 
		{
			// TODO Auto-generated method stub
			View view=convertView ;
			LayoutInflater inflater;
			ViewHolder holder;
			if(view==null)
			{
				inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view=inflater.inflate(R.layout.search_video_row, null);
				holder=new ViewHolder();
				holder.videoName=(TextView) view.findViewById(R.id.video_name);
				holder.imageThumb=(ImageView) view.findViewById(R.id.thumb_pl);
				holder.add=(Button) view.findViewById(R.id.add);
				view.setTag(holder);
			}
			else
			{
				holder=(ViewHolder) view.getTag();
			}
			holder.videoName.setText(plItems.get(position).playlist_name);
			imageLoader.DisplayImage(plItems.get(position).thumb, holder.imageThumb);
			holder.add.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					new GetAllPlaylistfromDBTask().execute();
					current_postion=position;
					if(isVisible==true)
					{
						slideOUT(lowerPannel);
					}
					else
					{
						slideIN(lowerPannel);
					}
				}
			});
			return view;
		}
		
		class ViewHolder
		{
			TextView videoName;
			ImageView imageThumb;
			Button add;
		}

	}
	private void slideIN(View view)
	{
		isVisible=true;
		view.setVisibility(View.VISIBLE);
		TranslateAnimation slide = new TranslateAnimation(0, 0,1000,0 );   
		slide.setDuration(500);   
		slide.setFillAfter(false);   
		view.startAnimation(slide);
	}
	private void slideOUT(View view)
	{
		isVisible=false;
		 view.setVisibility(View.GONE);
		 TranslateAnimation slide = new TranslateAnimation(0,0, 0,1000 );   
		 slide.setDuration(500);   
		 slide.setFillAfter(false);   
		 view.startAnimation(slide);
	}
	class getVideosTask extends AsyncTask<String, Void,ArrayList<PlaylistItems> >
	{
		@Override
		protected void onPreExecute() 
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			videoListVIew.setVisibility(View.GONE);
			loadding_pannel.setVisibility(View.VISIBLE);
		}
		@Override
		protected ArrayList<PlaylistItems> doInBackground(String... arg0)
		{
			// TODO Auto-generated method stub
			logE("========================================="+arg0[1]);
			logE("====================.0=========="+arg0[0]);
			itemsList=getPlayListItem(arg0[0],arg0[1]);
			return itemsList;
		}
		@Override
		protected void onPostExecute(ArrayList<PlaylistItems> result)
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			currentPage=11;
			videoListVIew.setVisibility(View.VISIBLE);
			loadding_pannel.setVisibility(View.GONE);
			adpter=new VideoListAdpter(context, result);
			adpter.imageLoader.clearCache();
			videoListVIew.setAdapter(adpter);
					
		}
	}
	public void setFooterLoder(ListView list)
	{
		LayoutInflater inflator=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view=inflator.inflate(R.layout.loading_bar, null);
		footet_ll=(LinearLayout) view.findViewById(R.id.loadingPl);
		list.addFooterView(view);
		//footet_ll.setVisibility(View.GONE);
	}
	public void removeFooterLoder()
	{
		LayoutInflater inflator=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view=inflator.inflate(R.layout.loading_bar, null);
		LinearLayout footet_ll=(LinearLayout) view.findViewById(R.id.loadingPl);
		//list.addFooterView(view);
		videoListVIew.removeFooterView(view);
	}
	public ArrayList<PlaylistItems>  getPlayListItem(String ids, String index)
	{
		ArrayList<PlaylistItems> pl_Items = new ArrayList<PlaylistItems>();
		String jesonData = handler.makeServiceCallforPlayListItemForIndex(ids, index,"10");
    	logE(ids);
    	logE(jesonData);
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
    	    logE("id is " +pl_id);
    	    channel_name=jobjPL.getString("author");
    	    logE("Channel name is "+channel_name);
    	    playlist_name=jobjPL.getString("title");
    	    logE("Playlist name is "+playlist_name);
    	    
    	    if(jObj.getJSONObject("data").has("items"))
    	    {
    	    	logE("Response has Items in json");
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
                	logE("Video ID is "+vid_id);
                	video_name=vidObj.getString("title");
                	logE("Video Name is  "+video_name);
                	
                	if(vidObj.has("thumbnail") && vidObj.has("player"))
                	{
                		logD("==================================== has thumb ");
                		JSONObject thum= vidObj.getJSONObject("thumbnail");
                    	thumb=thum.getString("hqDefault");
                    	logE("Thumb is "+thumb);
                    	JSONObject vidPlayer=vidObj.getJSONObject("player");
                    	vid_url=vidPlayer.getString("default");
                    	pl_Items.add(new PlaylistItems(pl_id, playlist_name, vid_id, vid_url, video_name, thumb, channel_name));
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
    	    logE("error occerd");
    	}
    	return pl_Items;
    }
	class loadMoreVIdeos extends AsyncTask<String,Void,ArrayList<PlaylistItems> >
	{
		View view;
		@Override
		protected void onPreExecute() 
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			footet_ll.setVisibility(View.VISIBLE);
		}
		@Override
		protected ArrayList<PlaylistItems> doInBackground(String... arg0)
		{
			// TODO Auto-generated method stub
			logE("====================.0=========="+arg0[0]);
			ArrayList<PlaylistItems> items=getPlayListItem(arg0[0],arg0[1]);
			return items;
		}
		@Override
		protected void onPostExecute(ArrayList<PlaylistItems> result)
		{
			// TODO Auto-generated method stub
			currentPage=currentPage+10;
			super.onPostExecute(result);
			footet_ll.setVisibility(View.GONE);
			int currentPosition = videoListVIew.getFirstVisiblePosition();
			itemsList.addAll(result);
			adpter=new VideoListAdpter(context, itemsList);
			videoListVIew.setAdapter(adpter);
			videoListVIew.setSelectionFromTop(currentPosition + 1, 0);
			loadding_pannel.setVisibility(View.GONE);
			videoListVIew.setVisibility(View.VISIBLE);
			
		}
	}
	private void logE(String msg)
	{
		if(BuildConfig.DEBUG)
		{
			Log.e("VideoListActivity : ", msg);
		}
	}
	private void logD(String msg)
	{
		if(BuildConfig.DEBUG)
		{
			Log.d("VideoListActivity : ", msg);
		}
	}
}
