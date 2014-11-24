package com.edu.tube;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import com.edu.adapter.Local_list_adpter;
import com.edu.adapter.SearchPlaylistAdapter;
import com.edu.been.FeedDataBeens;
import com.edu.been.PlaylistItems;
import com.edu.database.PlaylistDB;
import com.edu.other.HttpHandler;
import com.edu.other.Singleton;

import android.R.anim;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

public class Searchvideo extends Activity 
{

	private SearchView search1;
	private ListView listView;
	private HttpHandler handler;
	private ArrayList<FeedDataBeens> feedDataBeensList=new ArrayList<FeedDataBeens>();
	private LinearLayout loadingPannel;
	private int start_index=1;
	private int max_result=10;
	private SearchPlaylistAdapter adapter;
	private Context context;
	private Button addToPlaylist;
	private Button open_to_see_video;
	private LinearLayout lowerPannel;
	private PlaylistDB playlistDB;
	private boolean isVisible=false;
	private FeedDataBeens currentFeedDatabeens;
	private LinearLayout footet_ll;
	private boolean isLoading;
	private int currentPage=11;
	private String textToSearch;
	/*private Button searchButton1;*/
	private SearchView mSearchView;
	private ActionBar actionBar;
	
	private EditText searchEditText;
	private Button search;
	private Button clear;
	private Button back;
	private LinearLayout back_ll;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_playlist_xml);
		context=this;
		
		playlistDB=new PlaylistDB(context);
		listView=(ListView) findViewById(R.id.list);
		handler=new HttpHandler();
		loadingPannel=(LinearLayout) findViewById(R.id.pannel);
		lowerPannel=(LinearLayout) findViewById(R.id.lower_pannel);
		addToPlaylist=(Button) findViewById(R.id.sync_pl);
		open_to_see_video=(Button) findViewById(R.id.see_video_list);
		back=(Button) findViewById(R.id.back);
		back_ll=(LinearLayout) findViewById(R.id.back_ll);
		new SearchingTask().execute(new String[]{getResources().getString(R.string.playlist_search)});
		setFooterLoder(listView);
		back_ll.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		//searchEditText=(EditText) findViewById(R.id.search_edit);
		//search=(Button) findViewById(R.id.search);
		//clear=(Button) findViewById(R.id.clear);
		/*back=(Button) findViewById(R.id.back);
		
		
		*/
		/*clear.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				searchEditText.setText("");
				clear.setVisibility(View.GONE);
			}
		});
		search.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if(!searchEditText.getText().toString().equals("")|| searchEditText.getText()!=null)
				{
					searchEditText.clearFocus();
				    InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				    in.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
					new SearchingTask().execute(new String[]{searchEditText.getText().toString().trim()});
				}
			}
		});*/
		/*searchEditText.setOnEditorActionListener(new OnEditorActionListener() 
		{
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) 
			{
				// TODO Auto-generated method stub
				if(actionId==EditorInfo.IME_ACTION_SEARCH)
				{
					
					if(!searchEditText.getText().toString().equals("")|| searchEditText.getText()!=null)
					{
						searchEditText.clearFocus();
					    InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					    in.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
						
					}
				}
				return false;
			}
		});*/
		/*searchEditText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) 
			{
				// TODO Auto-generated method stub
				if(!searchEditText.getText().toString().equals("")|| searchEditText.getText()!=null)
				{
					clear.setVisibility(View.VISIBLE);
				}
				else
				{
					clear.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) 
			{
				// TODO Auto-generated method stub
				if(!searchEditText.getText().toString().equals("")|| searchEditText.getText()!=null)
				{
					clear.setVisibility(View.VISIBLE);
				}
				else
				{
					clear.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		*/
		
		
		
        listView.setOnScrollListener(new OnScrollListener()
        {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) 
			{
				// TODO Auto-generated method stub
				if(isVisible==true)
				{
					slideOUT(lowerPannel);
				}
				if(listIsAtBottom(listView))
				{
					logE("PLayLIst bottom came");
					if(Singleton.hasMorePlaylist)
					{
						if(isLoading==false)
						{
							new LoadMorePlaylist().execute(new String[]{Singleton.keyword,String.valueOf(currentPage),"10"});
						}
						else
						{
							logE("Page already .loading");
						}
					}
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) 
			{
				// TODO Auto-generated method stub
			}
		});
        listView.setOnItemClickListener(new OnItemClickListener()
        {
        	@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
				// TODO Auto-generated method stub
        		currentFeedDatabeens=feedDataBeensList.get(position);
				/*if(isVisible==false)
				{
					//Toast.makeText(context, "Show",Toast.LENGTH_LONG).show();
					
					//showPannel();
					slideIN(lowerPannel);
				}
				else
				{
					//Toast.makeText(context, "Hide",Toast.LENGTH_LONG).show();
					//hidePannel();
					slideOUT(lowerPannel);
				}*/
        		
        		
        		if(currentFeedDatabeens!=null)
				{
					Intent intent=new Intent(context, VideoListActivity.class);
					Bundle bundle=new Bundle();
					bundle.putString(Singleton.playlist_id, currentFeedDatabeens.getID());
					bundle.putString(Singleton.playlist_name, currentFeedDatabeens.getTitle());
					bundle.putString(Singleton.playlist_username, currentFeedDatabeens.getAuthor());
					bundle.putString(Singleton.playlist_count, currentFeedDatabeens.getSize());
					bundle.putString(Singleton.playlist_thumb, currentFeedDatabeens.getThumb());
					bundle.putInt(Singleton.playlist_child, currentFeedDatabeens.getChild());
					bundle.putInt(Singleton.playlist_sync, currentFeedDatabeens.getSync());
					intent.putExtra(Singleton.bundle, bundle);
					startActivity(intent);
					slideOUT(lowerPannel);
				}
				
			}
		});
        open_to_see_video.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				/*if(currentFeedDatabeens!=null)
				{
					Intent intent=new Intent(context, VideoListActivity.class);
					Bundle bundle=new Bundle();
					bundle.putString(Singleton.playlist_id, currentFeedDatabeens.getID());
					bundle.putString(Singleton.playlist_name, currentFeedDatabeens.getTitle());
					bundle.putString(Singleton.playlist_username, currentFeedDatabeens.getAuthor());
					bundle.putString(Singleton.playlist_count, currentFeedDatabeens.getSize());
					bundle.putString(Singleton.playlist_thumb, currentFeedDatabeens.getThumb());
					bundle.putInt(Singleton.playlist_child, currentFeedDatabeens.getChild());
					bundle.putInt(Singleton.playlist_sync, currentFeedDatabeens.getSync());
					intent.putExtra(Singleton.bundle, bundle);
					startActivity(intent);
					slideOUT(lowerPannel);
				}*/
				
				
				
			}
		});
        addToPlaylist.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				String name=currentFeedDatabeens.getTitle();
				String pl_id=currentFeedDatabeens.getID();
				String username=currentFeedDatabeens.getAuthor();
				String number_of_videos=currentFeedDatabeens.getSize();
				String thumb=currentFeedDatabeens.getThumb();
				logE("Name :"+name);
				logE("Playlist ID "+pl_id);
				logE("Username "+username);
				logE("Thumb "+thumb);
				logE("Number of Videos "+number_of_videos);
				if(playlistDB.isPlaylistAlreadyAdd(pl_id)==false)
				{
					playlistDB.addPlaylist(name,pl_id, username, number_of_videos+"", thumb,1,1);
					new VideoInsertTask().execute(new String[]{pl_id});
				}
				else
				{
					Toast.makeText(context, "Playlist Already Added", Toast.LENGTH_SHORT).show();
				}
				
				slideOUT(lowerPannel);
				
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// TODO Auto-generated method stub
		/*switch (item.getItemId())
		{
		  case android.R.id.home:
			  onBackPressed();
		  return true;
		  default:
		    
		   }*/
		 return true;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		/*MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        setupSearchView(searchItem);*/
        return true;
		
	}
	
	
	 protected boolean isAlwaysExpanded() 
	 {
	   return false;
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
	private void showPannel()
	{
		isVisible=true;
		 Animation animation = AnimationUtils.loadAnimation(this,R.anim.show);
		 animation.setAnimationListener(new AnimationListener() 
		    {                  
		        @Override
		        public void onAnimationStart(Animation animation) 
		        {}

		        @Override
		        public void onAnimationRepeat(Animation animation) 
		        {}

		        @Override
		        public void onAnimationEnd(Animation animation) 
		        {
		        	lowerPannel.setVisibility(View.VISIBLE);
		        }
		    });

		    lowerPannel.clearAnimation();
		    lowerPannel.startAnimation(animation);
		
	}
	private void hidePannel()
	{
		isVisible=false;
		 Animation animation = AnimationUtils.loadAnimation(this,R.anim.hide);

		    animation.setAnimationListener(new AnimationListener() 
		    {                  
		        @Override
		        public void onAnimationStart(Animation animation) 
		        {}
		        @Override
		        public void onAnimationRepeat(Animation animation) 
		        {}

		        @Override
		        public void onAnimationEnd(Animation animation) 
		        {
		        	lowerPannel.setVisibility(View.GONE);
		        }
		    });
		    lowerPannel.clearAnimation();
		    lowerPannel.startAnimation(animation);
	}
	public void setFooterLoder(ListView list)
	{
		LayoutInflater inflator=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view=inflator.inflate(R.layout.loading_bar, null);
		footet_ll=(LinearLayout) view.findViewById(R.id.loadingPl);
		list.addFooterView(view);
		footet_ll.setVisibility(View.GONE);
	}
	public class LoadMorePlaylist extends AsyncTask<String, Void,ArrayList<FeedDataBeens> >
	{
		@Override
		protected void onPreExecute() 
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			footet_ll.setVisibility(View.VISIBLE);
			isLoading=true;
		}
		@Override
		protected ArrayList<FeedDataBeens> doInBackground(final String... arg0)
		{
			// TODO Auto-generated method stub
			
			logE("=============================="+arg0[0]);
			ArrayList<FeedDataBeens> listItems=searchPlaylist(arg0[0], arg0[1],arg0[2]);
			
			feedDataBeensList.addAll(listItems);
			return feedDataBeensList;
		}
		
		@Override
		protected void onPostExecute(ArrayList<FeedDataBeens> result)
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			isLoading=false;
			footet_ll.setVisibility(View.GONE);
			currentPage=currentPage+10;
			int currentPosition = listView.getFirstVisiblePosition();
			adapter=new SearchPlaylistAdapter(context, result);
			listView.setAdapter(adapter);
			listView.setSelectionFromTop(currentPosition + 1, 0);
		}
	}
	
	class SearchingTask extends AsyncTask<String,Void,ArrayList<FeedDataBeens>>
	{
		@Override
		protected void onPreExecute() 
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			loadingPannel.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected ArrayList<FeedDataBeens> doInBackground(String... params) 
		{
			// TODO Auto-generated method stub
			try 
			{
				feedDataBeensList=searchPlaylist(URLEncoder.encode(params[0],"UTF-8"), start_index+"", max_result+"");
			}
			catch (UnsupportedEncodingException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//start_index=start_index+10;
			return feedDataBeensList;
		}
		@Override
		protected void onPostExecute(ArrayList<FeedDataBeens> result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			loadingPannel.setVisibility(View.GONE);
			adapter=new SearchPlaylistAdapter(context, result);
			adapter.imageLoader.clearCache();
			listView.setAdapter(adapter);
		}
	}
	public ArrayList<FeedDataBeens> searchPlaylist(String search, String index,String number)
	{
		
		ArrayList<FeedDataBeens> beensList=new ArrayList<FeedDataBeens>();
		String jesonData = handler.makeServiceCallforPlayList(search,index, number);
    	logE(search);
    	logE(jesonData);
    	String id = "123";
    	String auther="123";
    	String title="123";
    	try 
    	{       
    	    JSONObject jObj = new JSONObject(jesonData); 
    	    
    	    if(jObj.getJSONObject("data").has("items"))
    	    {
    	    	JSONArray ja = jObj.getJSONObject("data").getJSONArray("items");
    	    	if(ja.length()<Integer.parseInt(number))
    	    	{
    	    		Singleton.hasMorePlaylist=false;
    	    	}
    	    	else
    	    	{
    	    		Singleton.hasMorePlaylist=true;
    	    	}
    	    	for(int i=0;i<ja.length();i++)
        	    {
        	    	
        	    	JSONObject jo = (JSONObject) ja.get(i);
            	    id = jo.getString("id");
            	    auther=jo.getString("author");
            	    logE("id is " +id);
            	    logE("author is "+auther);
            	    title=jo.getString("title");
            	    logE("Title is "+title);
            	    
            	    if(jo.has("thumbnail"))
            	    {
            	    	JSONObject thum= jo.getJSONObject("thumbnail");
                	    String thumb=thum.getString("hqDefault");
                	    logE("Thumb is "+thumb);
                	    String size=jo.getString("size");
                	    boolean isAdded=playlistDB.isPlaylistAlreadyAdd(id);
                	    if(isAdded==true)
                	    {
                	    	logE("TRUEEEEEEEEEEE");
                	    	FeedDataBeens feedData=new FeedDataBeens(id, title, thumb, auther, size,true,0,0);
                    	    beensList.add(feedData);
                	    }
                	    else
                	    {
                	    	logE("FAAAAAAAAAAAAAAALLLLLLLLLSEEE");
                	    	FeedDataBeens feedData=new FeedDataBeens(id, title, thumb, auther, size,false,0,0);
                    	    beensList.add(feedData);
                	    }
            	    }
            	    
            	}
    	    }
    	    else
    	    {
    	    	Toast.makeText(context, "No Playlist found", Toast.LENGTH_LONG).show();
    	    	logE("No PLaylist found");
    	    }
    	}
    	catch (Exception e) 
    	{
    	    e.printStackTrace();
    	    logE("error occerd");
    	}
    	return beensList;
    }
	private void logE(String msg)
	{
		if(BuildConfig.DEBUG)
		{
			Log.e("MainActivty",msg);
		}
	}
	/*@Override
	public boolean onQueryTextSubmit(String query) 
	{
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
		new SearchingTask().execute(new String[]{query});
		return false;
	}
	@Override
	public boolean onQueryTextChange(String newText) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	*/


	
	
	
}
