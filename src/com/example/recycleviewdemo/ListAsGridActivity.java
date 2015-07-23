package com.example.recycleviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.framework.ViewHolderGridAdapter.OnItemClickListener;
import com.example.framework.ViewHolderGridAdapter.OnItemLongClickListener;
/**
 * 
 * @author anming.xam
 *
 */
public class ListAsGridActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		List<String> data = new ArrayList<String>();
		for(int i=0; i<500; i++){
			data.add("String  " + i);
		}
		
		ListView listView = (ListView) findViewById(R.id.list);
		listView.setDividerHeight(0);
		SingleGridAdapter adapter = new SingleGridAdapter(this, data, R.layout.grid_item);
		adapter.setNumColumns(3);
		listView.setAdapter(adapter);
		listView.setSelector(android.R.color.transparent);
		
		adapter.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(View v, int position, long itemId) {
				Toast.makeText(ListAsGridActivity.this, "click = " + position, Toast.LENGTH_SHORT).show();
			}
		});
		
		adapter.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(View v, int position, long id) {
				Toast.makeText(ListAsGridActivity.this, "longClick = " + position, Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
	}

}
