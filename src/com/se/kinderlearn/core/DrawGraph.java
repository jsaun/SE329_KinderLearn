package com.se.kinderlearn.core;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import com.se.kinderlearn.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawGraph extends View {
    Paint paint = new Paint();
    SharedPreferences scores;

    public DrawGraph(Context context, SharedPreferences scores) {
        super(context);
        paint.setColor(Color.BLACK);
        this.scores = scores;

    }

    @Override
    public void onDraw(Canvas canvas) {
    		String[] scoreList = scores.getString("spaceInvaderHighScore", "")
				.split("\\|");
    		Arrays.sort(scoreList);
    		Scanner s;
    		DateFormat df;
    		int y = 100;
    		int x = 100;
    		for(String score: scoreList){			
    			s = new Scanner(score);
    			df = new SimpleDateFormat("dd MMMM yyyy");
    			s.useDelimiter("-");
    			String date = s.next();
    			String scoreString = s.next();
    			int scoreInt = Integer.parseInt(scoreString.trim());
    			//canvas.drawText(date, 200, y, paint);
    			//canvas.drawP
    			//canvas.drawPoint(x, (800 - ((scoreInt%2000)/4)), paint);
    			//canvas.drawPoint(x, 200 - ((scoreInt%2000)/100), paint);
    			y = y + 100;
    			x = x + 50;
    		}
            canvas.drawLine(0, 0, 20, 20, paint);
            canvas.drawLine(20, 0, 0, 20, paint);
    }

}