package com.se.kinderlearn.core;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Enemy {
	DrawableSprite ship;
	int x;
	int y;
	Problem mProblem;
	int width;
	int height;
	Paint mPaint;
	String problemStr;
	Rect textBound;
	
	int velocity;
	int killBound;
	public Enemy(Problem p, Drawable s, int X, int Y, int Velocity, int KillBound){
		x = X;
		y = Y;
		ship = new DrawableSprite(s, X, Y);
		mProblem = p;
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		mPaint.setTextAlign(Paint.Align.CENTER);
		problemStr = p.getExpression();
	    textBound = new Rect();
		mPaint.getTextBounds(problemStr, 0, problemStr.length()-1 , textBound);
		ship.x = X;
		ship.y = Y;
		width = ship.getSize().x;
		height = ship.getSize().y;
		velocity = Velocity;
		killBound = KillBound;
	}
	
	public void Update(long time){
		y = (int) (y + (time * velocity)/50);
		ship.y = y;
		if(ship.y > killBound){
			//Player loses a life
		}
	}
	
	public void Draw(Canvas c, int ViewWidth, int ViewHeight){
		ship.draw(c, ViewWidth, ViewHeight);
		c.drawText(problemStr, x, y + height + (textBound.height() / 2), mPaint);
	}
	
}
