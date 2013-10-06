package com.se.kinderlearn.core;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
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
import android.widget.Button;
import android.widget.TextView;

import com.se.kinderlearn.R;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	AppSectionsPagerAdapter	mAppSectionsPagerAdapter;
	ViewPager				mViewPager;

	Typeface				roboto;
	TextView				welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// No Action Bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		setFontTypes();
		welcome = (TextView) findViewById(R.id.mainWelcomeText);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(
				getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);

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

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the primary sections of the app.
	 */
	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			switch (i) {
			case 0:
				// The first section of the app is the most interesting -- it
				// offers a launchpad into the other demonstrations in this
				// example
				// application.
				return new WelcomeFragment();

			case 1:
				// Rules Fragment
				Fragment fragment = new RulesFragment();
				Bundle args = new Bundle();
				args.putInt(RulesFragment.ARG_SECTION_NUMBER, i + 1);
				fragment.setArguments(args);
				return fragment;
			default:
				// The other sections of the app are dummy placeholders.
				Fragment fragmentTwo = new StartGameFragment();
				Bundle argsTwo = new Bundle();
				argsTwo.putInt(StartGameFragment.ARG_SECTION_NUMBER, i + 1);
				fragmentTwo.setArguments(argsTwo);
				return fragmentTwo;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "Section " + (position + 1);
		}
	}

	/**
	 * A fragment that launches other parts of the demo application.
	 */
	public static class WelcomeFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_welcome,
					container, false);

			return rootView;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class RulesFragment extends Fragment {

		public static final String	ARG_SECTION_NUMBER	= "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_rules,
					container, false);
			Bundle args = getArguments();
			TextView text = ((TextView) rootView
					.findViewById(R.id.main_rules_text));
			return rootView;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class StartGameFragment extends Fragment {

		public static final String	ARG_SECTION_NUMBER	= "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_start_game,
					container, false);
			Bundle args = getArguments();
			Button start = ((Button) rootView
					.findViewById(R.id.main_start_game));
			return rootView;
		}

	}

	public void startGame(View v) {
		System.out.println("STARTING");
		// Demonstration of navigating to external activities.
		Intent intent = new Intent();
		intent.setClass(this, GameActivity.class);
		startActivity(intent);
		finish();
	}
}
