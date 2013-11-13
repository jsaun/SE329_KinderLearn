package com.se.kinderlearn.core;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

/**
 * A simple wrapper class for drawing orthogonal sprites
 * @author Morgan
 *
 */
public class DrawableSprite {
	
	Drawable myDrawable;
	int width;
	int height;
	int x;
	int y;
	
	/**
	 * Construct a new drawable sprite
	 * @param d The drawable our sprite is based on
	 * @param x The center of our new sprite's x coordinate
	 * @param y The center of our new sprite's y coordinate
	 */
	public DrawableSprite(Drawable d, int x, int y){
		myDrawable = d;
		width = d.getIntrinsicWidth();
		height = d.getIntrinsicHeight();
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point getSize(){
		return new Point(width, height);
	}
	
	public void draw(Canvas c, int ViewWidth, int ViewHeight){
		int left = x - width;
		int top =  y - height;
		int right = left + width * 2;
		int bottom = top + height * 2;
		myDrawable.setBounds(left, top, right, bottom);
		myDrawable.draw(c);
	}
}
