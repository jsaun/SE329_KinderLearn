package com.se.kinderlearn.core;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
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
	Button		next;

	int			usersScore;
	TextView	answerDetail;
	GameThread	game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);

		textCounter = (TextView) findViewById(R.id.game_countdown);
		numOne = (TextView) findViewById(R.id.num_one);
		numTwo = (TextView) findViewById(R.id.num_two);
		op = (TextView) findViewById(R.id.operator);
		answerDetail = (TextView) findViewById(R.id.answer_detail);

		userAnswer = (EditText) findViewById(R.id.user_answer);
		next = (Button) findViewById(R.id.new_question);

		MyCount countdown = new MyCount(6000, 1000);
		usersScore = 0;
		countdown.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	public void nextQuestion(View v) {
		if (usersScore < 10) {
			if (game.isAlive()) {
				Thread kill = game;
				game = null;
				kill.interrupt();
			}
			game = new GameThread();
			game.run();
		} else {
			setWinStatement();
		}
	}

	private void setWinStatement() {
		this.answerDetail.post(new Runnable() {

			@Override
			public void run() {
				answerDetail.setVisibility(View.VISIBLE);
				answerDetail.setText("You Won!");
				numOne.setVisibility(View.INVISIBLE);
				numTwo.setVisibility(View.INVISIBLE);
				op.setVisibility(View.INVISIBLE);

			}
		});
	}

	// countdowntimer is an abstract class, so extend it and fill in methods
	public class MyCount extends CountDownTimer {

		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			textCounter.setVisibility(View.INVISIBLE);
			game = new GameThread();
			game.run();
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

	private class GameThread extends Thread {

		@Override
		public void run() {
			Random oneRand = new Random();
			int one = oneRand.nextInt() % 100;
			numOne.setText("" + one);

			Random twoRand = new Random();
			int two = twoRand.nextInt() % 100;
			numTwo.setText("" + two);

			if (one % 2 == 0) {
				op.setText("+");
			} else {
				op.setText("-");
			}
			answerDetail.setVisibility(View.INVISIBLE);
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
						int guess = 0;
						try {
							guess = Integer.parseInt(userAnswer.getText()
									.toString());
						} catch (NumberFormatException n) {
							answerDetail.setVisibility(View.VISIBLE);
							answerDetail.post(new Runnable() {

								@Override
								public void run() {
									answerDetail.setText("Enter a number");
								}
							});
							return false;
						}
						if (guess == passAnswer) {
							answerDetail.setVisibility(View.VISIBLE);
							answerDetail.post(new Runnable() {

								@Override
								public void run() {
									answerDetail.setText("Correct! Score = "
											+ usersScore);
								}
							});
							usersScore++;
						} else {
							answerDetail.setVisibility(View.VISIBLE);
							answerDetail.post(new Runnable() {

								@Override
								public void run() {
									answerDetail.setText("Incorrect! Score = "
											+ usersScore);
								}
							});
						}
						handled = true;
					}
					userAnswer.setText("");
					return handled;
				}
			});

		}
	}
}
