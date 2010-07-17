package com.hoydaa.qibla;

import java.util.Calendar;

/**
 * Class for prayar times calculation.
 * 
 * @author Umut Utkan
 */
public class PrayarTimeCalculator {

	private FajrIslaAngle fajrIslaAngle = FajrIslaAngle.MWL;

	private JuristicMethod juristicMethod = JuristicMethod.STANDARD;

	private Calendar calendar;

	private double[] declination;

	private double longitude;

	private double latitude;

	private double dhuhr;

	public PrayarTimeCalculator(double longitude, double latitude) {
		this.calendar = Calendar.getInstance();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Prayars getPrayars() {
		return getPrayars(this.calendar);
	}

	public Prayars getPrayars(Calendar c) {
		Prayars prayars = new Prayars();
		prayars.setDhuhr(getDhuhr() + "");
		prayars.setFajr(getFajr() + "");
		prayars.setIsla(getIsla() + "");
		prayars.setAsr(getAsr() + "");
		prayars.setMaghrib(getMaghrib() + "");
		return prayars;
	}

	public double getDhuhr() {
		if (dhuhr == 0) {
			dhuhr = 12 + calendar.getTimeZone().getRawOffset() - this.longitude / 15 - Math.toRadians(getEquationOfTime());
		}
		return dhuhr;
	}

	public double getFajr() {
		return getDhuhr() - calculateToMidDay(fajrIslaAngle.getFajrAngle());
	}

	public double getIsla() {
		return getDhuhr() + calculateToMidDay(fajrIslaAngle.getIslaAngle());
	}

	public double getAsr() {
		return getDhuhr()
				+ Math.toDegrees(Math.acos((Math.sin(Math.acos(Math.toRadians(juristicMethod.getValue()) + Math.tan(Math.toRadians(latitude - getDeclination()))
						- Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(getDeclination())))
						/ Math.cos(Math.toRadians(latitude)) / Math.cos(Math.toRadians(getDeclination()))))) / 15);
	}

	public double getMaghrib() {
		return getDhuhr() + calculateToMidDay(4);
	}

	private double getDeclination() {
		if (null == declination) {
			declination = DistanceUtils.calculateRightAscensionAndDeclination(this.calendar);
		}
		return declination[0];
	}

	private double getEquationOfTime() {
		if (null == declination) {
			declination = DistanceUtils.calculateRightAscensionAndDeclination(this.calendar);
		}
		return declination[1];
	}

	private double calculateToMidDay(double a) {
		return  
			Math.toDegrees(Math.acos(
					-1 * 
					(
							Math.sin(Math.toRadians(a)) + 
							Math.sin(Math.toRadians(latitude)) * 
							Math.sin(Math.toRadians(getDeclination()))
					) / 
					Math.cos(Math.toRadians(latitude)) / 
					Math.cos(Math.toRadians(getDeclination()))
			)) / 15;
	}

	public void setFajrIslaAngle(FajrIslaAngle fajrIslaAngle) {
		this.fajrIslaAngle = fajrIslaAngle;
	}

	public void setJuristicMethod(JuristicMethod juristicMethod) {
		this.juristicMethod = juristicMethod;
	}

	public enum FajrIslaAngle {

		JAFARI(16, 14), ISNA(15, 15), MWL(18, 17), EGYPTIAN(19.5, 17.5), KARACHI(18, 18), MAKKAH(18.5, -1);

		private double fajrAngle;
		private double islaAngle;

		private FajrIslaAngle(double fajrAngle, double islaAngle) {
			this.fajrAngle = fajrAngle;
			this.islaAngle = islaAngle;
		}

		public double getFajrAngle() {
			return fajrAngle;
		}

		public double getIslaAngle() {
			return islaAngle;
		}

	}

	public enum JuristicMethod {
		STANDARD(1), HANAFI(2);

		private int value;

		private JuristicMethod(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

}
