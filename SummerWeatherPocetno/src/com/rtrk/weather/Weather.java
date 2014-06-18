package com.rtrk.weather;

public class Weather {

	private int hi_temperature_c;
	private int low_temerature_c;
	private String conditions;
	private String icon_url;
	private int period;

	public Weather() {
		super();
	}
	public Weather(int hiTemperatureC, int lowTemeratureC, String conditions,
			String iconUrl) {
		super();
		hi_temperature_c = hiTemperatureC;
		low_temerature_c = lowTemeratureC;
		this.conditions = conditions;
		icon_url = iconUrl;
	}	

	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getHiTemperatureC() {
		return hi_temperature_c;
	}
	public void setHiTemperatureC(int hiTemperatureC) {
		hi_temperature_c = hiTemperatureC;
	}

	public int getLowTemeratureC() {
		return low_temerature_c;
	}
	public void setLowTemeratureC(int lowTemeratureC) {
		low_temerature_c = lowTemeratureC;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getIconUrl() {
		return icon_url;
	}
	public void setIconUrl(String iconUrl) {
		icon_url = iconUrl;
	}
	
	

	
 
}
