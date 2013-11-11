package com.se.kinderlearn.core;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	
	public Enemy(Problem p, Drawable s, int X, int Y){
		x = X;
		y = Y;
		ship = new DrawableSprite(s, X, Y);
		mProblem = p;
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		problemStr = formatStr(p.getExpression());
	}
	
	public void Draw(Canvas c, int ViewWidth, int ViewHeight){
		
	}
	
	private String formatStr(String s){
		String retstr = "";
		if(s.contains("+")){
		}
		else if(s.contains("-")){
		}
		else if(s.contains("*")){
		}
		else if(s.contains("/")){
		}
		return retstr;
	}
}
