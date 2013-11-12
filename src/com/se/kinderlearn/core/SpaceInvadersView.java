package com.se.kinderlearn.core;

import java.util.ArrayList;
import java.util.Calendar;

import com.se.kinderlearn.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;

public class SpaceInvadersView extends SurfaceView implements SurfaceHolder.Callback {
boolean run;

	public class SpaceInvadersThread extends Thread{		
		private Bitmap backgroundImage;
		private DrawableSprite ship;
		private Drawable explosion;
		private Handler mHandler;
		private long lastTime;
		private Paint linePaint;
		private SurfaceHolder mSurfaceHolder;
		
		private ArrayList<Enemy> enemies;
		private Enemy testEnemy;
		
		public SpaceInvadersThread(SurfaceHolder surfaceHolder, Context context,
                Handler handler){
			mSurfaceHolder = surfaceHolder;
			mHandler = handler;
			mContext = context;
			Resources res = context.getResources();
			ship = new DrawableSprite(res.getDrawable(R.drawable.ship), 0, 0 );
			explosion = res.getDrawable(R.drawable.explosion);
			backgroundImage = BitmapFactory.decodeResource(res, R.drawable.planet_earth_in_space);
			linePaint = new Paint();
			linePaint.setAntiAlias(true);
			linePaint.setARGB(255,0,255,0);
			run = true;
			testEnemy = new Enemy(new Problem("4 + 5", 9), explosion, 100, 100, 5, getHeight());
			enemies = new ArrayList<Enemy>();
			enemies.add(testEnemy);
		}
		
		public void doStart(){
			synchronized(mSurfaceHolder){
				lastTime = Calendar.getInstance().getTimeInMillis();
			}
		}
		
		public void run(){
			while (run) {
				long time = Calendar.getInstance().getTimeInMillis() - lastTime;
				lastTime = Calendar.getInstance().getTimeInMillis();
                Canvas c = null;
                ship.setPosition(getWidth()/2, getHeight() - ship.getSize().y / 2);
                testEnemy.Update(time);
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
			if(run){
			canvas.drawBitmap(backgroundImage, null, new Rect(0,0, getWidth(), getHeight()), null);
			ship.draw(canvas, getWidth(), getHeight());
			testEnemy.Draw(canvas, getWidth(), getHeight());
			}
		}
		
		public void TestEnemies(Point testPoint){
			for(int i = 0; i<enemies.size(); i++){
				boolean kill = enemies.get(i).CheckClick(testPoint);
				if(kill){
					System.out.println("kill confirmed");
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
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e){
		
		if(e.getAction() == MotionEvent.ACTION_DOWN){
			int x = (int) e.getX();
			int y = (int) e.getY();
			thread.TestEnemies(new Point(x, y));
		}
		
		return true;
	}
	
	public SpaceInvadersThread getThread(){
		return thread;
	}

}
