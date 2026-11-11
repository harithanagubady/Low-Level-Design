package com.weatherstation.v2;

public interface Observer {

	public void update(float temp, float humidity, float pressure);
}
