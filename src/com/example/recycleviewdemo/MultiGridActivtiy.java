package com.example.recycleviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.framework.ViewHolder;
import com.example.framework.ViewHolderAdapter;

public class MultiGridActivtiy extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		
		List<Item> data = new ArrayList<Item>();
		for(int i=0; i<100; i++){
			Item item = new Item();
			item.setText("String  " + i);
			
			if(i == 0){
				item.setType(1);
			}
			
			
			data.add(item);
			
		}
		
		GridView gridView = (GridView) findViewById(R.id.grid);
		gridView.setAdapter(new MultipleAdapter(this, data));
		
	}
	
	
	public class Item{
		private int resId;
		private String text;
		private int type;
		
		public int getResId() {
			return resId;
		}
		public void setResId(int resId) {
			this.resId = resId;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		
	}
	
	public static class MultipleAdapter extends ViewHolderAdapter<Item>{

		public MultipleAdapter(Context context, List<Item> data) {
			super(context, data);
		}
		
		
		

		@Override
		public int getCount() {
			return super.getCount();
		}




		@Override
		public int getItemViewType(int position) {
			return data.get(position).getType();
		}



		@Override
		public int getViewTypeCount() {
			return 3;
		}



		@Override
		public void onBindViewHolder(ViewHolder viewHolder, Item item, int position) {
			switch (getItemViewType(position)) {
			case 0:
				TextView text = viewHolder.getView(R.id.textView);
				text.setText(item.getText());
				
				viewHolder.setBackgroundRes(R.id.imageView, item.getResId());
			case 1:

			}
		}

		@Override
		public ViewHolder onCreateViewHolder(int position, View convertView,
				ViewGroup parent) {
			switch (getItemViewType(position)) {
			case 0:
				return ViewHolder.get(context, convertView, parent,
						R.layout.item);
			case 1:
				return ViewHolder.get(context, convertView, parent,
						R.layout.full_width);

			default:
				return ViewHolder.get(context, convertView, parent,
						R.layout.item);
			}
		}
		
	}

}
