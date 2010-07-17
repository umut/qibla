package com.hoydaa.qibla.test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import junit.framework.TestCase;
import android.util.Log;

import com.hoydaa.qibla.PrayarTimeCalculator;
import com.hoydaa.qibla.Prayars;

/**
 * 
 * @author Umut Utkan
 *
 */
public class PrayarTimeCalculatorTest extends TestCase {

	private final static String LT = PrayarTimeCalculatorTest.class.getSimpleName();
	
	public void testPrayarTimes() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(new Date(110, 5, 3, 0, 0, 0));
		
		PrayarTimeCalculator calculator = new PrayarTimeCalculator(29, 42);
		Prayars prayars = calculator.getPrayars(calendar);
		
		Log.d(LT, "Prayars: " + prayars);
		
		assertEquals("", prayars.getDhuhr());
		assertEquals("", prayars.getFajr());
		assertEquals("", prayars.getIsla());
		assertEquals("", prayars.getAsr());
		assertEquals("", prayars.getMaghrib());
	}
	
}
