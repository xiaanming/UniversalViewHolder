package com.example.recycleviewdemo;

import java.util.List;

import android.content.Context;

import com.example.framework.SingleItemGridAdapter;
import com.example.framework.ViewHolder;

public class SingleGridAdapter extends SingleItemGridAdapter<String> {


	public SingleGridAdapter(Context context, List<String> data, int layoutId) {
		super(context, data, layoutId);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, String item, int position) {
		viewHolder.setText(R.id.textView, item);
	}

}
