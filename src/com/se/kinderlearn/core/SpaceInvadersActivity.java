package com.se.kinderlearn.core;

import com.se.kinderlearn.R;
import com.se.kinderlearn.R.layout;
import com.se.kinderlearn.R.menu;
import com.se.kinderlearn.core.SpaceInvadersView.SpaceInvadersThread;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class SpaceInvadersActivity extends Activity {

	Intent myIntent;
	SpaceInvadersView v;
	SpaceInvadersThread t;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_space_invaders);
		myIntent = getIntent();
        int grade = myIntent.getIntExtra("grade", 0);
        v = (SpaceInvadersView) findViewById(R.id.space_invaders);
        t = v.getThread();
        t.doStart(grade);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.space_invaders, menu);
		return true;
	}

}
