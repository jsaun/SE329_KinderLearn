package com.se.kinderlearn.core;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import com.se.kinderlearn.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

public class DrawGraph extends View {
    Paint paint = new Paint();
    Paint words = new Paint();
    Paint dates = new Paint();
    Paint title = new Paint();
    SharedPreferences scores;
    Bitmap board;

    public DrawGraph(Context context, SharedPreferences scores) {
        super(context);
        paint.setColor(Color.WHITE);
        words.setColor(Color.WHITE);
        title.setColor(Color.WHITE);
        dates.setColor(Color.WHITE);
        words.setTextSize(26);
        dates.setTextSize(18);
        title.setTextSize(52);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        this.scores = scores;
        board=BitmapFactory.decodeResource(getResources(),R.drawable.planet_earth_in_space);

    }

    @Override
    public void onDraw(Canvas canvas) {
    		//canvas.drawBitmap(board, Rect src, RectF dst, null);
    		this.setBackgroundDrawable(new BitmapDrawable(getContext().getResources(), board));
    		canvas.drawText("Progress", 100, 100, title);
    		String[] scoreList = scores.getString("spaceInvaderHighScore", "")
				.split("\\|");
    		Arrays.sort(scoreList);
    		Scanner s;
    		DateFormat df;
    		int y = 100;
    		int x = 100;
    		ArrayList<Point> points = new ArrayList<Point>();
    		for(String score: scoreList){			
    			s = new Scanner(score);
    			df = new SimpleDateFormat("dd MMMM yyyy");
    			s.useDelimiter("-");
    			String date = s.next();
    			String scoreString = s.next();
    			int scoreInt = Integer.parseInt(scoreString.trim());
    			try {
					points.add(new Point(x, (800 - ((scoreInt)/4)), scoreInt, df.parse(date)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			//canvas.drawText(date, 200, y, paint);
    			//canvas.drawP
    			//canvas.drawPoint(x, (800 - ((scoreInt%2000)/4)), paint);
    			//canvas.drawPoint(x, 200 - ((scoreInt%2000)/100), paint);
    			y = y + 100;
    			x = x + 50;
    		}
    		if(points.size() > 1){
    			Point p1 = points.get(0);
    			canvas.drawText("" + p1.s, 20, p1.y, words);
    			canvas.drawText((p1.t.getMonth() + 1) + "/" + p1.t.getDate(), p1.x - 10, 820, dates);
    			for(int i = 1; i < points.size(); i++){
    				Point p2 = points.get(i);
    				canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
    				canvas.drawText("" + p2.s, 20, p2.y, words);
    				canvas.drawText((p2.t.getMonth() + 1) + "/" + p2.t.getDate(), p2.x - 10, 820, dates);
    				p1 = p2;
    				
    			}
    		}
    }
    
    class Point{
    	int x;
    	int y;
    	int s;
    	Date t;
    	public Point(int x, int y, int s, Date t){
    		this.s = s;
    		this.x = x;
    		this.y = y;
    		this.t = t;
    	}
    }

}