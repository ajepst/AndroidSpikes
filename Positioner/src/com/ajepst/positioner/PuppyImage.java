package com.ajepst.positioner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PuppyImage {
	 private int coordX = 0; // the x coordinate at the canvas
	 private int coordY = 0; // the y coordinate at the canvas
	 private Bitmap puppyImg;
	
	public PuppyImage(Context context, int drawable){
		BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), drawable); 

		int width = bm.getWidth();
		int height = bm.getHeight();
		int newWidth = width / 5;
		int newHeight = height / 5;

		puppyImg = Bitmap.createScaledBitmap(bm, newWidth, newHeight, true);
	}
	
	public Bitmap getBitmap() {
		return puppyImg;
	}

	public int getX() {
		return coordX;
	}
	
	public int getY() {
		return coordY;
	}

	public void setX(int i) {
		coordX = i;	
	}
	
	public void setY(int i) {
		coordY = i;	
	}

}
