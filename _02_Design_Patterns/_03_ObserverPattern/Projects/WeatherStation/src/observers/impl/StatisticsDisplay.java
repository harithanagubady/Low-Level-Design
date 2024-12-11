package observers.impl;

import observers.DisplayElement;
import observers.Observer;
import subject.impl.WeatherData;

public class StatisticsDisplay implements Observer, DisplayElement {

    private WeatherData weatherData;
    private float temperature;
    private float pressure;

//    @Override
//    public void update(float temperature, float humidity, float pressure) {
//        this.temperature = temperature;
//        this.pressure = pressure;
//        display();
//    }

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Statistics: \n Temperature: " + this.temperature + "\n Pressure: " + this.pressure);
    }

    //pull change
    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.pressure = weatherData.getPressure();
        display();
    }
}
