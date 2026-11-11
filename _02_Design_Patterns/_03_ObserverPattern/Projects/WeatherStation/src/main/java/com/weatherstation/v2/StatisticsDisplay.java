package com.weatherstation.v2;

public class StatisticsDisplay implements Observer, DisplayElement {

	private float temperature;
	private float humidity;
	private float pressure;
	private WeatherData weatherData;

	public StatisticsDisplay(WeatherData weatherData) {//to register as observer
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void display() {
		System.out.println("StatisticsDisplay{" +
				"temperature=" + temperature +
				", humidity=" + humidity +
				", pressure=" + pressure +
				'}');
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		this.temperature = temp;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}
}
