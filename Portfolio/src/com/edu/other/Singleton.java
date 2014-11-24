package com.edu.other;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.edu.been.FeedDataBeens;
import com.edu.been.PlaylistItems;

import android.graphics.Typeface;


public class Singleton {
	public static String keyword = "text";
	public static ArrayList<PlaylistItems> pl_Items = new ArrayList<PlaylistItems>();
	public static ArrayList<FeedDataBeens> playList = new ArrayList<FeedDataBeens>();
	public static boolean hasMOre;
	public static boolean hasMorePlaylist;
	public static String noMore;
	public static String no = "no";
	public static String yes = "yes";
	public static int signed_in = 0;
	public static int passcode = 0;
	public static String user = "";
	public static int active = 0;
	public static int edit_clicked = 1;
	public static int logs_clicked = 1;
	public static String currentDate = java.text.DateFormat
			.getDateTimeInstance().format(Calendar.getInstance().getTime());
	public static String SEARCH_KEY = "search_key";
	public static String SEARCH_USER = "search_user";
	public static String SEARCH_USER_ID = "search_user_id";
	public static String lastThumb;
	public static String lastNoOfVideos;
	public static boolean isItemChecked;
	public static int currentItemPosition;
	public static String lastPlaylistName = null;
	public static String lastKeyword = null;
	public static boolean isUser;
	public static Typeface tf;
	public static String frnd_search_key="frnd_search_key";
	public static String  frnd_host_key="frnd_host_key";
	public static HashMap<String, String> pl_IsstHas=new HashMap<String, String>();
	public static String current_plailist_id;
	
	
	public static final String playlist_id="playlist_id";
	public static final String playlist_name="playlist_name";
	public static final String playlist_username="username";
	public static final String playlist_count="count";
	public static final String playlist_thumb="thumb";
	public static final String playlist_child="child";
	public static final String playlist_sync="sync";
	
	public static final String bundle = "bundle";
	
	public static int userPlaylist=0;
	
	public static String CREATE_PLAYLIST="create_playlist";
	public static String RENAME_PLAYLIST="rename_playlist";
	
	public static final String VIDEO_ID="video_id";
	
}
