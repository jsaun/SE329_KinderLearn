package com.se.kinderlearn.core;

import com.se.kinderlearn.core.SpaceInvadersView.SpaceInvadersThread;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
	
	
	AlertDialog.Builder builder;
	AlertDialog diag;
	
	SpaceInvadersThread thread;
	
	static Context context;
	
	static void setContext(Context c){
		context = c;
	}
	
	public Enemy(Problem p, Drawable asteroid, Drawable explosion, int X, int Y, int Velocity, int KillBound, SpaceInvadersThread t){
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
		
		builder = new AlertDialog.Builder(context);
		builder.setTitle(problemStr).setSingleChoiceItems(
				p.getPossibleAnswers(), 0, new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog, int answer) 
					{
						dialog.dismiss();
						if (answer == mProblem.getAnswerIndex()){
							Kill();
						}
					}
				});
		
		diag = builder.create();
		thread = t;

	}
	
	public void Update(long time){
		if(!Killed){
			if(!Frozen){
				y = (int) (y + (time * velocity)/50);
				ship.y = y;
				explosion.y = y;
				if(ship.y > killBound){
					thread.RemoveLife(this);
				}
			}
			else if(FrozenTime < 3000){
				FrozenTime += time;
				if(FrozenTime > 3000){
					Frozen = false;
					FrozenTime = 0;
					if(diag.isShowing()){
						diag.dismiss();
					}
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
		diag.show();
	}
	
	public void Kill(){
		Killed = true;
		thread.tickScore();
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
			thread.RequestFx(this);
		}
		else
		{
			thread.RequestRemove(this);
		}
	}
	
	public Problem getProblem(){
	return mProblem;
	}
	
}
