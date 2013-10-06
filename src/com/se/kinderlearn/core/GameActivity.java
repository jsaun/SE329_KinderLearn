package com.se.kinderlearn.core;

import java.util.Random;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.se.kinderlearn.R;

public class GameActivity extends Activity {

	TextView	textCounter;
	TextView	numOne;
	TextView	numTwo;
	TextView	op;
	EditText	userAnswer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);

		textCounter = (TextView) findViewById(R.id.game_countdown);
		numOne = (TextView) findViewById(R.id.num_one);
		numTwo = (TextView) findViewById(R.id.num_two);
		op = (TextView) findViewById(R.id.operator);
		userAnswer = (EditText) findViewById(R.id.user_answer);

		MyCount countdown = new MyCount(6000, 1000);
		countdown.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	// countdowntimer is an abstract class, so extend it and fill in methods
	public class MyCount extends CountDownTimer {

		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			textCounter.setVisibility(View.INVISIBLE);
			playGame();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			System.out.println(millisUntilFinished);
			if (millisUntilFinished >= 5000) {
				textCounter.setText("Ready?");
			} else if (millisUntilFinished >= 1000
					&& millisUntilFinished < 2000) {
				textCounter.setText("GO!");
			} else {
				textCounter.setText("" + (millisUntilFinished - 1000) / 1000);
			}

		}
	}

	public void playGame() {
		Random oneRand = new Random();
		int one = oneRand.nextInt() % 100;
		numOne.setText("" + one);

		Random twoRand = new Random();
		int two = oneRand.nextInt() % 100;
		numTwo.setText("" + two);

		if (one % 2 == 0) {
			op.setText("+");
		} else {
			op.setText("-");
		}
		numOne.setVisibility(View.VISIBLE);
		numTwo.setVisibility(View.VISIBLE);
		op.setVisibility(View.VISIBLE);
		userAnswer.setVisibility(View.VISIBLE);
		int answer = 0;
		if (op.getText().equals("+")) {
			answer = one + two;
		} else if (op.getText().equals("-")) {
			answer = one - two;
		}
		final int passAnswer = answer;
		userAnswer.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				boolean handled = false;
				if (actionId == EditorInfo.IME_ACTION_GO) {
					if (Integer.parseInt(userAnswer.getText().toString()) == passAnswer) {
						System.out.println("CORRECT");
					} else {
						System.out.println("FAILED");
					}
					handled = true;
				}
				return handled;
			}
		});

	}
}
