package com.hoydaa.qibla;

import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

/**
 * @author Umut Utkan
 */
public class ShowQibla extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// if the application is opened just now, register to alarm manager.
		Intent intent = getIntent();
		if (intent.equals(Intent.ACTION_MAIN)) {
			registerToAlarmManager();
			finish();
			return;
		}

		String message = intent.getExtras().getString("message");
		Date date = (Date) intent.getExtras().get("date");

		setContentView(R.layout.main);
	}

	private void registerToAlarmManager() {
		AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

		Intent i = new Intent(this, PrayarChecker.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 60000, pi);
	}

}
