package com.edu.adapter;

import java.util.ArrayList;

import com.edu.been.PlaylistItems;
import com.edu.loader.ImageLoader;
import com.edu.tube.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
	public View getView(int position, View convertView, ViewGroup parent) 
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
			view.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) view.getTag();
		}
		holder.videoName.setText(plItems.get(position).playlist_name);
		imageLoader.DisplayImage(plItems.get(position).thumb, holder.imageThumb);
		return view;
	}
	
	class ViewHolder
	{
		TextView videoName;
		ImageView imageThumb;
	}

}
