package com.edu.tube;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;


public class Image_Gallery extends Activity {
	// Log tag
	Matrix matrix = new Matrix();
	 Matrix savedMatrix = new Matrix();
	 PointF startPoint = new PointF();
	 PointF midPoint = new PointF();
	 float oldDist = 1f;
	 static final int NONE = 0;
	 static final int DRAG = 1;
	 static final int ZOOM = 2;
	 int mode = NONE;

	 
	 public static ImageView imgv;




	// Movies json url
//	private static final String url = "http://api.androidhive.info/json/movies.json";
	//private ProgressDialog pDialog;
///	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;
	//private GallerylistAdapter adapter;

	public int[] listitem;


	
	private Button back;
	private LinearLayout back_ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		/*listitem=new int[10];
		listitem[0]=R.drawable.demo0;
		listitem[1]=R.drawable.demo1;
		listitem[2]=R.drawable.demo2;
		listitem[3]=R.drawable.demo3;
		listitem[4]=R.drawable.demo4;
		listitem[5]=R.drawable.demo5;
		listitem[6]=R.drawable.demo6;
		listitem[7]=R.drawable.demo7;
		listitem[8]=R.drawable.demo8;
		listitem[9]=R.drawable.demo9;*/
	setContentView(R.layout.galleryview);
	
	ImageView demo0=(ImageView)findViewById(R.id.imgview0);
	ImageView demo1=(ImageView)findViewById(R.id.imgview1);
	ImageView demo2=(ImageView)findViewById(R.id.imgview2);
	ImageView demo3=(ImageView)findViewById(R.id.imgview3);
	ImageView demo4=(ImageView)findViewById(R.id.imgview4);
	ImageView demo5=(ImageView)findViewById(R.id.imgview5);
	ImageView demo6=(ImageView)findViewById(R.id.imgview6);
	ImageView demo7=(ImageView)findViewById(R.id.imgview7);
	ImageView demo8=(ImageView)findViewById(R.id.imgview8);
	ImageView demo9=(ImageView)findViewById(R.id.imgview9);
	back=(Button) findViewById(R.id.back);
	back_ll=(LinearLayout) findViewById(R.id.back_ll);
	
	back_ll.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			onBackPressed();
		}
	});
back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			onBackPressed();
		}
	});
	
	
	demo0.setOnClickListener(new View.OnClickListener() {
		
		@Override
		 public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
			intent.putExtra("value", R.drawable.demo0);
			startActivity(intent);
		}});
	demo1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		 public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
			intent.putExtra("value", R.drawable.demo1);
			startActivity(intent);
			
		}});
	demo2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		 public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
			intent.putExtra("value", R.drawable.demo2);
			startActivity(intent);
			
		}});
	demo3.setOnClickListener(new View.OnClickListener() {
		
		@Override
		 public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
			intent.putExtra("value", R.drawable.demo3);
			startActivity(intent);
			
		}});
	demo4.setOnClickListener(new View.OnClickListener() {
		
		@Override
		 public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
			intent.putExtra("value", R.drawable.demo4);
			startActivity(intent);
			
		}});
	demo5.setOnClickListener(new View.OnClickListener() {
		
		@Override
		 public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
			intent.putExtra("value", R.drawable.demo5);
			startActivity(intent);
			
		}});
	demo6.setOnClickListener(new View.OnClickListener() {
		
		@Override
		 public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
			intent.putExtra("value", R.drawable.demo6);
			startActivity(intent);
			
		}});
	demo7.setOnClickListener(new View.OnClickListener() {
		
		@Override
		 public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
			intent.putExtra("value", R.drawable.demo7);
			startActivity(intent);
			
		}});
	demo8.setOnClickListener(new View.OnClickListener() {
		
		@Override
		 public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
			intent.putExtra("value", R.drawable.demo8);
			startActivity(intent);
			
		}});
	demo9.setOnClickListener(new View.OnClickListener() {
		
		@Override
		 public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(),ImageEditActivity.class);
			intent.putExtra("value", R.drawable.demo9);
			startActivity(intent);
			
		}});

	/*	demo0.setOnTouchListener(new View.OnTouchListener() {
		
		@Override
		   public boolean onTouch(View v, MotionEvent event) {

		    ImageView view = (ImageView) v;
		    System.out.println("matrix=" + savedMatrix.toString());
		    switch (event.getAction() & MotionEvent.ACTION_MASK) {
		    case MotionEvent.ACTION_DOWN:

		     savedMatrix.set(matrix);
		     startPoint.set(event.getX(), event.getY());
		     mode = DRAG;
		     break;

		    case MotionEvent.ACTION_POINTER_DOWN:

		     oldDist = spacing(event);

		     if (oldDist > 10f) {
		      savedMatrix.set(matrix);
		      midPoint(midPoint, event);
		      mode = ZOOM;
		     }
		     break;

		    case MotionEvent.ACTION_UP:

		    case MotionEvent.ACTION_POINTER_UP:
		     mode = NONE;

		     break;

		    case MotionEvent.ACTION_MOVE:
		     if (mode == DRAG) {
		      matrix.set(savedMatrix);
		      matrix.postTranslate(event.getX() - startPoint.x,
		        event.getY() - startPoint.y);
		     } else if (mode == ZOOM) {
		      float newDist = spacing(event);
		      if (newDist > 10f) {
		       matrix.set(savedMatrix);
		       float scale = newDist / oldDist;
		       matrix.postScale(scale, scale, midPoint.x, midPoint.y);
		      }
		     }
		     break;

		    }
		    view.setImageMatrix(matrix);

		    return true;
		   }

		   @SuppressLint("FloatMath")
		   private float spacing(MotionEvent event) {
		    float x = event.getX(0) - event.getX(1);
		    float y = event.getY(0) - event.getY(1);
		    return FloatMath.sqrt(x * x + y * y);
		   }

		   private void midPoint(PointF point, MotionEvent event) {
		    float x = event.getX(0) + event.getX(1);
		    float y = event.getY(0) + event.getY(1);
		    point.set(x / 2, y / 2);
		   }
		  });*/


		

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            /*Intent intent = new Intent(this, MainActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);*/
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
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
