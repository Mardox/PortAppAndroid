package com.edu.been;

public class PlaylistItems
{
	public String playlist_id;
	public String playlist_name;
	public String v_id;
	public String v_url;
	public String v_name;
	public String thumb;
	public String channel;
	
	public PlaylistItems(String playlist_id, String playlist_name, String v_id,String v_url,String  v_name, String thumb ,String channel)
	{
		this.playlist_id=playlist_id;
		this.playlist_name=playlist_name;
		this.v_id=v_id;
		this.v_url=v_url;
		this.v_name=v_name;
		this.thumb=thumb;
		this.channel=channel;
	}
	public PlaylistItems(String v_id, String thumb,String vName )
	{
		this.v_id=v_id;
		this.thumb=thumb;
		this.v_name=vName;
	}
}
