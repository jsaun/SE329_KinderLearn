// not using this class for now

package com.se.kinderlearn.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.se.kinderlearn.R;

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
