package observers.impl;

import observers.DisplayElement;
import observers.Observer;
import subject.impl.WeatherData;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private WeatherData weatherData;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
//    @Override
//    public void update(float temperature, float humidity, float pressure) {
//        this.temperature = temperature;
//        this.humidity = humidity;
//        display();
//    }

    @Override
    public void display() {
        System.out.println("Current Conditions: \n Temperature: " + this.temperature + "\n Humidity: " + this.humidity);
    }

    //pull change
    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        display();
    }
}
