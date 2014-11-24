package com.edu.adapter;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.edu.app.AppController;
import com.edu.model.Movie;
import com.edu.tube.About;
import com.edu.tube.Contact;
import com.edu.tube.Image_Gallery;
import com.edu.tube.R;
import com.edu.tube.Searchvideo;
import com.edu.tube.SocialActivity;
import com.edu.tube.VideoListActivity;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Movie> movieItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Movie> movieItems) {
		this.activity = activity;
		this.movieItems = movieItems;
	}

	@Override
	public int getCount() {
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
			convertView = inflater.inflate(R.layout.list_row, null);

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
	if(position==0){	thumbNail.setBackgroundResource(R.drawable.galleryicon);}
	if(position==1){	thumbNail.setBackgroundResource(R.drawable.videosicon);}
	if(position==2){	thumbNail.setBackgroundResource(R.drawable.abouticon);}
	if(position==3){	thumbNail.setBackgroundResource(R.drawable.contacticon);}
	if(position==4){	thumbNail.setBackgroundResource(R.drawable.socialicon);}
	
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
			Intent gallery_intent = new Intent(activity.getApplicationContext(),Image_Gallery.class);
			activity.startActivity(gallery_intent);
		}
		if(pos==1){
			Intent gallery_intent = new Intent(activity.getApplicationContext(),VideoListActivity.class);
			activity.startActivity(gallery_intent);
		}
		if(pos==2){
			Intent gallery_intent = new Intent(activity.getApplicationContext(),About.class);
			activity.startActivity(gallery_intent);
		}
		if(pos==3){
			Intent gallery_intent = new Intent(activity.getApplicationContext(),Contact.class);
			activity.startActivity(gallery_intent);
		}
		if(pos==4){
			Intent gallery_intent = new Intent(activity.getApplicationContext(),SocialActivity.class);
			activity.startActivity(gallery_intent);
		}
	}
});
		return convertView;
	}

	
}