package com.hoydaa.qibla.test;

import java.util.Calendar;
import java.util.Date;

import com.hoydaa.qibla.DistanceUtils;

import junit.framework.TestCase;

/**
 * 
 * @author Umut Utkan
 * 
 */
public class DistanceUtilsTest extends TestCase {

	public void testRotate() {
		double rotated[] = DistanceUtils.rotate(0.881048, 0.482098, 0.0, 23.4406);

		assertEqualsWithPrecision(0.881048, rotated[0]);
		assertEqualsWithPrecision(0.442311, rotated[1]);
		assertEqualsWithPrecision(0.191777, rotated[2]);
	}

	public void testCalculateJulianDay() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(90, 3, 19, 0, 0, 0));

		assertEquals(-3543, DistanceUtils.calculateJulianDay(c));
	}

	public void testRightAscensionAndDeclination() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(90, 3, 19, 0, 0, 0));

		double[] val = DistanceUtils.calculateRightAscensionAndDeclination(c);
		assertEqualsWithPrecision(1.77720, val[0]);
		assertEqualsWithPrecision(11.0089, val[1]);
		assertEqualsWithPrecision(13.01205, val[2]);
	}

	public void testCalculateDeclination() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(110, 5, 3, 0, 0, 0));

		assertEquals(22.289, getWithPrecision(DistanceUtils.calculateRightAscensionAndDeclination(calendar)[1], 3));

		calendar.setTime(new Date(110, 0, 1, 0, 0, 0));
		assertEquals(-23.004, getWithPrecision(DistanceUtils.calculateRightAscensionAndDeclination(calendar)[1], 3));
	}

	public void testEquationOfTime() {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date(110, 5, 3, 0, 0, 0));
		assertEquals(4.539, getWithPrecision(DistanceUtils.calculateRightAscensionAndDeclination(calendar)[2], 3));

		calendar.setTime(new Date(110, 0, 1, 0, 0, 0));
		assertEquals(4.611, getWithPrecision(DistanceUtils.calculateRightAscensionAndDeclination(calendar)[2], 3));
	}

	public void testCalculateDistanceAndBearing() {
		// TODO: add more test data, you can use page: http://www.movable-type.co.uk/scripts/latlong.html to generate
		// data for the test.
		assertEquals(158.538, getWithPrecision(DistanceUtils.calculateDistance(10.169444444, 10.169444444,
				11.186388889, 11.186388889), 3));
		assertEquals(44.406, getWithPrecision(DistanceUtils.calculateBearing(10.169444444, 10.169444444, 11.186388889,
				11.186388889), 3));
	}

	public void assertEqualsWithPrecision(double actual, double expected) {
		int precision = (actual + "").substring((actual + "").indexOf('.') + 1).length();
		assertEquals(actual, getWithPrecision(expected, precision));
	}

	public double getWithPrecision(double value, int precision) {
		return getWithPrecision(value, precision, false);
	}

	public double getWithPrecision(double value, int precision, boolean round) {
		if (round) {
			return Math.round(value * Math.pow(10, precision)) / Math.pow(10, precision);
		} else {
			return ((int) (value * Math.pow(10, precision))) / Math.pow(10, precision);
		}
	}

}
