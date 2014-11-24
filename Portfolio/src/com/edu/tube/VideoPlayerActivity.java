package com.edu.tube;

import com.edu.other.Singleton;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class VideoPlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
{
	public static final String API_KEY = "AIzaSyBXRlGpOMAaQusVg-7VYcNqGvyho8BgsWY";//"AIzaSyCe6tORd9Ch4lx-9Ku5SQ476uS9OtZYsWA";
	private YouTubePlayerView youTubePlayerView;
	private String video_id;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.video_player);
		youTubePlayerView=(YouTubePlayerView) findViewById(R.id.youtube_player);
		youTubePlayerView.initialize(API_KEY, this);
		intent=getIntent();
		video_id=intent.getStringExtra(Singleton.VIDEO_ID);
		
	}
	@Override
	public void onInitializationFailure(Provider arg0,YouTubeInitializationResult arg1) 
	{
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(),"onInitializationFailure()",Toast.LENGTH_LONG).show();
	}
	@Override
	public void onInitializationSuccess(Provider provider, YouTubePlayer player,boolean wasRestored) 
	{
		// TODO Auto-generated method stub
		if (!wasRestored) 
		{
			player.cueVideo(video_id);
		}
	}
}
