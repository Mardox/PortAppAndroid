/*package com.edu.tube;

import java.util.ArrayList;

import com.edu.been.FeedDataBeens;
import com.edu.database.PlaylistDB;
import com.edu.database.Session;
import com.edu.loader.ImageLoader;
import com.edu.other.Singleton;
import com.fortysevendeg.swipelistview.SwipeListView;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MyPlaylists extends Activity 
{

	private ListView myList;
	private ArrayList<FeedDataBeens> beensList=new ArrayList<FeedDataBeens>();
	private PlaylistDB plDB;
	private Context context;
	private LinearLayout lowerPannel;
	private LinearLayout loadingPannel;
	private SwipeListView listView;
	private MyPlaylistAdpter playlistAdpter;
	private boolean isVisible=false;
	private int postionToRename;
	private EditText renameEdit;
	private Button saveReaname;
	private Button cancel;
	private Session session;
	private String type;
	private Button down;
	private TextView pannelTitle;
	private Button back;
	private Button create;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_playlist);
		context=this;
		session=new Session(context);
		lowerPannel=(LinearLayout) findViewById(R.id.lower_pannel);
		loadingPannel=(LinearLayout) findViewById(R.id.pannel);
		listView=(SwipeListView) findViewById(R.id.my_playlist);
		renameEdit=(EditText) findViewById(R.id.rename);
		saveReaname=(Button) findViewById(R.id.save);
		cancel=(Button) findViewById(R.id.cancel);
		down=(Button) findViewById(R.id.down);
		pannelTitle=(TextView) findViewById(R.id.rename_edit);
		
		back=(Button) findViewById(R.id.back);
		down=(Button) findViewById(R.id.down);
		create=(Button) findViewById(R.id.create_pl);
		pannelTitle=(TextView) findViewById(R.id.pannel_tittle);
		
		
		plDB=new PlaylistDB(context);
		listView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT); // there are five swiping modes
        listView.setSwipeActionLeft(SwipeListView.SWIPE_ACTION_REVEAL); //there are four swipe actions 
        listView.setSwipeActionRight(SwipeListView.SWIPE_ACTION_NONE);
        listView.setOffsetLeft(convertDpToPixel(200f)); // left side offset
        listView.setOffsetRight(convertDpToPixel(0f)); // right side offset
        listView.setAnimationTime(500); // Animation time
        listView.setSwipeOpenOnLongPress(true); // enable or disable SwipeOpenOnLongPress
		
        
        
        
        
        
		back.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
        down.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				slideOUT(lowerPannel);
			}
		});
        create.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				renameEdit.setHint("Create New Playlist");
				saveReaname.setText("Create");
				pannelTitle.setText("New Playlist");
				type=Singleton.CREATE_PLAYLIST;
				if(isVisible==false)
				{
					
					slideIN(lowerPannel);
				}
				else
				{
					slideOUT(lowerPannel);
				}
			}
		});
        
        
        
        
		saveReaname.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				String renameText=renameEdit.getText().toString();
				if(renameText!=null && renameText.length()>0)
				{
					if(type.equals(Singleton.CREATE_PLAYLIST))
					{
						FeedDataBeens been=new FeedDataBeens("edu_tube_user_pl_"+session.getUserPlCount()+1, renameText,null, "EduTube", "0", false, 0, 1);
						plDB.addPlaylist(renameText, "edu_tube_user_pl_"+session.getUserPlCount()+1, "EduTube", "0", null, 1, 0);
						session.saveUserPlCount(session.getUserPlCount()+1);
						beensList.add(0, been);
						playlistAdpter.notifyDataSetChanged();
						slideOUT(lowerPannel);
						
					}
					else if(type.equals(Singleton.RENAME_PLAYLIST))
					{
						
						Log.v("============================.","renamePlaylist");
						plDB.renamePlaylist(beensList.get(postionToRename).yt_id, renameText);
						FeedDataBeens been=new FeedDataBeens(beensList.get(postionToRename).yt_id, beensList.get(postionToRename).yt_title,beensList.get(postionToRename).yt_thumb, beensList.get(postionToRename).yt_author, beensList.get(postionToRename).size, beensList.get(postionToRename).isAdded, beensList.get(postionToRename).sync, beensList.get(postionToRename).sync);
						beensList.get(postionToRename).setTitle(renameText);
						
						beensList.set(postionToRename,been);
						playlistAdpter.notifyDataSetChanged();
						slideOUT(lowerPannel);
					}
				}
			}
		});
		cancel.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				
				slideOUT(lowerPannel);
				
			}
		});
		new MyPlaylistTask().execute();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// TODO Auto-generated method stub
		//return super.onCreateOptionsMenu(menu);
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.my_pl_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// TODO Auto-generated method stub
		//return super.onOptionsItemSelected(item);
		switch (item.getItemId())
		{
			case R.id.add_pl_menu:
				renameEdit.setHint("Create New Playlist");
				saveReaname.setText("Create");
				
				type=Singleton.CREATE_PLAYLIST;
				if(isVisible==false)
				{
					
					slideIN(lowerPannel);
				}
				else
				{
					slideOUT(lowerPannel);
				}
				return true;
			default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	
	
	
	public int convertDpToPixel(float dp) 
    {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }
	class MyPlaylistTask extends AsyncTask<Void, Void,ArrayList<FeedDataBeens>>
	{
		@Override
		protected void onPreExecute() 
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			loadingPannel.setVisibility(View.VISIBLE);
			listView.setVisibility(View.GONE);
		}
		
		@Override
		protected ArrayList<FeedDataBeens> doInBackground(Void... params)
		{
			// TODO Auto-generated method stub
			beensList=plDB.getLocalPlaylists();
			return beensList;
		}
		@Override
		protected void onPostExecute(ArrayList<FeedDataBeens> result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			listView.setVisibility(View.VISIBLE);
			loadingPannel.setVisibility(View.GONE);
			playlistAdpter=new MyPlaylistAdpter(context, result);
			listView.setAdapter(playlistAdpter);
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
		renameEdit.setText("");
		 view.setVisibility(View.GONE);
		 TranslateAnimation slide = new TranslateAnimation(0,0, 0,1000 );   
		 slide.setDuration(500);   
		 slide.setFillAfter(false);   
		 view.startAnimation(slide);
	}
	public class MyPlaylistAdpter extends BaseAdapter 
	{

		private ArrayList<FeedDataBeens> beensList=new ArrayList<FeedDataBeens>();
		public ImageLoader imageLoader;
		private Context context;
		
		public MyPlaylistAdpter(Context context,ArrayList<FeedDataBeens> beensList)
		{
			this.context=context;
			this.beensList=beensList;
			imageLoader=new  ImageLoader(context);
		}
		@Override
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return beensList.size();
		}

		@Override
		public Object getItem(int position) 
		{
			// TODO Auto-generated method stub
			return beensList.get(position);
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
			View view=convertView;
			ViewHolder vHolder;
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if(view==null)
			{
				view=inflater.inflate(R.layout.my_play_row, null);
				vHolder=new ViewHolder();
				vHolder.name=(TextView) view.findViewById(R.id.pl_name);
				vHolder.size=(TextView) view.findViewById(R.id.pl_size);
				vHolder.rename=(Button) view.findViewById(R.id.rename);
				vHolder.imageView=(ImageView) view.findViewById(R.id.thumb_pl);
				vHolder.childLock=(ToggleButton) view.findViewById(R.id.childlock_toggle);
				vHolder.sync=(ToggleButton) view.findViewById(R.id.sync_toggle);
				vHolder.delete=(Button) view.findViewById(R.id.delete);
				vHolder.childLockTV=(TextView) view.findViewById(R.id.child_on_off_tv);
				vHolder.syncTV=(TextView) view.findViewById(R.id.sync_on_off_tv);
				
				view.setTag(vHolder);
			}
			else
			{
				vHolder=(ViewHolder) view.getTag();
			}
			vHolder.name.setText(beensList.get(position).yt_title);
			vHolder.size.setText(beensList.get(position).size);
			
			
			
			vHolder.rename.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					renameEdit.setHint("Rename Playlist");
					saveReaname.setText("Rename");
					pannelTitle.setText("Rename Playlist");
					type=Singleton.RENAME_PLAYLIST;
					postionToRename=position;
					if(isVisible==false)
					{
						
						slideIN(lowerPannel);
					}
					else
					{
						slideOUT(lowerPannel);
					}
				}
			});
			imageLoader.DisplayImage( beensList.get(position).yt_thumb,vHolder.imageView);
			int isChild=beensList.get(position).child;
			int isSync=beensList.get(position).sync;
			if(isSync==0)
			{
				logD("Sync Off");
				vHolder.syncTV.setText("Sync Off");
				vHolder.sync.setChecked(false);
			}
			else
			{
				logD("Sync On");
				vHolder.syncTV.setText("Sync On");
				vHolder.sync.setChecked(true);
			}
			if(isChild==0)
			{
				logD("Child Off");
				vHolder.childLockTV.setText("Child lock Off");
				vHolder.childLock.setChecked(false);
			}
			else
			{
				logD("Child on");
				vHolder.childLockTV.setText("Child lock on");
				vHolder.childLock.setChecked(true);
			}
			vHolder.sync.setOnCheckedChangeListener(new OnCheckedChangeListener() 
			{
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
				{
					// TODO Auto-generated method stub
					if(isChecked==true)
					{
						plDB.updateSync(beensList.get(position).yt_id, 1);
						beensList.get(position).setSync(1);
						notifyDataSetChanged();
					}
					else
					{
						plDB.updateSync(beensList.get(position).yt_id, 0);
						beensList.get(position).setSync(0);
						notifyDataSetChanged();
					}
				}
			});
			
			vHolder.childLock.setOnCheckedChangeListener(new OnCheckedChangeListener()
			{
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
				{
					// TODO Auto-generated method stub
					if(isChecked==true)
					{
						plDB.setChildVisible(beensList.get(position).yt_id, 1);
						beensList.get(position).setCHild(1);
						notifyDataSetChanged();
					}
					else
					{
						plDB.setChildVisible(beensList.get(position).yt_id, 0);
						beensList.get(position).setCHild(0);
						notifyDataSetChanged();
					}
				}
			});
			vHolder.delete.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					plDB.deletePlaylist(beensList.get(position).yt_id);
					beensList.remove(position);
					notifyDataSetChanged();
				}
			});
			return view;
		}
		class ViewHolder
		{
			TextView name;
			TextView size;
			Button rename; 
			ImageView imageView;
			ToggleButton childLock;
			Button delete;
			ToggleButton sync; 
			TextView childLockTV;
			TextView syncTV;
		}
		private void logD(String msg)
		{
			if(BuildConfig.DEBUG)
			{
				Log.d("MyPlaylistAdpter",msg);
			}
		}
	}
	
}
*/