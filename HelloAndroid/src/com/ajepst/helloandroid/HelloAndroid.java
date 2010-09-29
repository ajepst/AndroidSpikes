package com.ajepst.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelloAndroid extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button = (Button)findViewById(R.id.mybutton);
        button.setOnClickListener(textChanger);
     
    }
    
 // Create an anonymous implementation of OnClickListener
    private OnClickListener textChanger = new OnClickListener() {

		@Override
		public void onClick(View v) {
			String blah1 = "blah";
			String yippee = "yippeee!!!";
			Button text = (Button)findViewById(R.id.mybutton);
			if (text.getText() == blah1)
			{
				text.setText(yippee);
			}
			else
			{
				text.setText(blah1);
			}
		}

    };
}