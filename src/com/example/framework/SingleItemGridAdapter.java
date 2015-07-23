package com.example.framework;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 使用ListView实现GridView效果，如果item是单一的实现此类
 * @author anming.xam
 *
 * @param <T>
 */
public abstract class SingleItemGridAdapter<T> extends ViewHolderGridAdapter<T> {
	private int layoutId;

	public SingleItemGridAdapter(Context context, List<T> data, int layoutId) {
		super(context, data);
		this.layoutId = layoutId;
	}

	@Override
	public abstract void onBindViewHolder(ViewHolder viewHolder, T item, int position); 

	@Override
	public ViewHolder onCreateViewHolder(int position, View convertView,
			ViewGroup parent) {
		return ViewHolder.get(context, convertView, parent, layoutId);
	}

}
