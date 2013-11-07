package com.se.kinderlearn.core;

import com.se.kinderlearn.R;
import com.se.kinderlearn.R.layout;
import com.se.kinderlearn.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SpaceInvadersActivity extends Activity {

	Intent myIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_space_invaders);
		myIntent = getIntent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.space_invaders, menu);
		return true;
	}

}
