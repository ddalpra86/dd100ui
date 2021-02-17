package org.dalpra.acme.rest.entities;

public class Airport {
	private Long id;
	private String airportCode;
	private String airportName;
	private String cityName;
	private String countryName;
	
	public Airport(Long id, String airportCode, String airportName, String cityName, String countryName) {
		this.id = id;
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.cityName = cityName;
		this.countryName = countryName;
	}
	
	public Airport() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	

}
