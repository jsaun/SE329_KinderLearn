package com.se.kinderlearn.core;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.se.kinderlearn.R;

public class SecondGradeHighScore extends Activity {

	DrawGraph drawView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_second_grade_score);
		
		//TextView display = (TextView) findViewById(R.id.high_scores_list);

		SharedPreferences scoreSaver = getSharedPreferences(
				SpaceInvadersActivity.SPACE_INVADER_SAVED_SCORE, 0);
		
		drawView = new DrawGraph(this, scoreSaver);
        drawView.setBackgroundColor(R.drawable.planet_earth_in_space);
        setContentView(drawView);
        
		/*String[] scoreList = scoreSaver.getString("spaceInvaderHighScore", "")
				.split("\\|");

		StringBuilder sb = new StringBuilder("");
		for (String s : scoreList) {
			sb.append(s + "\n");
		}

		display.setText(sb.toString());*/
	}

}
