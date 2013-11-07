package com.se.kinderlearn.core;

import com.se.kinderlearn.R;
import com.se.kinderlearn.R.layout;
import com.se.kinderlearn.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class OptionsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_options, menu);
        return true;
    }
}
