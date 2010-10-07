package com.ajepst.howfar;


import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HowFar extends Activity {
	
	Location storedLocation;
	Context myself;
	private LocationManager locationManager;
	private LocationListener locationListener;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myself = this;
        
       
	    locationManager = (LocationManager) myself.getSystemService(Context.LOCATION_SERVICE);
	    Log.d("HowFar", "setting location listener");
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
              // Called when a new location is found by the network location provider.
              makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
          }; 
          
          
          Log.d("HowFar", "set click listener");
        Button getnewloc = (Button)findViewById(R.id.getlocation);
        getnewloc.setOnClickListener(goGetLocation);
         
   }
    
    private OnClickListener goGetLocation = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.d("HowFar", "requesting updates");

	        // Register the listener with the Location Manager to receive location updates
	        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	 
		}
    };


	protected void makeUseOfNewLocation(Location location) {
		Log.d("HowFar", "got called back!");
		TextView text = (TextView)findViewById(R.id.location);
		text.setText(location.toString());
		TextView status = (TextView)findViewById(R.id.status);
		if (storedLocation == null)
			status.setText( "hey, first location!" + Double.toString(location.getLatitude()));
		else
		{
		float distanceTo = location.distanceTo(storedLocation);
		
		if (distanceTo > 400)
			status.setText("you have moved more than 1/4 mile " + Float.toString(distanceTo));
		else 
			status.setText("you haven't moved much " + Float.toString(distanceTo));
		}
		storedLocation = location;
		locationManager.removeUpdates(locationListener);
	}
}