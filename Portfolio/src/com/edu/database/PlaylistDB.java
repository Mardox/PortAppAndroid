package com.edu.database;

import java.util.ArrayList;

import com.edu.been.FeedDataBeens;
import com.edu.been.PlaylistItems;
import com.edu.tube.BuildConfig;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlaylistDB extends SQLiteOpenHelper 
{
	
	private static final String DATABASE_NAME="edu_tube_db"; 
	public static final String ID="id";
	public static final String TABLE_NAME_PLAYLIST="playlist";
	public static final String PLAYLIST_CLM_ID="playlist_id";
	public static final String PLAYLIST_CLM_NAME="playlist_name";
	public static final String PLAYLIST_CLM_USERNAME="username";
	public static final String PLAYLIST_CLM_THUMB="thumb";
	public static final String PLAYLIST_CLM_NO="no_of_videos";
	public static final String PLAYLIST_CLM_ADDD="is add";
	public static final String PLAYLIST_CLM_SYNC="sync";
	public static final String PLAYLIST_CLM_VISIBLE_CHILD="visible_to_child";
	

	final String CREATE_PLAYLIST_FEED="CREATE TABLE "
			+TABLE_NAME_PLAYLIST
			+"( "
			+ID
			+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+PLAYLIST_CLM_ID
			+" STRING, "
			+PLAYLIST_CLM_NAME
			+" STRING, "
			+PLAYLIST_CLM_USERNAME
			+" STRING, "
			+PLAYLIST_CLM_THUMB
			+" STRING, "
			+PLAYLIST_CLM_NO 
			+" STRING, "
			+PLAYLIST_CLM_SYNC
			+" INTEGER, "
			+PLAYLIST_CLM_VISIBLE_CHILD
			+" INTEGER "
			+")";	

	public static final String TABLE_NAME_VODELIST_="videos_list";
	public static final String VIDEOLIST_IT_P_ID="playlist_id";
	public static final String VIDEOLIST_IT_NAME="playlist_name";
	public static final String VIDEOLIST_IT_VID_ID="v_id";
	public static final String VIDEOLIST_ID_VID_URL="v_url";
	public static final String VIDEOLIST_ID_VID_NAME="v_name";
	public static final String VIDEOLIST_IT_THUMB="v_thumb";
	public static final String VIDEOLIST_IT_CHANNEL_NAME="v_channel";
	public static final String VIDEO_LOCAL_PL_ID="local_pl_id";
	public static final String VIDEO_LOCAL_PL_NAME="local_pl_name";
	public static final String VIDEO_IS_VISIBLE_TO_CHILD="visibility";
	public static final String VIDEO_NEW_ADDED="is_new_added";
	
	final String CREATE_TABLE_P_ITEM="CREATE TABLE "
			+TABLE_NAME_VODELIST_
			+"( "
			+ID
			+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+VIDEOLIST_IT_P_ID
			+" String, "
			+VIDEOLIST_IT_NAME
			+" String, "
			+VIDEOLIST_IT_VID_ID
			+" String, "
			+VIDEOLIST_ID_VID_NAME
			+" String, "
			+VIDEOLIST_ID_VID_URL
			+" String, "
			+VIDEOLIST_IT_THUMB
			+" String, "
			+VIDEOLIST_IT_CHANNEL_NAME
			+" String, "
			+VIDEO_LOCAL_PL_ID
			+" String, "
			+VIDEO_LOCAL_PL_NAME
			+" String, "
			+VIDEO_IS_VISIBLE_TO_CHILD
			+" INTEGER, "
			+VIDEO_NEW_ADDED
			+" INTEGER "
			+")";
	
	public PlaylistDB(Context context) 
	{
		super(context,DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PLAYLIST_FEED);
		db.execSQL(CREATE_TABLE_P_ITEM);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_PLAYLIST);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_VODELIST_);
		
	}
	public void addVideoList(String pl_id,String pl_name,String v_id,String v_url,String v_name, String v_thumb,String v_channel,String local_pl_id,String local_pl_name, int childeVisible, int new_add)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cValues=new ContentValues();
		cValues.put(VIDEOLIST_IT_P_ID, pl_id);
		cValues.put(VIDEOLIST_IT_NAME, pl_name);
		cValues.put(VIDEOLIST_IT_VID_ID, v_id);
		cValues.put(VIDEOLIST_ID_VID_NAME, v_name);
		cValues.put(VIDEOLIST_ID_VID_URL, v_url);
		cValues.put(VIDEOLIST_IT_THUMB, v_thumb);
		cValues.put(VIDEOLIST_IT_CHANNEL_NAME, v_channel);
		cValues.put(VIDEO_LOCAL_PL_ID, local_pl_id);
		cValues.put(VIDEO_LOCAL_PL_NAME ,local_pl_name);
		cValues.put(VIDEO_IS_VISIBLE_TO_CHILD, childeVisible);
		cValues.put(VIDEO_NEW_ADDED, new_add);
		
		db.insert(TABLE_NAME_VODELIST_, null, cValues);
		db.close();
	}
	
	public ArrayList<PlaylistItems> getAllVideoList()
	{
		ArrayList<PlaylistItems> listItems=new ArrayList<PlaylistItems>();
		SQLiteDatabase db=this.getReadableDatabase();
		String query="Select * from "+TABLE_NAME_VODELIST_;
		Cursor cursor=db.rawQuery(query, null);
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
		{
			String pl_id=cursor.getString(cursor.getColumnIndex(VIDEOLIST_IT_P_ID));
			logI("Video Playlist id="+pl_id);
			String pl_name=cursor.getString(cursor.getColumnIndex(VIDEOLIST_IT_NAME));
			logI("Video Playlist Name="+pl_name);
			String v_id=cursor.getString(cursor.getColumnIndex(VIDEOLIST_IT_VID_ID));
			logI("Video  id="+v_id);
			String v_name=cursor.getString(cursor.getColumnIndex(VIDEOLIST_ID_VID_NAME));
			logI("Video Playlist id="+v_name);
			String v_url=cursor.getString(cursor.getColumnIndex(VIDEOLIST_ID_VID_URL));
			logI("Video url="+v_url);
			String v_thumb=cursor.getString(cursor.getColumnIndex(VIDEOLIST_IT_THUMB));
			logI("Video thumb="+v_thumb);
			String v_channel=cursor.getString(cursor.getColumnIndex(VIDEOLIST_IT_CHANNEL_NAME));
			logI("Channel Name "+v_channel);
			String local_pl_name=cursor.getString(cursor.getColumnIndex(VIDEO_LOCAL_PL_NAME));
			logI("Local Playlist Namre "+local_pl_name);
			String local_pl_id=cursor.getString(cursor.getColumnIndex(VIDEO_LOCAL_PL_ID));
			logI("Local Pl_id "+local_pl_id);
			int visibleToCHilde=cursor.getInt(cursor.getColumnIndex(VIDEO_IS_VISIBLE_TO_CHILD));
			logI("Visible to child "+visibleToCHilde);
			int new_add=cursor.getInt(cursor.getColumnIndex(VIDEO_NEW_ADDED));
			logI("Neew Addes "+new_add);
			
			PlaylistItems playlistItems=new PlaylistItems(pl_id, pl_name, v_id, v_url, v_name, v_thumb, v_channel);
			listItems.add(playlistItems);
		}
		cursor.close();
		db.close();
		return listItems;
	}
	public void deleteVideoPL(String pl_id)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete(TABLE_NAME_VODELIST_,VIDEOLIST_IT_P_ID+" = ? ", new String[]{pl_id});
		db.close();
	}
	
	
	
	
	public void addVideoToOtherPlaylist(String v_id,String p_id)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cValues=new  ContentValues();
		db.update(TABLE_NAME_VODELIST_, cValues, " WHERE "+VIDEOLIST_IT_VID_ID+ " ? ", new String[]{v_id});
		db.close();
	}
	
	public boolean isVideoAlreadyAdded(String p_id,String v_id)
	{
		boolean isAdd=false;
		SQLiteDatabase db=this.getReadableDatabase();
		String query="SELECT * FROM "+TABLE_NAME_VODELIST_+ " WHERE "+VIDEOLIST_IT_VID_ID+" = ? "+" AND "+VIDEO_LOCAL_PL_ID+ " = ? ";
		Cursor cursor=db.rawQuery(query, new String[]{v_id,p_id});
		if(cursor.getCount()>0)
		{
			isAdd=true;
		}
		else
		{
			isAdd=false;
		}
		return isAdd;
	}
	public void renamePlaylist(String pid,String p_name)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cValues=new ContentValues();
		cValues.put(PLAYLIST_CLM_NAME, p_name);
		db.update(TABLE_NAME_PLAYLIST, cValues, PLAYLIST_CLM_ID+" = ? ", new String[]{pid});
		db.close();
	}
	public void setChildVisible(String pid,int i)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cValues=new ContentValues();
		cValues.put(PLAYLIST_CLM_VISIBLE_CHILD, i);
		db.update(TABLE_NAME_PLAYLIST, cValues, PLAYLIST_CLM_ID+" = ? ", new String[]{pid});
		db.close();
	}
	public void deletePlaylist(String pl_id)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete(TABLE_NAME_PLAYLIST, PLAYLIST_CLM_ID+" = ? ", new String[]{pl_id});
		db.close();
	}
	public void addPlaylist(String playlist_name ,String playlist_id,String playlist_username,String playlist_no_of_videos,String playlist_thumb,int child,int sync)
	{
		SQLiteDatabase sqliteDB=this.getWritableDatabase();
		ContentValues cValues=new ContentValues();
		cValues.put(PLAYLIST_CLM_ID, playlist_id);
		cValues.put(PLAYLIST_CLM_NAME, playlist_name);
		cValues.put(PLAYLIST_CLM_USERNAME, playlist_username);
		cValues.put(PLAYLIST_CLM_NO, playlist_no_of_videos);
		cValues.put(PLAYLIST_CLM_THUMB, playlist_thumb);
		cValues.put(PLAYLIST_CLM_VISIBLE_CHILD, child);
		cValues.put(PLAYLIST_CLM_SYNC, sync);
		sqliteDB.insert(TABLE_NAME_PLAYLIST, null, cValues);
		sqliteDB.close();
	}
	public ArrayList<FeedDataBeens> getLocalPlaylists()
	{
		ArrayList<FeedDataBeens> list=new ArrayList<FeedDataBeens>();
		SQLiteDatabase sqliteDB=this.getReadableDatabase();
		String query="SELECT * FROM "+TABLE_NAME_PLAYLIST;
		Cursor cursor=sqliteDB.rawQuery(query, null);
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
		{
			String id=cursor.getString(cursor.getColumnIndex(PLAYLIST_CLM_ID));
			String title=cursor.getString(cursor.getColumnIndex(PLAYLIST_CLM_NAME));
			String thumb=cursor.getString(cursor.getColumnIndex(PLAYLIST_CLM_THUMB));
			String user=cursor.getString(cursor.getColumnIndex(PLAYLIST_CLM_USERNAME));
			String no=cursor.getString(cursor.getColumnIndex(PLAYLIST_CLM_NO));
			int sync=cursor.getInt(cursor.getColumnIndex(PLAYLIST_CLM_SYNC));
			int child=cursor.getInt(cursor.getColumnIndex(PLAYLIST_CLM_VISIBLE_CHILD));
			FeedDataBeens beens=new FeedDataBeens(id,title,thumb,user,no,true,sync,child);
			list.add(beens);
		}
		cursor.close();
		sqliteDB.close();
		return list;
	}
	public void updateChildLock(String pl_id,int child)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cValues=new ContentValues();
		cValues.put(PLAYLIST_CLM_VISIBLE_CHILD,child);
		db.update(TABLE_NAME_PLAYLIST, cValues,PLAYLIST_CLM_ID+" = ? ", new String[]{pl_id});
		db.close();
	}
	public void updateSync(String pl_id,int sync)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		ContentValues cValues=new ContentValues();
		cValues.put(PLAYLIST_CLM_SYNC, sync);
		db.update(TABLE_NAME_PLAYLIST, cValues, PLAYLIST_CLM_ID+" = ? ", new String[]{pl_id});
		db.close();
	}
	public boolean isPlaylistAlreadyAdd(String playlisdt_id)
	{
		boolean isAdd=false;
		SQLiteDatabase sqliteDB=this.getWritableDatabase();
		String query="SELECT * FROM "+TABLE_NAME_PLAYLIST+" WHERE "+PLAYLIST_CLM_ID+" = ? ";
		Cursor cursor=sqliteDB.rawQuery(query, new String[]{playlisdt_id});
		if(cursor.getCount()>0)
		{
			logI("Playlist Already Add");
			isAdd=true;
		}
		else
		{
			logI("Playlist Already not  Add");
			isAdd=false;
		}
		cursor.close();
		sqliteDB.close();
		return isAdd;
	}
	
	private void logI(String msg)
	{
		if(BuildConfig.DEBUG)
		{
			Log.i("PlaylistDB ",msg);
		}
	}
	
}
