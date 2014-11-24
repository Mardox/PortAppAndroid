package com.edu.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Session 
{

	SharedPreferences shared;
	Editor editor;
	
	public static final String USER_PLAYLIST_COUNT="user_pl_count";
	
	public Session(Context context)
	{
		shared=context.getSharedPreferences("edu_tube_session", Context.MODE_PRIVATE);
		editor=shared.edit();
	}
	
	public void saveUserPlCount(int count)
	{
		editor.putInt(USER_PLAYLIST_COUNT, count);
		editor.commit();
	}
	public int getUserPlCount()
	{
		return shared.getInt(USER_PLAYLIST_COUNT, 0);
	}
}
