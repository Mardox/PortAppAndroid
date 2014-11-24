package com.edu.tube;



import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.edu.adapter.CustomListAdapter;
import com.edu.app.AppController;
import com.edu.model.Movie;

public class ParentsActivity extends Activity {
	// Log tag
	

	// Movies json url
//	private static final String url = "http://api.androidhive.info/json/movies.json";
	//private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;
	private CustomListAdapter adapter;

	private String[] menuitem;

	private String[] menutext;

	private String menuicon;
	

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("beforemessage", "1");
		super.onCreate(savedInstanceState);
		Log.e("beforemessage", "2");
		setContentView(R.layout.activity_main);
		Log.e("beforemessage", "3");
		menuitem = getResources().getStringArray(R.array.menu_item);
		Log.e("beforemessage", "4");
		menutext=getResources().getStringArray(R.array.menu_text);
		Log.e("beforemessage", "5");
		//menuicon=getResources().getString(R.array.menu_icon);
		Log.e("beforemessage", "6");
		listView = (ListView) findViewById(R.id.list);
	Log.e("messeg", "1");	

	//	pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
	//	pDialog.setMessage("Loading...");
	//	pDialog.show();

		// changing action bar color
		//getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));
		Log.e("messeg", "2");
		// Creating volley request obj
/*		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();
*/
						// Parsing json
						for (int i = 0; i < 5; i++) {
							try {
								Log.e("messegfor", ""+i);
//String mess = getResources().getString(R.string.mess_1);
								//JSONObject obj = response.getJSONObject(i);
								Movie movie = new Movie();
								movie.setTitle(menuitem[i]);
							movie.setThumbnailUrl("drawable://" + R.drawable.galleryicon);
								movie.setRating(menutext[i]);
								//movie.setYear(obj.getInt("releaseYear"));

								// Genre is json array
							//	JSONArray genreArry = obj.getJSONArray("genre");
							//	ArrayList<String> genre = new ArrayList<String>();
							//	for (int j = 0; j < genreArry.length(); j++) {
							//		genre.add((String) genreArry.get(j));
							//	}
							//	movie.setGenre(genre);

								// adding movie to movies array
								movieList.add(movie);

							} catch (Exception e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						//adapter.notifyDataSetChanged();
/*					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});*/

		// Adding request to request queue
		//AppController.getInstance().addToRequestQueue(movieReq);
						adapter = new CustomListAdapter(this, movieList);
						Log.e("messeg", ""+3);
						listView.setAdapter(adapter);
						Log.e("messeg", ""+4);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		/*if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
