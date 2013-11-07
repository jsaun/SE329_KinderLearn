package com.se.kinderlearn.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.TextView;

public class SpaceInvadersView extends SurfaceView implements SurfaceHolder.Callback {


	public class SpaceInvadersThread extends Thread{		
		public SpaceInvadersThread(SurfaceHolder surfaceHolder, Context context,
                Handler handler){
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

	public void unlockCanvasAndPost(Canvas canvas) {
		// TODO Auto-generated method stub

	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
