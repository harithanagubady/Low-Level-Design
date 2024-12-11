package observers.impl;

import observers.DisplayElement;
import observers.Observer;
import subject.impl.WeatherData;

public class ForecastDisplay implements Observer, DisplayElement {
    private WeatherData weatherData;
    private float temperature;
    private float pressure;
    private float humidity;

//    @Override
//    public void update(float temperature, float humidity, float pressure) {
//        this.temperature = temperature;
//        this.pressure = pressure;
//        this.humidity = humidity;
//        display();
//    }

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Forecast: \n Temperature: " + this.temperature + "\n Pressure: " + this.pressure
                + "\n Humidity: " + this.humidity);
    }

    //pull change
    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.pressure = weatherData.getPressure();
        this.humidity = weatherData.getHumidity();
        display();
    }
}
