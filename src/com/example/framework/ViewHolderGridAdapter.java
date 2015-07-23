package com.example.framework;

import java.util.List;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * 使用ListView实现GridView的效果, 使用参照{@link #com.example.framework.ViewHolderAdapter}
 * 
 * @author anming.xam
 *
 * @param <T>
 */
public abstract class ViewHolderGridAdapter<T> extends AbsBaseAdapter<T> {
	protected int mNumColumns = 1;
	private OnItemClickListener OnItemClickListener;
	private OnItemLongClickListener onItemLongClickListener;
	protected Context context;
	
	protected int horizontalSpacing = 10;
	protected int verticalSpacing = 10;
	private DisplayMetrics mDisplayMetrics;
	

	public ViewHolderGridAdapter(Context context, List<T> data) {
		super(data);
		this.context = context;
		mDisplayMetrics = context.getResources().getDisplayMetrics();
		this.horizontalSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				horizontalSpacing, mDisplayMetrics);
		this.verticalSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				verticalSpacing, mDisplayMetrics);
	}



	public int getVerticalSpacing() {
		return verticalSpacing;
	}

	/**
	 * 设置item的垂直间距， 单位是dip
	 * 
	 * @param verticalSpacing
	 */
	public void setVerticalSpacing(int verticalSpacing) {
		this.verticalSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				verticalSpacing, mDisplayMetrics);
		notifyDataSetChanged();
	}

	public int getHorizontalSpacing() {
		return horizontalSpacing;
	}

	/**
	 * 设置item的水平间距， 单位是dip
	 * @param horizontalSpacing
	 */
	public void setHorizontalSpacing(int horizontalSpacing) {
		this.horizontalSpacing =  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				horizontalSpacing, mDisplayMetrics);
		notifyDataSetChanged();
	}

	/**
	 * 设置item的点击监听
	 * @param listener
	 */
	public void setOnItemClickListener(OnItemClickListener listener) {
		OnItemClickListener = listener;
	}
	
	/**
	 * 设置item的长按事件
	 * @param onItemLongClickListener
	 */
	public void setOnItemLongClickListener(
			OnItemLongClickListener onItemLongClickListener) {
		this.onItemLongClickListener = onItemLongClickListener;
	}

	public final int getNumColumns() {
		return mNumColumns;
	}

	public final void setNumColumns(int numColumns) {
		mNumColumns = numColumns;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return (int) Math.ceil(getItemCount() * 1f / getNumColumns());
	}

	/**
	 * 
	 * @return
	 */
	public final int getItemCount(){
		return data.size();
	};
	

	@Override
	public final View getView(int position, View view, ViewGroup viewGroup) {
		LinearLayout layout;
		int columnWidth = 0;
		if (viewGroup != null) {
			columnWidth = (viewGroup.getWidth() -(mNumColumns - 1) * horizontalSpacing) / mNumColumns;
		} else if (view != null) {
			columnWidth = (view.getWidth() - (mNumColumns - 1) * horizontalSpacing) / mNumColumns;
		}

		// Make it be rows of the number of columns
		if (view == null) {
			// This is items view
			layout = createItemRow(position, viewGroup, null, columnWidth);
		} else {
			layout = (LinearLayout) view;
			updateItemRow(position, viewGroup, layout, columnWidth);
		}
		return layout;
	}

	protected LinearLayout createItemRow(int position, ViewGroup viewGroup,
			LinearLayout layout, int columnWidth) {
		if (layout == null) {
			layout = new LinearLayout(context);
		}
		
		layout.setOrientation(LinearLayout.HORIZONTAL);

		// Now add the sub views to it
		for (int i = 0; i < mNumColumns; i++) {
			int currentPos = position * mNumColumns + i;
			// Get the new View
			View insideView;
			if (currentPos < getItemCount()) {
				insideView = getItemView(currentPos, null, viewGroup);
				insideView.setVisibility(View.VISIBLE);
				View theView = getItemView(currentPos, insideView, viewGroup);
				theView.setOnClickListener(new ListItemClickListener(currentPos));
				theView.setOnLongClickListener(new ListItemLongClickListener(currentPos));
			} else {
				insideView = getItemView(getItemCount() - 1, null, viewGroup);
				insideView.setVisibility(View.INVISIBLE);
			}
			layout.addView(insideView);
			// Set the width of this column
			
			setLayoutParam(insideView, columnWidth);
		}
		return layout;
	}
	

	private void updateItemRow(int position, ViewGroup viewGroup,
			LinearLayout layout, int columnWidth) {
		for (int i = 0; i < mNumColumns; i++) {
			int currentPos = position * mNumColumns + i;
			View insideView = layout.getChildAt(i);
			// If there are less views than objects. add a view here
			if (insideView == null) {
				insideView = getItemView(getItemCount() - 1, null, viewGroup);
				insideView.setVisibility(View.INVISIBLE);
				layout.addView(insideView);
			}
			setLayoutParam(insideView, columnWidth);

			if (currentPos < getItemCount()) {
				insideView.setVisibility(View.VISIBLE);
				// Populate the view
				View theView = getItemView(currentPos, insideView, viewGroup);
				theView.setOnClickListener(new ListItemClickListener(currentPos));
				theView.setOnLongClickListener(new ListItemLongClickListener(currentPos));
				if (!theView.equals(insideView)) {
					// DO NOT CHANGE THE VIEWS
				}
			} else {
				insideView.setVisibility(View.INVISIBLE);
			}
		}
	}
	
	/**
	 * 给item设置布局参数
	 * @param view
	 * @param itemWidth
	 */
	private void setLayoutParam(View view, int itemWidth){
		LayoutParams params = (LayoutParams) view.getLayoutParams();
		params.width = itemWidth;
		params.rightMargin = horizontalSpacing;
		params.bottomMargin = verticalSpacing;
		view.setLayoutParams(params);
	}

	public final View getItemView(int position, View view, ViewGroup parent) {
		ViewHolder viewHolder = onCreateViewHolder(position, view, parent);
		T item = getItem(position);
		onBindViewHolder(viewHolder, item, position);
		return viewHolder.getView();
	};
	
	
	private class ListItemClickListener implements OnClickListener {

		private int position;

		public ListItemClickListener(int currentPos) {
			position = currentPos;
		}

		@Override
		public void onClick(View v) {
			if(OnItemClickListener != null) {
				OnItemClickListener.onItemClick(v, position, getItemId(position));
			}
		}
	}
	
	private class ListItemLongClickListener implements OnLongClickListener{
		private int position;
		
		public ListItemLongClickListener(int position){
			this.position = position;
		}

		@Override
		public boolean onLongClick(View v) {
			if(null != onItemLongClickListener){
				return onItemLongClickListener.onItemLongClick(v, position, getItemId(position));
			}
			return false;
		}
		
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
	
	
	/**
	 * 每个Grid item的点击事件
	 * 
	 * @author anming.xam
	 *
	 */
	public interface OnItemClickListener{
		public void onItemClick(View v, int position, long itemId);
	}
	
	public interface OnItemLongClickListener{
		public boolean onItemLongClick(View v, int position, long id);
	}
	

}
