package com.se.kinderlearn.core;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.se.kinderlearn.R;

public class FirstGradeHighScore extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_grade_score);

		TextView display = (TextView) findViewById(R.id.high_scores_list);

		SharedPreferences scoreSaver = getSharedPreferences(
				SpaceInvadersActivity.SPACE_INVADER_SAVED_SCORE, 0);

		String[] scoreList = scoreSaver.getString("spaceInvaderHighScore", "")
				.split("\\|");

		StringBuilder sb = new StringBuilder("");
		for (String s : scoreList) {
			sb.append(s + "\n");
		}

		display.setText(sb.toString());

	}

}
