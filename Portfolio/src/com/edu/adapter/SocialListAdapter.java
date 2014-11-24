package com.edu.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.edu.app.AppController;
import com.edu.model.Movie;
import com.edu.tube.About;
import com.edu.tube.Contact;
import com.edu.tube.Image_Gallery;
import com.edu.tube.R;
import com.edu.tube.Searchvideo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SocialListAdapter extends BaseAdapter
{

	private Activity activity;
	private LayoutInflater inflater;
	private List<Movie> movieItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public SocialListAdapter(Activity activity, List<Movie> movieItems) {
		this.activity = activity;
		this.movieItems = movieItems;
	}

	@Override
	public int getCount() 
	{
		return movieItems.size();
	}

	@Override
	public Object getItem(int location) {
		return movieItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
Log.e("position", ""+position);

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.social_row, null);

		//if (imageLoader == null)
			//imageLoader = AppController.getInstance().getImageLoader();
		ImageView thumbNail = (ImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView rating = (TextView) convertView.findViewById(R.id.text);
	/*	TextView genre = (TextView) convertView.findViewById(R.id.genre);
		TextView year = (TextView) convertView.findViewById(R.id.releaseYear);*/

		// getting movie data for the row
		Movie m = movieItems.get(position);
		// thumbnail image
	if(position==0){	thumbNail.setBackgroundResource(R.drawable.facebook_icon);}
	if(position==1){	thumbNail.setBackgroundResource(R.drawable.twitter_icon);}
	if(position==2){	thumbNail.setBackgroundResource(R.drawable.google_plus_icon);}
	if(position==3){	thumbNail.setBackgroundResource(R.drawable.instagram_icon);}
	if(position==4){	thumbNail.setBackgroundResource(R.drawable.linkedin_icon);}
	
		// title
		title.setText(m.getTitle());
		
		// rating
		rating.setText(m.getRating());
		
		// genre
		//String genreStr = "";
		/*for (String str : m.getGenre()) {
			genreStr += str + ", ";
		}*/
		//genreStr = genreStr.length() > 0 ? genreStr.substring(0,
		//		genreStr.length() - 2) : genreStr;
	//	genre.setText(genreStr);
		
		// release year
	//	year.setText(String.valueOf(m.getYear()));
		final int pos=position;
convertView.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Log.e("onclick",""+pos);
		if(pos==0){
			Uri uri = Uri.parse("http://www.facebook.com");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			activity.startActivity(intent);
		}
		if(pos==1){
			Uri uri = Uri.parse("http://www.m.twitter.com");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			activity.startActivity(intent);
		}
		if(pos==2){
			Uri uri = Uri.parse("http://www.google.com");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			activity.startActivity(intent);
		}
		if(pos==3){
			Uri uri = Uri.parse("http://www.instagram.com");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			activity.startActivity(intent);
		}
		if(pos==4){
			Uri uri = Uri.parse("http://www.linkedin.com");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			activity.startActivity(intent);
		}
	}
});
		return convertView;
	}


}
