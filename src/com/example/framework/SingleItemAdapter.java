package com.example.framework;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 如果ListView, GridView item的布局是单一的, 继承此类,
 * 
 * @author 瑞克
 *
 * @param <T>
 */
public abstract class SingleItemAdapter<T> extends ViewHolderAdapter<T>{
	private int layoutId;

	public SingleItemAdapter(Context context, List<T> data, int layoutId) {
		super(context, data);
		this.layoutId = layoutId;
	}

	@Override
	public ViewHolder onCreateViewHolder(int position, View convertView,
			ViewGroup parent) {
		return ViewHolder.get(context, convertView, parent, layoutId);
	}
	
	
	@Override
	public abstract void onBindViewHolder(ViewHolder viewHolder, T item, int position) ;

}
