package com.example.recycleviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class SingleListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		List<String> data = new ArrayList<String>();
		for(int i=0; i<500; i++){
			data.add("String  " + i);
		}
		
		ListView listView = (ListView) findViewById(R.id.list);
		SingleAdapter mSingleAdapter = new SingleAdapter(this, data, R.layout.item);
		listView.setAdapter(mSingleAdapter);
		
	}
	
	

}
