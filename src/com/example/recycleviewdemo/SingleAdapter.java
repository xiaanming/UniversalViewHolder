package com.example.recycleviewdemo;

import java.util.List;

import android.content.Context;

import com.example.framework.SingleItemAdapter;
import com.example.framework.ViewHolder;
public class SingleAdapter extends SingleItemAdapter<String> {

	public SingleAdapter(Context context, List<String> data, int layoutId) {
		super(context, data, layoutId);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, String item, int position) {
		viewHolder.setText(R.id.textView, item);
	}

}
