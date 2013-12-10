package com.se.kinderlearn.core;

/**
 * Changed the structure of the main activity class
 * Using a alert dialog for user to choose game
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.se.kinderlearn.R;

public class MainActivity extends Activity implements OnClickListener {

	private Button play, help, highScores, option;

	private String[] gradeLevels = { "Kinderlearn", "Grade1", "Grade2",
			"Grade3", "Grade4", "Grade5", "Grade6" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		play = (Button) findViewById(R.id.main_start_game);
		help = (Button) findViewById(R.id.help);
		highScores = (Button) findViewById(R.id.high_score);
		option = (Button) findViewById(R.id.options);

		play.setOnClickListener(this);
		help.setOnClickListener(this);
		highScores.setOnClickListener(this);
		option.setOnClickListener(this);

		// sound setup
		SoundManager.getInstance();
		SoundManager.initSounds(this);
		SoundManager.loadSounds();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {
		if (view.getId() == R.id.main_start_game) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle("Choose a Grade").setSingleChoiceItems(
					gradeLevels, 0, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int gradeLevel) {
							dialog.dismiss();
							startGame(gradeLevel);
						}
					});

			AlertDialog ad = builder.create();
			ad.show();

			// sound effect
			SoundManager.playSound(1, 1f);

		}

		else if (view.getId() == R.id.help) {
			// go to help page
			Intent help = new Intent(this, Help.class);
			this.startActivity(help);
			// sound effect
			SoundManager.playSound(1, 1f);

		}

		else if (view.getId() == R.id.high_score) {
			// go to high score page
			Intent high = new Intent(this, HighScores.class);
			this.startActivity(high);
			// sound effect
			SoundManager.playSound(1, 1f);

		} else if (view.getId() == R.id.options) {
			// go to option page
			Intent op = new Intent(this, OptionsActivity.class);
			this.startActivity(op);
			// sound effect
			SoundManager.playSound(1, 1f);

		}
	}

	/**
	 * This method reads the user input grade level and start the game
	 * 
	 * @param grade
	 *            the level of difficulty of the game
	 * 
	 */
	private void startGame(int grade) {

		if (grade == 0) {
			Intent play = new Intent(this, MathGameActivity.class);
			play.putExtra("grade", 0);
			this.startActivity(play);
			// sound effect
			SoundManager.playSound(1, 1f);
		}

		else if (grade == 1) {
			Intent play = new Intent(this, SpaceInvadersActivity.class);
			play.putExtra("grade", 1);
			this.startActivity(play);
			// sound effect
			SoundManager.playSound(1, 1f);
		}

		else if (grade == 2) {
			Intent play = new Intent(this, SpaceInvadersActivity.class);
			play.putExtra("grade", 2);
			this.startActivity(play);
			// sound effect
			SoundManager.playSound(1, 1f);
		}

		else if (grade == 3) {
			Intent play = new Intent(this, SpaceInvadersActivity.class);
			play.putExtra("grade", 3);
			this.startActivity(play);
			// sound effect
			SoundManager.playSound(1, 1f);
		}

		else if (grade == 4) {
			Intent play = new Intent(this, SpaceInvadersActivity.class);
			play.putExtra("grade", 4);
			this.startActivity(play);
			// sound effect
			SoundManager.playSound(1, 1f);

		} else if (grade == 5) {
			Intent play = new Intent(this, SpaceInvadersActivity.class);
			play.putExtra("grade", 5);
			this.startActivity(play);
			// sound effect
			SoundManager.playSound(1, 1f);
		}

		else if (grade == 6) {
			Intent play = new Intent(this, SpaceInvadersActivity.class);
			play.putExtra("grade", 6);
			this.startActivity(play);
			// sound effect
			SoundManager.playSound(1, 1f);
		}

	}

}
