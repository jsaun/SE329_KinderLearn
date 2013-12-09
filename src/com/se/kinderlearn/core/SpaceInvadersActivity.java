package com.se.kinderlearn.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.se.kinderlearn.R;
import com.se.kinderlearn.core.SpaceInvadersView.SpaceInvadersThread;

public class SpaceInvadersActivity extends Activity {

	Intent myIntent;
	SpaceInvadersView v;
	SpaceInvadersThread t;

	private SharedPreferences spcaceInvaderScoreSaver;
	public static final String SPACE_INVADER_SAVED_SCORE = "HighScore";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_space_invaders);

		spcaceInvaderScoreSaver = getSharedPreferences(
				SPACE_INVADER_SAVED_SCORE, 0);

		myIntent = getIntent();
		int grade = myIntent.getIntExtra("grade", 0);
		v = (SpaceInvadersView) findViewById(R.id.space_invaders);
		t = v.getThread();
		t.doStart(grade, this);
		v.SetTextView((TextView) findViewById(R.id.space_invaders_text));
		getActionBar().hide();

	}

	public void spawnEnemies(final int numEnemies) {

		this.runOnUiThread(new Runnable() {

			public void run() {
				Random rand = new Random();
				for (int i = 0; i < numEnemies; i++) {
					t.enemies.add(new Enemy(t.gen.getProblems(1).get(0),
							t.asteroid, t.explosion, t.asteroid
									.getIntrinsicWidth()
									+ rand.nextInt(v.getWidth() - 2
											* t.asteroid.getIntrinsicWidth()),
							-1 * rand.nextInt(50),
							Math.max(2, rand.nextInt(5)), v.getHeight(), t));
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

	private void setHighScore() {

		// get score
		int sc = getScore();

		// check if valid,ini = 0
		if (sc > 0) {

			SharedPreferences.Editor scoreBuilder = spcaceInvaderScoreSaver
					.edit();

			DateFormat df = new SimpleDateFormat("dd MMMM yyyy");

			String formattedScores = df.format(new Date());

			String scoreBuffer = spcaceInvaderScoreSaver.getString(
					"spaceInvaderHighScore", "");

			// check if has scores in retrived data
			if (scoreBuffer.length() > 0) {

				List<Score> scoreList = new ArrayList<Score>();

				// store each single high score in string array
				String[] getScoreList = scoreBuffer.split("\\|");

				for (String s : getScoreList) {
					// get single high score from high score list and add to
					// score object
					String[] temp = s.split(" - ");
					scoreList
							.add(new Score(temp[0], Integer.parseInt(temp[1])));
				}

				// after looping through exiting scores, add a new one
				Score newHihgScore = new Score(formattedScores, sc);
				scoreList.add(newHihgScore);
				Collections.sort(scoreList);

				StringBuilder sb = new StringBuilder("");
				for (int i = 0; i < scoreList.size(); i++) {
					if (i >= 10)
						break;
					if (i > 0)
						sb.append("|");
					sb.append(scoreList.get(i).output());
				}

				scoreBuilder.putString("spaceInvaderHighScore", sb.toString());
				scoreBuilder.commit();
			}

			// not high score found
			else {
				scoreBuilder.putString("spaceInvaderHighScore", ""
						+ formattedScores + " - " + sc);
				scoreBuilder.commit();
			}

		}

	}

	// save high score when activity is destroyed
	protected void onDestroy() {
		setHighScore();
		super.onDestroy();
	}

	public void onSaveInstanceState(Bundle savedInstanceState) {
		// get score
		int sc = getScore();
		savedInstanceState.putInt("spaceinvaderscore", sc);
		super.onSaveInstanceState(savedInstanceState);
	}

	private int getScore() {
		int sc = v.getScore();
		return sc;
	}

}
