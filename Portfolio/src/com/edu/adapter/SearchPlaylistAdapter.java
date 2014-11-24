package com.edu.adapter;

import java.util.ArrayList;

import com.edu.been.FeedDataBeens;
import com.edu.loader.ImageLoader;
import com.edu.tube.BuildConfig;
import com.edu.tube.R;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SearchPlaylistAdapter extends BaseAdapter 
{
	
	private Context context;
	private ArrayList<FeedDataBeens> dataList;
	public ImageLoader imageLoader;
	public SearchPlaylistAdapter(Context context, ArrayList<FeedDataBeens> dataList) 
	{
		this.context=context;
		this.dataList=dataList;
		imageLoader=new ImageLoader(context);
	}
	
	
	@Override
	public int getCount() 
	{
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public Object getItem(int position) 
	{
		// TODO Auto-generated method stub
		return dataList.get(position);
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
		LayoutInflater inflater;
		ViewHolder holder;
		if(view==null)
		{
			inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view=inflater.inflate(R.layout.search_pl_row, null);
			holder=new ViewHolder();
			holder.name=(TextView) view.findViewById(R.id.pl_name);
			holder.size=(TextView) view.findViewById(R.id.pl_size);
			holder.thumb=(ImageView) view.findViewById(R.id.thumb_pl);
			
			view.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) view.getTag();
		}
		
		holder.name.setText(dataList.get(position).getTitle());
		holder.size.setText(dataList.get(position).getSize());
		imageLoader.DisplayImage(dataList.get(position).getThumb(), holder.thumb);
		logE(dataList.get(position).getTitle());
		logE(dataList.get(position).getThumb());
		holder.name.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}
	class ViewHolder
	{
		TextView name;
		TextView size;
		ImageView thumb;
	}
	private void logE(String msg)
	{
		if(BuildConfig.DEBUG)
		{
			Log.e("sEARCHpLAYLISTaDAPTER",msg);
		}
	}
}
