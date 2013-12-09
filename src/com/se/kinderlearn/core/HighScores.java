package com.se.kinderlearn.core;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.se.kinderlearn.R;

public class HighScores extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_score);

		TextView display = (TextView) findViewById(R.id.high_scores_list);

		// DatabaseHandler h = new DatabaseHandler(getApplicationContext());
		// h.getHighScores("1");

		// get high scores that is stored in game play
		// formate the score
		// list top 10
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
