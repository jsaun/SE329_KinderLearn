package com.se.kinderlearn.core;

import com.se.kinderlearn.R;
import com.se.kinderlearn.R.layout;
import com.se.kinderlearn.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {
	Typeface	roboto;
	TextView	welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		setFontTypes();
		welcome = (TextView) findViewById(R.id.mainWelcomeText);
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				setTypeFace(roboto, "#ffffff", 14, welcome);

			}
		});
	}

	private void setFontTypes() {
		roboto = Typeface
				.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static void setTypeFace(Typeface tf, String color, int size,
			TextView view) {
		view.setTypeface(tf);
		if (size == 0) {
			view.setTextSize(14);
		} else {
			view.setTextSize(size);
		}
		if (color != null) {
			view.setTextColor(Color.parseColor(color));
		}
		view.setGravity(Gravity.CENTER);

	}
}
