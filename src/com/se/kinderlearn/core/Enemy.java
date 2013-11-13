package com.se.kinderlearn.core;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.StyleSpan;

public class Enemy {
	DrawableSprite ship;
	DrawableSprite explosion;
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
	
	boolean Frozen = false;
	long FrozenTime = 0;
	
	boolean Killed = false;
	long KilledTime = 0;
	
	public Enemy(Problem p, Drawable asteroid, Drawable explosion, int X, int Y, int Velocity, int KillBound){
		x = X;
		y = Y;
		ship = new DrawableSprite(asteroid, X, Y);
		this.explosion = new DrawableSprite(explosion, X, Y);
		mProblem = p;
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(24);
		mPaint.setTextAlign(Paint.Align.CENTER);
		problemStr = p.getExpression();
	    textBound = new Rect();
		mPaint.getTextBounds(problemStr, 0, problemStr.length()-1 , textBound);
		ship.x = X;
		ship.y = Y;
		this.explosion.x = X;
		this.explosion.y = Y;
		width = ship.getSize().x;
		height = ship.getSize().y;
		velocity = Velocity;
		killBound = KillBound;
	}
	
	public void Update(long time){
		if(!Killed){
			if(!Frozen){
				y = (int) (y + (time * velocity)/50);
				ship.y = y;
				explosion.y = y;
				if(ship.y > killBound){
					//Player loses a life
				}
			}
			else if(FrozenTime < 3000){
				FrozenTime += time;
				if(FrozenTime > 3000){
					Frozen = false;
					FrozenTime = 0;
				}
			}
		}
		else{
			explosion.y = y;
			KilledTime += time;
		}
	}
	
	public void Freeze(){
		Frozen = true;
	}
	
	public void Kill(){
		Killed = true;
	}
	
	public boolean CheckClick(Point testPoint){
		if(testPoint.x < x + width && testPoint.x > x - width){
			if(testPoint.y < y + height && testPoint.y > y - height){
				return true;
			}
		}
		return false;
	}
	
	public void Draw(Canvas c, int ViewWidth, int ViewHeight){
		if(!Killed){
		ship.draw(c, ViewWidth, ViewHeight);
		c.drawText(problemStr, x, y + height + (textBound.height() / 2), mPaint);
		}
		else if(KilledTime < 750){
			explosion.draw(c, ViewWidth, ViewHeight);
		}
	}
	
	public Problem getProblem(){
	return mProblem;
	}
	
}
