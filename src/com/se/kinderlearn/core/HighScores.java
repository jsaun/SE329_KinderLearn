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

	}

	public void onClick(View view) {
		if (view.getId() == R.id.kinderLearn) {
			Intent kdlevel = new Intent(this, KinderLearnLevelHighScore.class);
			this.startActivity(kdlevel);
		} else if (view.getId() == R.id.firstGrade) {
			Intent fgrade = new Intent(this, FirstGradeHighScore.class);
			this.startActivity(fgrade);
		} else if (view.getId() == R.id.secondGrade) {
			Intent sgrade = new Intent(this, SecondGradeHighScore.class);
			this.startActivity(sgrade);
		}

	}
}
