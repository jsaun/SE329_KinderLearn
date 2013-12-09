package com.se.kinderlearn.core;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.se.kinderlearn.R;

public class SpaceInvadersView extends SurfaceView implements
		SurfaceHolder.Callback {

	boolean run;

	private int highScore = 0;

	public class SpaceInvadersThread extends Thread {
		private Bitmap backgroundImage;
		private DrawableSprite ship;
		public Drawable explosion;
		public Drawable asteroid;
		private Handler mHandler;
		private long lastTime;
		private Paint linePaint;
		private Paint textPaint;
		private SurfaceHolder mSurfaceHolder;
		private boolean pause = false;
		public ProblemGenerator gen;

		private SpaceInvadersActivity activity;

		public ArrayList<Enemy> enemies;
		private Enemy testEnemy;

		private ArrayList<Enemy> fxPoints;

		private int lives = 3;
		private int score = 0;

		Context c;

		TextView v;

		int enemyCountStart = 3;
		int liveEnemies = 0;
		int enemiesThisRound;
		int round = 0;

		DatabaseHandler dbHandler;

		public SpaceInvadersThread(SurfaceHolder surfaceHolder,
				Context context, Handler handler) {
			mSurfaceHolder = surfaceHolder;
			mHandler = handler;
			mContext = context;
			Resources res = context.getResources();
			ship = new DrawableSprite(res.getDrawable(R.drawable.ship), 0, 0);
			explosion = res.getDrawable(R.drawable.explosion);
			backgroundImage = BitmapFactory.decodeResource(res,
					R.drawable.planet_earth_in_space);
			asteroid = res.getDrawable(R.drawable.astroid);
			linePaint = new Paint();
			linePaint.setAntiAlias(true);
			linePaint.setARGB(255, 0, 255, 0);

			textPaint = new Paint();
			textPaint.setTextAlign(Align.RIGHT);
			textPaint.setTextSize(32);
			textPaint.setARGB(255, 255, 255, 255);

			c = context;
			fxPoints = new ArrayList<Enemy>();
			enemies = new ArrayList<Enemy>();
			Enemy.setContext(c);
			dbHandler = new DatabaseHandler(c);
		}

		@Override
		public void start() {
			run = true;
			super.start();
		}

		public void tickScore() {
			score += 100;
			highScore = score;
		}

		public void doStart(int grade, SpaceInvadersActivity a) {
			synchronized (mSurfaceHolder) {
				lastTime = Calendar.getInstance().getTimeInMillis();
				gen = new ProblemGenerator("" + grade);
				activity = a;
			}
		}

		public void RequestRemove(Enemy toRemove) {
			enemies.remove(toRemove);
			liveEnemies--;
			fxPoints.remove(toRemove);
		}

		public void RemoveLife(Enemy toRemove) {
			lives--;
			if (lives == 0) {
				Message msg = mHandler.obtainMessage();
				Bundle b = new Bundle();
				b.putString("text", "Game Over");
				b.putInt("viz", View.VISIBLE);
				msg.setData(b);
				mHandler.sendMessage(msg);
				run = false;
				dbHandler.saveHighScore(score, gen.getGrade());
			}
			RequestRemove(toRemove);
		}

		public void RequestFx(Enemy e) {
			fxPoints.add(e);
		}

		public void run() {
			while (run) {
<<<<<<< HEAD
				if (liveEnemies == 0) {
=======
					if(pause){
						while(pause){
						yield();
						}
					}
				if(liveEnemies == 0){
>>>>>>> upstream/master
					round++;
					activity.spawnEnemies(enemyCountStart * round);
					liveEnemies = enemyCountStart * round;
				}

				long time = Calendar.getInstance().getTimeInMillis() - lastTime;
				lastTime = Calendar.getInstance().getTimeInMillis();
<<<<<<< HEAD
				Canvas c = null;
				ship.setPosition(getWidth() / 2, getHeight() - ship.getSize().y
						/ 2);
				for (int i = 0; i < enemies.size(); i++) {
					enemies.get(i).Update(time);
				}
				try {
					c = mSurfaceHolder.lockCanvas(null);
					synchronized (mSurfaceHolder) {
						doDraw(c);
					}
				} finally {
					// do this in a finally so that if an exception is thrown
					// during the above, we don't leave the Surface in an
					// inconsistent state
					if (c != null) {
						mSurfaceHolder.unlockCanvasAndPost(c);
					}
				}
=======
                Canvas c = null;
                ship.setPosition(getWidth()/2, getHeight() - ship.getSize().y / 2);
                for(int i = 0; i < enemies.size(); i++){
                	enemies.get(i).Update(time);
                }
                try {
                    c = mSurfaceHolder.lockCanvas(null);
                    synchronized (mSurfaceHolder) {
                    	doDraw(c);
                    }
                } finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
		}
		
		public void doDraw(Canvas canvas){
			if(run && canvas!=null){
			canvas.drawBitmap(backgroundImage, null, new Rect(0,0, getWidth(), getHeight()), null);
			ship.draw(canvas, getWidth(), getHeight());
			for(int i = 0; i < enemies.size(); i++){
				enemies.get(i).Draw(canvas, getWidth(), getHeight());
>>>>>>> upstream/master
			}
		}

		public void doDraw(Canvas canvas) {
			if (run) {
				canvas.drawBitmap(backgroundImage, null, new Rect(0, 0,
						getWidth(), getHeight()), null);
				ship.draw(canvas, getWidth(), getHeight());
				for (int i = 0; i < enemies.size(); i++) {
					enemies.get(i).Draw(canvas, getWidth(), getHeight());
				}
				for (int i = 0; i < fxPoints.size(); i++) {
					canvas.drawLine(ship.x, ship.y - ship.height,
							fxPoints.get(i).x, fxPoints.get(i).y, linePaint);
				}
				fxPoints.clear();
				canvas.drawText("Score: " + score, getWidth(), 32, textPaint);
				canvas.drawText("Lives: " + lives, getWidth(), 64, textPaint);
			}
		}

		public void TestEnemies(Point testPoint) {
			for (int i = 0; i < enemies.size(); i++) {
				boolean kill = enemies.get(i).CheckClick(testPoint);
				if (kill) {
					enemies.get(i).Freeze();
				}
			}
		}

	}

	private SpaceInvadersThread thread;
	private Context mContext;
	private TextView mStatusText;

	public SpaceInvadersView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// register our interest in hearing about changes to our surface
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);

		// create thread only; it's started in surfaceCreated()
		thread = new SpaceInvadersThread(holder, context, new Handler() {
			@Override
			public void handleMessage(Message m) {
				mStatusText.setVisibility(m.getData().getInt("viz"));
				mStatusText.setText(m.getData().getString("text"));
			}
		});
		setFocusable(true); // make sure we get key events
	}

	public void addCallback(Callback callback) {
		// TODO Auto-generated method stub

	}

	public Surface getSurface() {
		// TODO Auto-generated method stub
		return null;
	}

	public void SetTextView(TextView v) {
		mStatusText = v;
	}

	public void SetGameOver() {
		mStatusText.setText("Game Over");
	}

	public Rect getSurfaceFrame() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCreating() {
		// TODO Auto-generated method stub
		return false;
	}

	public Canvas lockCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	public Canvas lockCanvas(Rect dirty) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeCallback(Callback callback) {
		// TODO Auto-generated method stub

	}

	public void setFixedSize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void setFormat(int format) {
		// TODO Auto-generated method stub

	}

	public void setSizeFromLayout() {
		// TODO Auto-generated method stub

	}

	public void setType(int type) {
		// TODO Auto-generated method stub

	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder holder) {
<<<<<<< HEAD
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		try {
			run = false;
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

=======
		if(!thread.isAlive()){
		thread.start();
		}
		else
		{
		thread.pause = false;
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
			thread.pause = true;
>>>>>>> upstream/master
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {

		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			int x = (int) e.getX();
			int y = (int) e.getY();
			thread.TestEnemies(new Point(x, y));
		}

		return true;
	}

	public SpaceInvadersThread getThread() {
		return thread;
	}

	public int getScore() {
		return highScore;
	}

}
