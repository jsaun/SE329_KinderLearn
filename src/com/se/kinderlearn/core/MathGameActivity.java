package com.se.kinderlearn.core;

/**
 * Simple Math Game Class
 * 
 * The score will be reset if the user enters a wrong answer
 * 
 * !! High Score System has not been set up yet !!
 */
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.se.kinderlearn.R;

public class MathGameActivity extends Activity implements OnClickListener {

	// list of buttons
	private Button button_0;
	private Button button_1;
	private Button button_2;
	private Button button_3;
	private Button button_4;
	private Button button_5;
	private Button button_6;
	private Button button_7;
	private Button button_8;
	private Button button_9;
	private Button button_enter;
	private Button button_c;

	// equation components
	private int op1 = 0;
	private int op2 = 0;
	private int operator = 0;
	private int answer = 0;

	private int score = 0;

	private Random random;

	private TextView problemTextView;
	private TextView answerTextView;
	private TextView scoreTextView;

	private ImageView resultImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_math_game);

		// text and image views
		problemTextView = (TextView) findViewById(R.id.problem);
		answerTextView = (TextView) findViewById(R.id.answer);
		scoreTextView = (TextView) findViewById(R.id.score);
		resultImageView = (ImageView) findViewById(R.id.result);

		random = new Random();

		// number, enter and clear buttons
		button_0 = (Button) findViewById(R.id.button_0);
		button_1 = (Button) findViewById(R.id.button_1);
		button_2 = (Button) findViewById(R.id.button_2);
		button_3 = (Button) findViewById(R.id.button_3);
		button_4 = (Button) findViewById(R.id.button_4);
		button_5 = (Button) findViewById(R.id.button_5);
		button_6 = (Button) findViewById(R.id.button_6);
		button_7 = (Button) findViewById(R.id.button_7);
		button_8 = (Button) findViewById(R.id.button_8);
		button_9 = (Button) findViewById(R.id.button_9);
		button_enter = (Button) findViewById(R.id.button_enter);
		button_c = (Button) findViewById(R.id.button_c);

		// add listener for all buttons
		button_1.setOnClickListener(this);
		button_2.setOnClickListener(this);
		button_3.setOnClickListener(this);
		button_4.setOnClickListener(this);
		button_5.setOnClickListener(this);
		button_6.setOnClickListener(this);
		button_7.setOnClickListener(this);
		button_8.setOnClickListener(this);
		button_9.setOnClickListener(this);
		button_0.setOnClickListener(this);
		button_enter.setOnClickListener(this);
		button_c.setOnClickListener(this);

		problemGenerator();
	}

	public void onClick(View view) {

		/**
		 * if clear button is pressed
		 */
		if (view.getId() == R.id.button_c) {
			answerTextView.setText("= ?");
		}

		/**
		 * if enter button is pressed get answer if its valid and generate new
		 * problems
		 */
		else if (view.getId() == R.id.button_enter) {

			// get answer
			String userAnswer = answerTextView.getText().toString();
			int anslength = userAnswer.length();

			// check last char of the answer

			// if valid
			if (!(userAnswer.substring(anslength - 1).equals("?"))) {
				int ans = Integer.parseInt(userAnswer.substring(2));
				String sc = scoreTextView.getText().toString();

				score = Integer.parseInt(sc.substring(7));
				// check answer
				if (ans == answer) {
					// correct
					score++;
					scoreTextView.setText("Score: " + score);
					resultImageView.setImageResource(R.drawable.right);
					resultImageView.setVisibility(View.VISIBLE);
				} else {
					score = 0;
					scoreTextView.setText("Score: " + score);
					resultImageView.setImageResource(R.drawable.wrong);
					resultImageView.setVisibility(View.VISIBLE);
				}
				problemGenerator();
			}
		}

		/**
		 * if number button is pressed
		 */
		else {
			resultImageView.setVisibility(View.INVISIBLE);
			int number = Integer.parseInt(view.getTag().toString());
			if (answerTextView.getText().toString().endsWith("?"))
				answerTextView.setText("= " + number);
			else
				answerTextView.append("" + number);
		}
	}

	/**
	 * This method is to generate simple add/sub problems
	 */
	private void problemGenerator() {

		answerTextView.setText("= ?");

		// 1 : add
		// 2 : sub
		operator = random.nextInt(2) + 1;
		op1 = random.nextInt(10) + 1;
		op2 = random.nextInt(10) + 1;

		// sub
		if (operator == 2) {
			// no negative numbers
			while (op1 < op2) {
				op1 = random.nextInt(10);
				op2 = random.nextInt(10);
			}
			answer = op1 - op2;

			problemTextView.setText(op1 + " - " + op2);
		}

		// add
		else {
			answer = op1 + op2;

			problemTextView.setText(op1 + " + " + op2);
		}

	}

}
