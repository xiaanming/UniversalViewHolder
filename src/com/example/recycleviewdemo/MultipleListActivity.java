package com.example.recycleviewdemo;

import java.util.ArrayList;
import java.util.List;

import com.example.framework.ViewHolderAdapter;
import com.example.framework.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class MultipleListActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		List<Item> data = new ArrayList<Item>();
		for(int i=0; i<500; i++){
			Item item = new Item();
			item.setText("String  " + i);
			item.setType(i % 3);
			
			if(i % 3 == 0){
				item.setResId(R.drawable.big_share_laiwang);
			}else if(i % 3 == 1){
				item.setResId(R.drawable.big_share_weibo);
			}else{
				item.setResId(R.drawable.big_share_weixin);
			}
			
			data.add(item);
			
		}
		
		ListView listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(new MultipleAdapter(this, data));
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
				TextView text2 = viewHolder.getView(R.id.textView);
				text2.setText(item.getText());
				
				viewHolder.setBackgroundRes(R.id.imageView, item.getResId());
			case 2:
				TextView text3 = viewHolder.getView(R.id.textView);
				text3.setText(item.getText());
				
				viewHolder.setBackgroundRes(R.id.imageView, item.getResId());

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
						R.layout.item1);
			case 2:
				return ViewHolder.get(context, convertView, parent,
						R.layout.grid_item);

			default:
				return ViewHolder.get(context, convertView, parent,
						R.layout.item);
			}
		}
		
	}

}
