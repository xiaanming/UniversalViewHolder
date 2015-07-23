package com.example.recycleviewdemo;

import java.util.ArrayList;
import java.util.List;

import com.example.extra.HeaderGridView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class GridViewActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		

		List<String> data = new ArrayList<String>();
		for(int i=0; i<21; i++){
			data.add("String  " + i);
		}
		
		
		
		HeaderGridView gridView = (HeaderGridView) findViewById(R.id.grid);
		
		View header = LayoutInflater.from(this).inflate(R.layout.header, null);
		gridView.addHeaderView(header);
		
		View header1 = LayoutInflater.from(this).inflate(R.layout.header, null);
		gridView.addHeaderView(header1);
		
		gridView.setAdapter(new SingleAdapter(this, data, R.layout.grid_item));
		
		
		View footer = LayoutInflater.from(this).inflate(R.layout.header, null);
		gridView.addFooterView(footer);
		
		View footer1 = LayoutInflater.from(this).inflate(R.layout.header, null);
		gridView.addFooterView(footer1);
		
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(GridViewActivity.this, "position =" + position, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

}
