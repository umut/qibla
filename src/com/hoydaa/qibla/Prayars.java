package com.hoydaa.qibla;

import java.io.Serializable;

/**
 * 
 * @author Umut Utkan
 *
 */
public class Prayars implements Serializable {
	private String dhuhr;
	private String fajr;
	private String isla;
	private String asr;
	private String maghrib;

	public Prayars() {

	}

	public String getDhuhr() {
		return dhuhr;
	}

	public void setDhuhr(String dhuhr) {
		this.dhuhr = dhuhr;
	}

	public String getFajr() {
		return fajr;
	}

	public void setFajr(String fajr) {
		this.fajr = fajr;
	}

	public String getIsla() {
		return isla;
	}

	public void setIsla(String isla) {
		this.isla = isla;
	}

	public String getAsr() {
		return asr;
	}

	public void setAsr(String asr) {
		this.asr = asr;
	}

	public String getMaghrib() {
		return maghrib;
	}

	public void setMaghrib(String maghrib) {
		this.maghrib = maghrib;
	}

	public String toString() {
		return "dhuhr: " + dhuhr + ", fajr: " + fajr + ", isla: " + isla + ", asr: " + asr + ", maghrib: "
				+ maghrib;
	}
}
