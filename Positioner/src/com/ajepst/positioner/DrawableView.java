package com.ajepst.positioner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawableView extends View {
	Context mContext;
	private PuppyImage puppy;
	boolean foundPuppy = false;
	Canvas canvas;
	private static final String TAG = "DrawableView";

	public DrawableView(Context context) {
		super(context);
		mContext = context;
		Log.d(TAG, "createing................");
		puppy = new PuppyImage(mContext, R.drawable.puppy);
	}

	@Override
	protected void onDraw(Canvas c) {
		//
		canvas = c;
		canvas.drawColor(0xFFCCCCCC);
		Log.d(TAG, "onDraw method");
		canvas.drawBitmap(puppy.getBitmap(), puppy.getX(), puppy.getY(), null);
		invalidate();
	}
	
	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "got the touch!!!!!!!!!!!!!!!!");
		canvas.drawColor(0xFF0066);
		int eventaction = event.getAction();

		int X = (int) event.getX();
		int Y = (int) event.getY();

		switch (eventaction) {
		case MotionEvent.ACTION_DOWN: // touch down so check if the finger is on
										// a puppy

			// check all the bounds of the puppy

			if (X > puppy.getX() && X < puppy.getX() + 200 && Y > puppy.getY()
					&& Y < puppy.getY() + 200) {
				foundPuppy = true;
				Log.d(TAG, "found puppy");
				break;
			}else{
				Log.d(TAG, "NO puppy");
			}
			break;
		case MotionEvent.ACTION_MOVE: // touch drag with the ball
			// move the balls the same as the finger
			if (foundPuppy) {
				Log.d(TAG, "Moooooving!");
				puppy.setX(X - 25);
				puppy.setY(Y - 25);
			}
			break;
		case MotionEvent.ACTION_UP:
			// touch drop - just do things here after dropping
			foundPuppy = false;
			break;
		}

		Log.d(TAG, "x"+ Integer.toString(puppy.getX()));
		Log.d(TAG, "y"+ Integer.toString(puppy.getY()));
		invalidate();
		return true;
	}
}