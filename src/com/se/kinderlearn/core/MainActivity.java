package com.se.kinderlearn.core;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.se.kinderlearn.R;

public class MainActivity extends Activity implements
		ActionBar.TabListener {

	//AppSectionsPagerAdapter	mAppSectionsPagerAdapter;
	ViewPager				mViewPager;
	Typeface				roboto;
	TextView				welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// No Action Bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// setFontTypes();
        
        findViewById(R.id.stats).setOnClickListener(
                new OnClickListener() {
            public void onClick(View v) {
            	setContentView(R.layout.stats);
            }
        });
        

		
		

	}

	private void setFontTypes() {
		roboto = Typeface
				.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

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

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//mViewPager.setCurrentItem(tab.getPosition());
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	public void startGame(View v) {
		System.out.println("STARTING");
		// Demonstration of navigating to external activities.
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void startChooseGrade(View v){
		Intent intent = new Intent(this, ChooseGradeActivity.class);
		startActivity(intent);
	}
	
}
