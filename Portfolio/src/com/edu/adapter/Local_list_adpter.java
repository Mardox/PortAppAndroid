package com.edu.adapter;

import java.util.ArrayList;

import com.edu.been.FeedDataBeens;
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

public class Local_list_adpter extends BaseAdapter 
{
	
	private ArrayList<FeedDataBeens> listItems=new ArrayList<FeedDataBeens>();
	public Context context;
	public ImageLoader imageLoader;
	
	public Local_list_adpter(Context context,ArrayList<FeedDataBeens> listItems )
	{
		this.context=context;
		this.listItems=listItems;
		imageLoader=new ImageLoader(context);
	}
	
	@Override
	public int getCount() 
	{
		// TODO Auto-generated method stub
		return listItems.size();
	}

	@Override
	public Object getItem(int position) 
	{
		// TODO Auto-generated method stub
		return listItems.get(position);
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
		ViewHolder holder=new ViewHolder();
		LayoutInflater inflater;
		if(view==null)
		{
			inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view=inflater.inflate(R.layout.playlist_row, null);
			holder.name=(TextView) view.findViewById(R.id.pl_name);
			holder.image=(ImageView) view.findViewById(R.id.image);
			holder.count=(TextView) view.findViewById(R.id.pl_count);
			
			view.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) view.getTag();
		}
		holder.name.setText(listItems.get(position).getTitle());
		imageLoader.DisplayImage(listItems.get(position).yt_thumb, holder.image);
		holder.count.setText(listItems.get(position).size);
		return view;
	}
	
	class ViewHolder 
	{
		TextView name;
		TextView count;
		ImageView image;
	}
	
	
	

}
