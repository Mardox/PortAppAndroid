package com.edu.tube;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Contact extends Activity 
{
	private Button back;
	private LinearLayout back_ll;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactus);
		back=(Button) findViewById(R.id.back);
		back_ll=(LinearLayout) findViewById(R.id.back_ll);
		back.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		back_ll.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
