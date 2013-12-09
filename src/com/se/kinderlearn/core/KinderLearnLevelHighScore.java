package com.se.kinderlearn.core;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.se.kinderlearn.R;

public class KinderLearnLevelHighScore extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kinderlearn_high_score);

		TextView display = (TextView) findViewById(R.id.high_scores_list);

		SharedPreferences scoreSaver = getSharedPreferences(
				MathGameActivity.SAVED_SCORE, 0);

		String[] scoreList = scoreSaver.getString("highScores", "")
				.split("\\|");

		StringBuilder sb = new StringBuilder("");
		for (String s : scoreList) {
			sb.append(s + "\n");
		}

		display.setText(sb.toString());

	}
}
