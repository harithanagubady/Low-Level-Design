package com.weatherstation.v1;

public class CurrentConditionsDisplay {

	float temp;
	float humidity;
	float pressure;

	public void update(float temp, float humidity, float pressure) {
		this.temp = temp;
		this.humidity = humidity;
		this.pressure = pressure;
	}
}
