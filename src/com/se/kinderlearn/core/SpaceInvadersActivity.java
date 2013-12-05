package com.se.kinderlearn.core;

import java.util.Random;

import com.se.kinderlearn.R;
import com.se.kinderlearn.R.layout;
import com.se.kinderlearn.R.menu;
import com.se.kinderlearn.core.SpaceInvadersView.SpaceInvadersThread;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

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
        t.doStart(grade, this);
        v.SetTextView((TextView)findViewById(R.id.space_invaders_text));
        getActionBar().hide();

	}

	public void spawnEnemies(final int numEnemies){
		
		this.runOnUiThread(new Runnable() {
			
			public void run() {
				Random rand = new Random();
				for(int i = 0; i < numEnemies; i++){
					t.enemies.add(new Enemy(t.gen.getProblems(1).get(0), t.asteroid, t.explosion, t.asteroid.getIntrinsicWidth() + rand.nextInt(v.getWidth() - 2 * t.asteroid.getIntrinsicWidth()), -1 * rand.nextInt(50),Math.max(2, rand.nextInt(5)), v.getHeight(), t));
				}
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.space_invaders, menu);
		return true;
	}

}
