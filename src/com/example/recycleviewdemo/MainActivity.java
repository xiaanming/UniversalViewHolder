package com.example.recycleviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.single_item_list).setOnClickListener(this);
		findViewById(R.id.grid).setOnClickListener(this);
		findViewById(R.id.multiple_item_list).setOnClickListener(this);
		findViewById(R.id.list_as_grid).setOnClickListener(this);
		findViewById(R.id.multi_grid).setOnClickListener(this);
		
	}


	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.single_item_list:{
			Intent intent = new Intent(this, SingleListActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.grid:{
			Intent intent = new Intent(this, GridViewActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.multiple_item_list:{
			Intent intent = new Intent(this, MultipleListActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.list_as_grid:{
			Intent intent = new Intent(this, ListAsGridActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.multi_grid:{
			Intent intent = new Intent(this, MultiGridActivtiy.class);
			startActivity(intent);
			break;
		}
		}
		
	}
}
