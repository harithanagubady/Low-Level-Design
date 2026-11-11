package com.weatherstation.v3;

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
	public void update() {
		this.temperature = weatherData.getTemperature();
		this.humidity = weatherData.getHumidity();
		this.pressure = weatherData.getPressure();
		display();
	}
}
