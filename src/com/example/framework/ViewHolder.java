package com.example.framework;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ListView，GridView通用的ViewHolder, 调用{@link #get(Context, View, ViewGroup, int)}
 * 可以生成一个ViewHolder
 * 
 * @author 瑞克
 *
 */
public final class ViewHolder {
	/**
	 * 用来存储Item上面的View
	 */
	private final SparseArray<View> views;
	private final Context context;
	private View convertView;

	private ViewHolder(Context context, ViewGroup parent, int layoutId) {
		this.views = new SparseArray<View>();
		this.context = context;
		convertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		convertView.setTag(this);
	}

	/**
	 * 
	 * 
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @return
	 */
	public static ViewHolder get(Context context, View convertView,
			ViewGroup parent, int layoutId) {
		if (null == convertView) {
			return new ViewHolder(context, parent, layoutId);
		}
		return (ViewHolder) convertView.getTag();

	}

	/**
	 * 获取ListView, GridView的convertView
	 * 
	 * @return
	 */
	public View getView() {
		return convertView;
	}

	/**
	 * 给Item的TextView设置text
	 * 
	 * @param viewId
	 * @param text
	 */
	public void setText(int viewId, CharSequence text) {
		TextView view = getView(viewId);

		System.out.println("view = " + view);

		view.setText(text);
	}

	/**
	 * 给Item的TextView设置text
	 * 
	 * @param viewId
	 * @param resId
	 */
	public void setText(int viewId, int resId) {
		TextView view = getView(viewId);
		view.setText(context.getResources().getString(resId));
	}

	/**
	 * 给Item的ImageView设置图片
	 * 
	 * @param viewId
	 * @param imageResId
	 */
	public void setImageResource(int viewId, int imageResId) {
		ImageView view = getView(viewId);
		view.setImageResource(imageResId);
	}

	/**
	 * 设置Item上View的背景颜色
	 * 
	 * @param viewId
	 * @param color
	 */
	public void setBackgroundColor(int viewId, int color) {
		View view = getView(viewId);
		view.setBackgroundColor(color);
	}

	/**
	 * 设置Item上View的背景资源
	 * 
	 * @param viewId
	 * @param backgroundRes
	 */
	public void setBackgroundRes(int viewId, int backgroundRes) {
		View view = getView(viewId);
		view.setBackgroundResource(backgroundRes);
	}

	/**
	 * 设置Item上TextView的字体颜色
	 * 
	 * @param viewId
	 * @param textColor
	 */
	public void setTextColor(int viewId, int textColor) {
		TextView view = getView(viewId);
		view.setTextColor(textColor);
	}

	/**
	 * 设置Item上TextView的字体资源
	 * 
	 * @param viewId
	 * @param textColorRes
	 */
	public void setTextColorRes(int viewId, int textColorRes) {
		TextView view = getView(viewId);
		view.setTextColor(context.getResources().getColor(textColorRes));
	}

	/**
	 * 设置Item上ImageView的图片资源
	 * 
	 * @param viewId
	 * @param drawable
	 */
	public void setImageDrawable(int viewId, Drawable drawable) {
		ImageView view = getView(viewId);
		view.setImageDrawable(drawable);
	}

	/**
	 * 通过viewId获取item上面的View, 使用了SparseArray来缓存
	 * 
	 * @param viewId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		View view = views.get(viewId);
		if (null == view) {
			view = findViewById(viewId);
			views.put(viewId, view);
		}
		return (T) view;
	}
	
	
	/**
	 * 
	 * @param viewId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T findViewById(int viewId){
		return (T) convertView.findViewById(viewId);
	}


}
