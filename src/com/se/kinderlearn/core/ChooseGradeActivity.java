package com.se.kinderlearn.core;

import com.se.kinderlearn.R;
import com.se.kinderlearn.R.layout;
import com.se.kinderlearn.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ChooseGradeActivity extends Activity {

	private Intent intent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_grade);
        this.intent = new Intent(this, ChooseGameActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choose_grade, menu);
        return true;
    }
    
    public void startKindergarten(View v){
		
		intent.putExtra("grade","k");
		startActivity(intent);
    }
    
    public void startFirstGrade(View v){
    	intent.putExtra("grade","1");
		startActivity(intent);
    }
    
    public void startSecondGrade(View v){
    	intent.putExtra("grade","2");
		startActivity(intent);
    }
    
    public void startThirdGrade(View v){
    	intent.putExtra("grade","3");
		startActivity(intent);
    }
    
    public void startFourthGrade(View v){
    	intent.putExtra("grade","4");
		startActivity(intent);
    }
    
    public void startFifthGrade(View v){
    	intent.putExtra("grade","5");
		startActivity(intent);
    }
    
    public void startSixthGrade(View v){
    	intent.putExtra("grade","6");
		startActivity(intent);
    }
}
