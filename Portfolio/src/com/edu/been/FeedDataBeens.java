package com.edu.been;

import android.R.bool;

public class FeedDataBeens 
{
	public String  yt_id;
	public String yt_title;
	public String yt_thumb;
	public String yt_url;
	public String yt_author;
	public String size;
	public boolean isAdded;
	public int sync;
	public int child;
	
	
	
	public FeedDataBeens(String yt_id,String yt_title,String yt_thumb,String yt_author,String size,boolean isAdded,int sync,int child)
	{
		this.yt_id=yt_id;
		this.yt_thumb=yt_thumb;
		this.yt_title=yt_title;
		this.yt_author=yt_author;
		this.size=size;
		this.isAdded=isAdded;
		this.sync=sync;
		this.child=child;
	}
	
	
	public void setSync(int i)
	{
		this.sync=i;
	}
	public int getSync()
	{
		return this.sync;
	}
	
	
	
	public void setCHild(int i)
	{
		this.child=i;
	}
	public int getChild()
	{
		return this.child;
	}
	
	public void setAdded(boolean isAdd)
	{
		isAdded=isAdd;
	}
	public boolean isAdded()
	{
		return isAdded;
	}
	
	
	public void setAuthor(String author)
	{
		yt_author=author;
	}
	public String getAuthor()
	{
		return yt_author;
	}
	public void setId(String id)
	{
		yt_id=id;
	}
	public String getID()
	{
		return yt_id;
	}
	public void setThumbe(String thumb)
	{
		yt_thumb=thumb;
	}
	public String getThumb()
	{
		return yt_thumb;
	}
	public void setTitle(String title)
	{
		yt_title=title;
	}
	public String getTitle()
	{
		return yt_title;
	}
	public String getSize()
	{
		return size;
	}
	public void setString(String size1)
	{
		size=size1;
	}
}
