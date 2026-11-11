package com.weatherstation.v1;

public class WeatherData {

	CurrentConditionsDisplay currentConditionsDisplay;
	StatisticsDisplay statisticsDisplay;
	ForecastDisplay forecastDisplay;

	public float getTemperature() {
		return 72f;
	}

	public float getHumidity() {
		return 60f;
	}

	public float getPressure() {
		return 30f;
	}

	public void measurementsChanged() {
		float temp = getTemperature();
		float humidity = getHumidity();
		float pressure = getPressure();

		currentConditionsDisplay.update(temp, humidity, pressure);
		statisticsDisplay.update(temp, humidity, pressure);
		forecastDisplay.update(temp, humidity, pressure);
	}
}
