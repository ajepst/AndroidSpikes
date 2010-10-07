package com.ajepst.positioner;

import android.app.Activity;
import android.os.Bundle;

public class Positioner extends Activity {
	DrawableView view;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new DrawableView(this);
        setContentView(view);
    }
    
}

