package com.ajepst.drumkit;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Drumkit extends Activity {
	private Context myself;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myself = this;
        Button snare = (Button)findViewById(R.id.snare);
        snare.setOnClickListener(snarePlayer);
        
        Button kick = (Button)findViewById(R.id.kick);
        kick.setOnClickListener(kickPlayer);

        Button hihat = (Button)findViewById(R.id.hihat);
        hihat.setOnClickListener(hihatPlayer);
    }
    
    private OnClickListener snarePlayer = new OnClickListener() {

		@Override
		public void onClick(View v) {
			playSound(R.raw.snarewav);
		}
    };

    
    private OnClickListener kickPlayer = new OnClickListener() {
		@Override
		public void onClick(View v) {
			 playSound(R.raw.kickwav);
		}
    };
    
    private OnClickListener hihatPlayer = new OnClickListener() {
		@Override
		public void onClick(View v) {
			 playSound(R.raw.hihat);
		}
    };
    
    
	private void playSound(int soundId) {
		MediaPlayer mp = MediaPlayer.create(myself, soundId);
		 mp.start();
         mp.setOnCompletionListener(new OnCompletionListener() {

             @Override
             public void onCompletion(MediaPlayer mp) {
                 // TODO Auto-generated method stub
                 mp.release();
             }

         });
	}
}