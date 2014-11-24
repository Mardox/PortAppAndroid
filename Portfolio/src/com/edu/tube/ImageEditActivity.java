package com.edu.tube;


import com.edu.util.TouchImageView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ImageEditActivity extends Activity 
{
	private TouchImageView imageView;
	private LinearLayout back_ll;
	private Button back;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_image);
		imageView=(TouchImageView) findViewById(R.id.image_view_act);
		
		back_ll=(LinearLayout) findViewById(R.id.back_ll);
		back=(Button) findViewById(R.id.back);
		
		back_ll.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		back.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		Intent intent=getIntent();
		int id=intent.getIntExtra("value",0);
		//imageView.setBackgroundResource(id);
		
		Bitmap bitnap=BitmapFactory.decodeResource(getResources(), id);
		imageView.setImageBitmap(bitnap);
		
		
	}
}
