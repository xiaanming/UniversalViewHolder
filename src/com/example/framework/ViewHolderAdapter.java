package com.example.framework;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 当我们在使用ListView, GridView的时候，为了缓存item，我们需要使用ViewHolder,
 * 这使得我们每写一个Adapter都需要定义一个ViewHolder, 为了简化这种过程，这里定义了
 * 一个通用的{@link #com.taobao.android.big.adaptet.framework.ViewHolder}}， 如果ListView
 * GridView的为单一item ，继承{@link #com.taobao.android.big.adaptet.framework.SingleItemAdapter}
 * 就可以了，如果ListView,GridView有多个item,则需要继承此类，并且还要重写{@link #getViewTypeCount()} 
 * 和{@link #getItemViewType(int)}
 * 
 * 
 * 
 * @author 瑞克
 *
 * @param <T>
 */
public abstract class ViewHolderAdapter<T> extends AbsBaseAdapter<T> {
	protected Context context;
	
	public ViewHolderAdapter(Context context, List<T> data){
		super(data);
		this.context = context;
	}
	

	@Override
	public final View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = onCreateViewHolder(position, convertView, parent);
		T item = getItem(position);
		onBindViewHolder(viewHolder, item, position);
		return viewHolder.getView();
	}
	
	/**
	 * 此方法更新界面数据，利用ViewHolder的getView(int viewId)方法获取某个View,然后在更新View
	 * @param viewHolder
	 * @param item
	 * @param position
	 */
	public abstract void onBindViewHolder(ViewHolder viewHolder, T item, int position);
	
	/**
	 * 创建ViewHolder, 调用{@link #com.example.framework.ViewHolder}}的get()方法
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 */
	public abstract ViewHolder onCreateViewHolder(int position, View convertView, ViewGroup parent);
	
	

}
