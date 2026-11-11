package com.weatherstation.v3;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

	private List<Observer> observers;
	private float humidity;

	private float pressure;
	private float temperature;

	public WeatherData() {
		observers = new ArrayList<>();
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}

	public float getTemperature() {
		return temperature;
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {

		for(Observer observer: observers) {
			observer.update();
		}
	}

	public void measurementsChanged() {
		notifyObservers();
	}

	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}
}
