package com.edu.adapter;

import java.util.ArrayList;

import com.edu.been.FeedDataBeens;
import com.edu.loader.ImageLoader;
import com.edu.tube.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
	public View getView(int position, View convertView, ViewGroup parent) 
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
			view.setTag(vHolder);
		}
		else
		{
			vHolder=(ViewHolder) view.getTag();
		}
		vHolder.name.setText(beensList.get(position).yt_title);
		vHolder.size.setText(beensList.get(position).size);
		imageLoader.DisplayImage( beensList.get(position).yt_thumb,vHolder.imageView);
		return view;
	}
	class ViewHolder
	{
		TextView name;
		TextView size;
		Button rename; 
		ImageView imageView;
	}
}
