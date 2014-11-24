package com.edu.adapter;

import android.R;
import android.R.integer;
import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class Image_Adapter extends ArrayAdapter<Item> {

	private Context context;
	private Item[] items;

	public Image_Adapter(Context context, int textViewResourceId, Item[] items) {
		super(context, textViewResourceId, items);
		 this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
		//	LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//	v = vi.inflate(R.layout.items_list_item, null);
		}

		Item it = items[position];
		if (it != null) {
		// iv = (ImageView) v.findViewById(	R.layout.img);
		//	if (iv != null) {
		//		iv.setImageDrawable(it.getImage());
			}
	//	}

		return v;
	}
}
