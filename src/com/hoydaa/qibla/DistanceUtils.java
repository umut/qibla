package com.hoydaa.qibla;

import java.util.Calendar;

/**
 * @author Umut Utkan
 */
public class DistanceUtils {

	private final static String LT = DistanceUtils.class.getSimpleName();

	private final static int EARTH_RADIUS = 6371;

	/**
	 * Algorithm used
	 * 
	 * <code>
	 * 	var dLat = (lat2-lat1).toRad();
	 * 	var dLon = (lon2-lon1).toRad(); 
	 * 	var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	 * 	        Math.cos(lat1.toRad()) * Math.cos(lat2.toRad()) * 
	 * 	        Math.sin(dLon/2) * Math.sin(dLon/2); 
	 * 	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
	 * 	var d = R * c;
	 * 	return 0;
	 *  </code>
	 */
	public static double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
		double dLatitude = Math.toRadians(latitude2 - latitude1);
		double dLongitude = Math.toRadians(longitude2 - longitude1);

		double a = Math.pow(Math.sin(dLatitude / 2), 2) + Math.cos(Math.toRadians(latitude1))
				* Math.cos(Math.toRadians(latitude2)) * Math.pow(Math.sin(dLongitude / 2), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = EARTH_RADIUS * c;

		return d;
	}

	/**
	 * Algorith in use.
	 * 
	 * <code>
	 * var y = Math.sin(dLon) * Math.cos(lat2);
	 * var x = Math.cos(lat1)*Math.sin(lat2) -
	 *   Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
	 * var brng = Math.atan2(y, x).toDeg();
     * </code>
	 */
	public static double calculateBearing(double latitude1, double longitude1, double latitude2, double longitude2) {
		double dLatitude = Math.toRadians(latitude2 - latitude1);
		double dLongitude = Math.toRadians(longitude2 - longitude1);
		double rLatitude1 = Math.toRadians(latitude1);
		double rLongitude1 = Math.toRadians(longitude1);
		double rLatitude2 = Math.toRadians(latitude2);
		double rLongitude2 = Math.toRadians(longitude2);

		double y = Math.sin(dLongitude) * Math.cos(rLatitude2);
		double x = Math.cos(rLatitude1) * Math.sin(rLatitude2) - Math.sin(rLatitude1) * Math.cos(rLatitude2)
				* Math.cos(dLongitude);
		return Math.toDegrees(Math.atan2(y, x));
	}


	public static double[] calculateRightAscensionAndDeclination(Calendar c) {
		double d = calculateJulianDay(c);

		// longitude of perihelion
		double w = 282.9404 + 4.70935E-5 * d;
		// mean distance, a.u.
		double a = 1.000000;
		// eccentricity
		double e = 0.016709 - 1.151E-9 * d;
		// mean anomaly
		double M = (356.0470 + 0.9856002585 * d) % 360;
		M = M < 0 ? (M + 360) : M;

		double oblecl = 23.4393 - 3.563E-7 * d;

		double L = (w + M) % 360;

		double E = M + (180 / Math.PI) * e * Math.sin(Math.toRadians(M)) * (1 + e * Math.cos(Math.toRadians(M)));

		double x = Math.cos(Math.toRadians(E)) - e;
		double y = Math.sin(Math.toRadians(E)) * Math.sqrt(1 - e * e);

		double r = Math.sqrt(x * x + y * y);
		double v = Math.toDegrees(Math.atan2(y, x));

		double lon = (v + w) % 360;

		x = r * Math.cos(Math.toRadians(lon));
		y = r * Math.sin(Math.toRadians(lon));
		double z = 0.0;

		double[] rotated = rotate(x, y, z, oblecl);

		// Right Ascension
		double RA = Math.toDegrees(Math.atan2(rotated[1], rotated[0])) / 15;
		// declination
		double Decl = Math.toDegrees(Math.asin(rotated[2]) / r);
		
//		double GMST0 = L / 15 + 12;
//		double SIDTIME = GMST0 + 0 + 15 / 15;
//		double HA = SIDTIME - RA;

		double EoT = M / 15 - RA;
		
		return new double[] { RA, Decl, EoT };
	}

	public static double[] rotate(double x, double y, double z, double angle) {
		double xequat = x;
		double yequat = y * Math.cos(Math.toRadians(angle)) - z * Math.sin(Math.toRadians(angle));
		double zequat = y * Math.sin(Math.toRadians(angle)) + z * Math.cos(Math.toRadians(angle));
		return new double[] { xequat, yequat, zequat };
	}

	public static int calculateJulianDay(Calendar c) {
		int Y = c.get(Calendar.YEAR);
		int M = c.get(Calendar.MONTH) + 1;
		int D = c.get(Calendar.DAY_OF_MONTH);

		return 367 * Y - (7 * (Y + ((M + 9) / 12))) / 4 + (275 * M) / 9 + D - 730530;
	}

}
