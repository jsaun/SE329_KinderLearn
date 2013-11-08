package com.se.kinderlearn.core;

import com.se.kinderlearn.R;
import com.se.kinderlearn.R.layout;
import com.se.kinderlearn.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ChooseGameActivity extends Activity {

	private String grade;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        Intent intent= getIntent(); // gets the previously created intent
        grade = intent.getStringExtra("grade");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choose_game, menu);
        return true;
    }
    
    public void startGame1(View v){
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra("grade",grade);
		startActivity(intent);
    }
    
    public void startGame2(View v){
		Intent intent = new Intent(this, SpaceInvadersActivity.class);
		intent.putExtra("grade",grade);
		startActivity(intent);
    }
}
