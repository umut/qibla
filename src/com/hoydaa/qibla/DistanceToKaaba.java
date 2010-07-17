package com.hoydaa.qibla;

/**
 * Represents the distance to Kaaba, including the heading.
 * 
 * @author Umut Utkan
 */
public class DistanceToKaaba {

	//N= 21d 25' 15.6" = 21.421
	private final static double LATITUDE_OF_KAABA = 21.421;
	
	//E = 39d 49'29.1" = 39.82475
	private final static double LONGITUDE_OF_KAABA = 39.82475;
	
	private long distance;
	
	private double heading;

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public double getHeading() {
		return heading;
	}

	public void setHeading(double heading) {
		this.heading = heading;
	}
	
	public static DistanceToKaaba getInstance() {
		return null;
	}
	
}
