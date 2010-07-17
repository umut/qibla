package com.hoydaa.qibla;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 
 * @author Umut Utkan
 * 
 */
public class PrayarChecker extends BroadcastReceiver implements LocationListener {

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
			
			Prayars prayar = checkIfPrayarExistsInTheNearFuture();
			if (null != prayar) {
				Intent newIntent = new Intent(context, ShowQibla.class);
				newIntent.putExtra("prayar", prayar);
				newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(newIntent);
			}
		} catch (Exception e) {
			Toast.makeText(context, "Something wrong!", Toast.LENGTH_SHORT).show();
		}
	}

	private Prayars checkIfPrayarExistsInTheNearFuture() {
		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub		
	}

}
