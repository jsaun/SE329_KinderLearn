package com.se.kinderlearn.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.se.kinderlearn.R;

public class HighScores extends Activity implements OnClickListener {

	private Button kdLevel;
	private Button firstLevel;
	private Button secondLevel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_score);

		kdLevel = (Button) findViewById(R.id.kinderLearn);
		firstLevel = (Button) findViewById(R.id.firstGrade);
		secondLevel = (Button) findViewById(R.id.secondGrade);

		kdLevel.setOnClickListener(this);
		firstLevel.setOnClickListener(this);
		secondLevel.setOnClickListener(this);
		
		//sound set up
		SoundManager.getInstance();
		SoundManager.initSounds(this);
		SoundManager.loadSounds();

	}

	public void onClick(View view) {
		if (view.getId() == R.id.kinderLearn) {
			Intent kdlevel = new Intent(this, KinderLearnLevelHighScore.class);
			this.startActivity(kdlevel);
			//sound effect
			SoundManager.playSound(4, 2f);
			
		} else if (view.getId() == R.id.firstGrade) {
			Intent fgrade = new Intent(this, FirstGradeHighScore.class);
			this.startActivity(fgrade);
			//sound effect
			SoundManager.playSound(4, 2f);
			
		} else if (view.getId() == R.id.secondGrade) {
			Intent sgrade = new Intent(this, SecondGradeHighScore.class);
			this.startActivity(sgrade);
			//sound effect
			SoundManager.playSound(4, 2f);
			
		}

	}
}
